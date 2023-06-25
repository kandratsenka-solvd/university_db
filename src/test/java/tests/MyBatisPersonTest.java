package tests;

import connection.ConnectionPool;
import connection.CustomSqlSession;
import mappers.IPersonMapper;
import models.Person;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import utils.PersonUtil;
import java.sql.Connection;
import java.util.List;

public class MyBatisPersonTest extends BaseTest {

    private static final Logger LOGGER = LogManager.getLogger();
    private final Connection connection = ConnectionPool.getInstance().getConnection();

    @Test
    public void testAddPerson() {
        SqlSession sqlSession = CustomSqlSession.openSession(connection);
        IPersonMapper iPersonMapper = sqlSession.getMapper(IPersonMapper.class);
        iPersonMapper.add(PersonUtil.generatePerson());
        int i = iPersonMapper.getGeneratedKey();
        LOGGER.info("person_id: " + i);
    }

    @Test
    public void testGetPersonById() {
        int personId = 2;
        SqlSession sqlSession = CustomSqlSession.openSession(connection);
        IPersonMapper iPersonMapper = sqlSession.getMapper(IPersonMapper.class);
        Person person = iPersonMapper.getById(personId);
        LOGGER.info("Person: {}; {}; {}", person.getFullName(), person.getAddress(), person.getEmail());
    }

    @Test
    public void testGetPersonList() {
        int id = 1;
        SqlSession sqlSession = CustomSqlSession.openSession(connection);
        IPersonMapper iPersonMapper = sqlSession.getMapper(IPersonMapper.class);
        List<Person> personList = iPersonMapper.getAll();
        Person person = personList.get(id);
        LOGGER.info("Person full name: " + person.getFullName());
    }

    @Test
    public void testDeletePersonById() {
        int personId = 83;
        SqlSession sqlSession = CustomSqlSession.openSession(connection);
        IPersonMapper iPersonMapper = sqlSession.getMapper(IPersonMapper.class);
        iPersonMapper.deleteById(personId);
    }
}
