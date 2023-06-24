package tests;

import connection.SqlSessionPool;
import mappers.IStudentMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import utils.PersonUtil;


public class MyBatisStudentTest {

    private static final Logger LOGGER = LogManager.getLogger();

    @Test
    public void testAddStudent1() {
        SqlSession sqlSession = SqlSessionPool.getInstance().getSqlSession();
        IStudentMapper iStudentMapper = sqlSession.getMapper(IStudentMapper.class);
        iStudentMapper.add(PersonUtil.generateStudent());
        int i = iStudentMapper.getGeneratedKey();
        LOGGER.info("student_id: " + i);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testAddStudent() {
        SqlSession sqlSession = SqlSessionPool.getInstance().getSqlSession();
        IStudentMapper iStudentMapper = sqlSession.getMapper(IStudentMapper.class);
        iStudentMapper.add(PersonUtil.generateStudent());
        int i = iStudentMapper.getGeneratedKey();
        LOGGER.info("student_id: " + i);
        sqlSession.commit();
        sqlSession.close();
    }
}
