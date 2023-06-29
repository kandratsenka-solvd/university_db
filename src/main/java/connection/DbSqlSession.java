package connection;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class DbSqlSession {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String MYBATIS_CONFIG_PATH = "mybatis-config.xml";
    private static final List<SqlSession> sqlSessionList = new ArrayList<>();

    private DbSqlSession(){}

    public static SqlSession openSession(Connection connection) {
        SqlSession sqlSession;
        try (Reader reader = Resources.getResourceAsReader(MYBATIS_CONFIG_PATH)) {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            sqlSession = sqlSessionFactory.openSession(connection);
            LOGGER.info("Opened SqlSession: [{}]", sqlSession);
            sqlSessionList.add(sqlSession);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sqlSession;
    }

    public static void closeSession(SqlSession sqlSession) {
        LOGGER.info("Closing SqlSession [{}]", sqlSession);
        sqlSession.close();
    }

    public static void closeAllSessions() {
        for(SqlSession sqlSession : sqlSessionList) {
            DbSqlSession.closeSession(sqlSession);
        }
        LOGGER.info("Clearing sql sessions list.");
        sqlSessionList.clear();
    }
}
