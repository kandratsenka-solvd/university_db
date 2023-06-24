package tests;

import mappers.IStudentMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import utils.PersonUtil;

import java.io.IOException;
import java.io.Reader;

public class MyBatisStudentTest {

    private static final Logger LOGGER = LogManager.getLogger();

    @Test
    public void testAddStudent() {
        try (Reader reader = Resources.getResourceAsReader("mybatis-config.xml")) {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            SqlSession sqlSession = sqlSessionFactory.openSession(false);
            System.out.println(sqlSession.hashCode());
            IStudentMapper iStudentMapper = sqlSession.getMapper(IStudentMapper.class);
            iStudentMapper.add(PersonUtil.generateStudent());
            int i = iStudentMapper.getGeneratedKey();
            LOGGER.info("student_id: " + i);
            sqlSession.commit();
            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
