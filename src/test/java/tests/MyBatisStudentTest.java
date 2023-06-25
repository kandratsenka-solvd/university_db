package tests;

import connection.ConnectionPool;
import connection.CustomSqlSession;
import mappers.IStudentMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import utils.PersonUtil;

import java.sql.Connection;


public class MyBatisStudentTest {

    private static final Logger LOGGER = LogManager.getLogger();
    private final Connection connection = ConnectionPool.getInstance().getConnection();

    @Test
    public void testAddStudent1() {
        SqlSession sqlSession = CustomSqlSession.openSession(connection);
        IStudentMapper iStudentMapper = sqlSession.getMapper(IStudentMapper.class);
        iStudentMapper.add(PersonUtil.generateStudent(30));
        int i = iStudentMapper.getGeneratedKey();
        LOGGER.info("student_id: " + i);
    }

    @Test
    public void testAddStudent() {
        SqlSession sqlSession = CustomSqlSession.openSession(connection);
        IStudentMapper iStudentMapper = sqlSession.getMapper(IStudentMapper.class);
        iStudentMapper.add(PersonUtil.generateStudent(30));
        int i = iStudentMapper.getGeneratedKey();
        LOGGER.info("student_id: " + i);
    }
}
