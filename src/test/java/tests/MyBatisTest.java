package tests;

import mappers.IPersonMapper;
import models.Person;
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
import java.util.List;

public class MyBatisTest {

    private static final Logger LOGGER = LogManager.getLogger();

    @Test
    public void testMyBatis() {
        try (Reader reader = Resources.getResourceAsReader("mybatis-config.xml")) {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            SqlSession sqlSession = sqlSessionFactory.openSession(false);
            IPersonMapper iPersonMapper = sqlSession.getMapper(IPersonMapper.class);
            iPersonMapper.add(PersonUtil.generatePerson());
            int i = iPersonMapper.getGeneratedKey();
            LOGGER.info("person_id: " + i);
            List<Person> personList = iPersonMapper.getAll();
            Person person = personList.get(personList.size() - 1);
            LOGGER.info(person.getFullName());
            sqlSession.commit();
            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
