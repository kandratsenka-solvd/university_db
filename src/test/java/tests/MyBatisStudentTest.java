package tests;

import connection.ConnectionPool;
import connection.CustomSqlSession;
import mappers.IStudentMapper;
import models.Student;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.PersonUtil;

import java.sql.Connection;
import java.util.List;


public class MyBatisStudentTest {

    private static final Logger LOGGER = LogManager.getLogger();
    private final Connection connection = ConnectionPool.getInstance().getConnection();

    @Test
    public void testAddStudent() {
        SqlSession sqlSession = CustomSqlSession.openSession(connection);
        IStudentMapper iStudentMapper = sqlSession.getMapper(IStudentMapper.class);
        iStudentMapper.add(PersonUtil.generateStudent(100));
        int i = iStudentMapper.getGeneratedKey();
        LOGGER.info("student_id: " + i);
    }

    @Test
    public void testGetStudentById() {
        int studentId = 40;
        SqlSession sqlSession = CustomSqlSession.openSession(connection);
        IStudentMapper iStudentMapper = sqlSession.getMapper(IStudentMapper.class);
        Student student = iStudentMapper.getById(studentId);
        LOGGER.info(student);
    }

    @Test
    public void testGetStudentList() {
        SqlSession sqlSession = CustomSqlSession.openSession(connection);
        IStudentMapper iStudentMapper = sqlSession.getMapper(IStudentMapper.class);
        List<Student> studentList = iStudentMapper.getAll();
        LOGGER.info(studentList.get(0));
    }

    @Test
    public void testUpdateStudent() {
        SqlSession sqlSession = CustomSqlSession.openSession(connection);
        IStudentMapper iStudentMapper = sqlSession.getMapper(IStudentMapper.class);
        List<Student> studentList = iStudentMapper.getAll();
        Student oldStudent = studentList.get(0);
        int studentId = oldStudent.getStudentId();
        int personId = oldStudent.getPersonId();
        Student newStudent = PersonUtil.generateStudent(personId);
        newStudent.setStudentId(studentId);
        iStudentMapper.updateById(newStudent);
        int newEduGroupId = iStudentMapper.getById(studentId).getEduGroupId();
        Assert.assertNotEquals(oldStudent.getEduGroupId(), newEduGroupId);
    }

    @Test
    public void testDeleteStudentById() {
        int studentId = 36;
        SqlSession sqlSession = CustomSqlSession.openSession(connection);
        IStudentMapper iStudentMapper = sqlSession.getMapper(IStudentMapper.class);
        iStudentMapper.deleteById(studentId);
    }
}
