package tests;

import connection.ConnectionPool;
import connection.DbSqlSession;
import mappers.IPersonMapper;
import models.Person;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.PersonUtil;
import java.sql.Connection;
import java.util.List;

public class MyBatisPersonTest extends BaseTest {

    private static final Logger LOGGER = LogManager.getLogger();
    private final Connection connection = ConnectionPool.getInstance().getConnection();

    @Test
    public void testAddPerson() {
        SqlSession sqlSession = DbSqlSession.openSession(connection);
        IPersonMapper iPersonMapper = sqlSession.getMapper(IPersonMapper.class);
        Person personFromGenerator = PersonUtil.generatePerson();
        iPersonMapper.add(personFromGenerator);
        int personId = iPersonMapper.getGeneratedKey();
        Person personFromTable = iPersonMapper.getById(personId);
        LOGGER.info("Expected result name: " + personFromGenerator.getFullName());
        LOGGER.info("Actual result: " + personFromTable.getFullName());
        Assert.assertEquals(personFromGenerator.getFullName(), personFromTable.getFullName(),
                "Objects have different names.");
    }

    @Test
    public void testGetPersonById() {
        int personId = 2;
        SqlSession sqlSession = DbSqlSession.openSession(connection);
        IPersonMapper iPersonMapper = sqlSession.getMapper(IPersonMapper.class);
        Person person = iPersonMapper.getById(personId);
        LOGGER.info("Person: {}; {}; {}", person.getFullName(), person.getAddress(), person.getEmail());
    }

    @Test
    public void testGetPersonByFullName() {
        SqlSession sqlSession = DbSqlSession.openSession(connection);
        IPersonMapper iPersonMapper = sqlSession.getMapper(IPersonMapper.class);
        List<Person> personList = iPersonMapper.getAll();
        String fullName = personList.get(0).getFullName();
        Person person = iPersonMapper.getPersonByFullName(fullName);
        LOGGER.info(person.getEmail());
    }

    @Test
    public void testGetPersonList() {
        int id = 1;
        SqlSession sqlSession = DbSqlSession.openSession(connection);
        IPersonMapper iPersonMapper = sqlSession.getMapper(IPersonMapper.class);
        List<Person> personList = iPersonMapper.getAll();
        Person person = personList.get(id);
        LOGGER.info("Person full name: " + person.getFullName());
    }

    @Test
    public void testUpdatePersonById() {
        SqlSession sqlSession = DbSqlSession.openSession(connection);
        IPersonMapper iPersonMapper = sqlSession.getMapper(IPersonMapper.class);
        List<Person> personList = iPersonMapper.getAll();
        Person oldPerson = personList.get(0);
        LOGGER.info("Name before update: " + oldPerson.getFullName());
        int personId = oldPerson.getPersonId();
        Person newPerson = PersonUtil.generatePerson();
        newPerson.setPersonId(personId);
        iPersonMapper.updateById(newPerson);
        LOGGER.info("Name after update: " + iPersonMapper.getById(personId).getFullName());
        Assert.assertNotEquals(oldPerson.getFullName(), newPerson.getFullName());
    }

    @Test
    public void testUpdateEmailByFullName() {
        SqlSession sqlSession = DbSqlSession.openSession(connection);
        IPersonMapper iPersonMapper = sqlSession.getMapper(IPersonMapper.class);
        List<Person> personList = iPersonMapper.getAll();
        Person personActual = personList.get(0);
        int personId = personActual.getPersonId();
        String email = PersonUtil.generatePerson().getEmail();
        iPersonMapper.updateEmailByFullName(personActual.getFullName(), email);
        Person personExpected = iPersonMapper.getById(personId);
        String oldEmail = personActual.getEmail();
        String newEmail = personExpected.getEmail();
        Assert.assertNotEquals(oldEmail, newEmail);
        LOGGER.info("Old email: {}; New email: {}", oldEmail, newEmail);
    }

    @Test
    public void testDeletePersonById() {
        int personId = 100;
        SqlSession sqlSession = DbSqlSession.openSession(connection);
        IPersonMapper iPersonMapper = sqlSession.getMapper(IPersonMapper.class);
        iPersonMapper.deleteById(personId);
        Person person = iPersonMapper.getById(personId);
        LOGGER.info(person);
        Assert.assertNull(person);
    }
}
