package tests;

import connection.ConnectionPool;
import connection.DbSqlSession;
import mappers.ILecturerMapper;
import models.Lecturer;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import utils.PersonUtil;

import java.sql.Connection;
import java.util.List;

public class MyBatisLecturerTest {

    private static final Logger LOGGER = LogManager.getLogger();
    private final Connection connection = ConnectionPool.getInstance().getConnection();

    @Test
    public void testAddLecturer() {
        int personId = 1;
        SqlSession sqlSession = DbSqlSession.openSession(connection);
        ILecturerMapper iLecturerMapper = sqlSession.getMapper(ILecturerMapper.class);
        int lecturerId = iLecturerMapper.add(PersonUtil.generateLecturer(personId));
        LOGGER.info("lecturer_id: " + lecturerId);
    }

    @Test
    public void testGetLecturerById() {
        int lecturerId = 1;
        SqlSession sqlSession = DbSqlSession.openSession(connection);
        ILecturerMapper iLecturerMapper = sqlSession.getMapper(ILecturerMapper.class);
        Lecturer lecturer = iLecturerMapper.getById(lecturerId);
        LOGGER.info(lecturer);
    }

    @Test
    public void testGetLecturerList() {
        SqlSession sqlSession = DbSqlSession.openSession(connection);
        ILecturerMapper iLecturerMapper = sqlSession.getMapper(ILecturerMapper.class);
        List<Lecturer> lecturerList = iLecturerMapper.getAll();
        LOGGER.info(lecturerList);
    }

    @Test
    public void testUpdateLecturerById() {
        SqlSession sqlSession = DbSqlSession.openSession(connection);
        ILecturerMapper iLecturerMapper = sqlSession.getMapper(ILecturerMapper.class);
        List<Lecturer> lecturerList = iLecturerMapper.getAll();
        Lecturer oldLecturer = lecturerList.get(0);
        int lecturerId = oldLecturer.getLecturerId();
        int personId = oldLecturer.getPersonId();
        Lecturer newLecturer = PersonUtil.generateLecturer(personId);
        newLecturer.setLecturerId(lecturerId);
        iLecturerMapper.updateById(newLecturer);
    }

    @Test
    public void testDeleteLecturerById() {
        int lecturerId = 2;
        SqlSession sqlSession = DbSqlSession.openSession(connection);
        ILecturerMapper iLecturerMapper = sqlSession.getMapper(ILecturerMapper.class);
        iLecturerMapper.deleteById(lecturerId);
    }
}
