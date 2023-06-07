package connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.FileManagerUtil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;


public class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int INITIAL_POOL_SIZE = 5;
    private static final BlockingQueue<Connection> allConnections = new ArrayBlockingQueue<>(INITIAL_POOL_SIZE);
    private final BlockingQueue<Connection> freeConnections;
    private final ReentrantLock lock = new ReentrantLock();
    private static volatile ConnectionPool instance;
    private static final String URL = FileManagerUtil.getValue("config.json", "url");
    private static final String USERNAME = FileManagerUtil.getValue("credentials.json", "username");
    private static final String PASSWORD = FileManagerUtil.getValue("credentials.json", "password");

    private ConnectionPool() {
        createConnectionList();
        freeConnections = new ArrayBlockingQueue<>(INITIAL_POOL_SIZE);
        freeConnections.addAll(allConnections);
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) {
                    instance = new ConnectionPool();
                }
            }
        }
        return instance;
    }

    private static void createConnectionList() {
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            allConnections.add(openConnection());
        }
    }

    private static Connection openConnection() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            LOGGER.info("Created connection: " + connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public Connection getConnection() {
        CompletableFuture<Connection> future = new CompletableFuture<>();
        String currentThreadName = Thread.currentThread().getName();
        try {
            lock.lock();
            int freeConnNum = freeConnections.size();
            Connection connection = freeConnections.poll();
            if (connection != null) {
                LOGGER.info(String.format("Free connections: %s. Received <- %s", freeConnNum, connection));
                future.complete(connection);
            } else {
                LOGGER.warn("No available connections.");
                CompletableFuture.supplyAsync(() -> {
                    try {
                        LOGGER.warn(String.format("<==> [%s]. Waiting for connection...", currentThreadName));
                        return freeConnections.take();
                    } catch (InterruptedException e) {
                        LOGGER.error("Error while receiving connection.");
                        throw new RuntimeException(e);
                    }
                }).thenAccept(receivedConnection -> {
                    LOGGER.info("Received <- " + receivedConnection);
                    future.complete(receivedConnection);
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

    public void returnConnection(Connection connection) {
        LOGGER.info("Returned -> " + connection);
        boolean added = freeConnections.offer(connection);
        if (!added) {
            LOGGER.warn("Failed to return connection to the pool.");
        }
    }

    public void closeAllConnections() {
        for (Connection connection : allConnections) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        allConnections.clear();
        freeConnections.clear();
    }
}