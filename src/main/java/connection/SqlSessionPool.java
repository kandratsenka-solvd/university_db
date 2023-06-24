package connection;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.io.Reader;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class SqlSessionPool {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int INITIAL_POOL_SIZE = 5;
    private static final BlockingQueue<SqlSession> allSessions = new ArrayBlockingQueue<>(INITIAL_POOL_SIZE);
    private final BlockingQueue<SqlSession> freeSessions;
    private final ReentrantLock lock = new ReentrantLock();
    private static volatile SqlSessionPool instance;
    private static final String MYBATIS_CONFIG_PATH = "mybatis-config.xml";

    private SqlSessionPool() {
        createSessionList();
        freeSessions = new ArrayBlockingQueue<>(INITIAL_POOL_SIZE);
        freeSessions.addAll(allSessions);
    }

    public static SqlSessionPool getInstance() {
        if (instance == null) {
            synchronized (SqlSessionPool.class) {
                if (instance == null) {
                    instance = new SqlSessionPool();
                }
            }
        }
        return instance;
    }


    private static void createSessionList() {
        try (Reader reader = Resources.getResourceAsReader(MYBATIS_CONFIG_PATH)) {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
                SqlSession sqlSession = sqlSessionFactory.openSession(false);
                LOGGER.info("Created Sql session: {}", sqlSession);
                allSessions.add(sqlSession);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to create session list.", e);
        }
    }

    public SqlSession getSqlSession() {
        CompletableFuture<SqlSession> future = new CompletableFuture<>();
        String currentThreadName = Thread.currentThread().getName();
        try {
            lock.lock();
            int freeSessionNum = freeSessions.size();
            SqlSession sqlSession = freeSessions.poll();
            if (sqlSession != null) {
                LOGGER.info(String.format("Free sessions: %s. Received <- %s", freeSessionNum, sqlSession));
                future.complete(sqlSession);
            } else {
                LOGGER.warn("No available sessions.");
                CompletableFuture.supplyAsync(() -> {
                    try {
                        LOGGER.warn(String.format("<==> [%s]. Waiting for session...", currentThreadName));
                        return freeSessions.take();
                    } catch (InterruptedException e) {
                        LOGGER.error("Error while receiving session.");
                        throw new RuntimeException(e);
                    }
                }).thenAccept(receivedSession -> {
                    LOGGER.info("Received <- " + receivedSession);
                    future.complete(receivedSession);
                });
            }
        } catch (Exception e) {
            future.completeExceptionally(e);
        } finally {
            lock.unlock();
        }
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public void returnSqlSession(SqlSession sqlSession) {
        LOGGER.info("Returned -> " + sqlSession);
        boolean added = freeSessions.offer(sqlSession);
        if (!added) {
            LOGGER.warn("Failed to return session to the pool.");
        }
    }

    public void closeAllSqlSessions() {
        for (SqlSession sqlSession : allSessions) {
            sqlSession.close();
        }
        allSessions.clear();
        freeSessions.clear();
    }
}
