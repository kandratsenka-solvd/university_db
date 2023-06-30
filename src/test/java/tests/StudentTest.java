package tests;

import connection.ConnectionPool;
import dao.PersonDAO;
import dao.StudentDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import utils.*;

import java.sql.Connection;


public class StudentTest extends BaseTest {

    private static final Logger LOGGER = LogManager.getLogger();

    @Test
    public void addStudent1() {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PersonDAO personDAO = new PersonDAO(connection);
        int personId = personDAO.add(PersonUtil.generatePerson());
        StudentDAO studentDAO = new StudentDAO(connection);
        int studentId = studentDAO.addStudent(PersonUtil.generateStudent(1), personId);
        LOGGER.info(ResultSetUtil.getFirstRecord(studentDAO.getStudentById(studentId)));
        studentDAO.graduateStudent(studentId, 1, 1);
        studentDAO.updateStudent(studentId, 5);
        studentDAO.deleteStudentById(studentId);
        ConnectionPool.getInstance().returnConnection(connection);
        }

    @Test
    public void addStudent2() {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PersonDAO personDAO = new PersonDAO(connection);
        int personId = personDAO.add(PersonUtil.generatePerson());
        StudentDAO studentDAO = new StudentDAO(connection);
        studentDAO.addStudent(PersonUtil.generateStudent(2), personId);
        ConnectionPool.getInstance().returnConnection(connection);
        }

    @Test
    public void addStudent3() {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PersonDAO personDAO = new PersonDAO(connection);
        int personId = personDAO.add(PersonUtil.generatePerson());
        StudentDAO studentDAO = new StudentDAO(connection);
        studentDAO.addStudent(PersonUtil.generateStudent(3), personId);
        ConnectionPool.getInstance().returnConnection(connection);
        }

    @Test
    public void addStudent4() {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PersonDAO personDAO = new PersonDAO(connection);
        int personId = personDAO.add(PersonUtil.generatePerson());
        StudentDAO studentDAO = new StudentDAO(connection);
        studentDAO.addStudent(PersonUtil.generateStudent(4), personId);
        ConnectionPool.getInstance().returnConnection(connection);
        }

    @Test
    public void addStudent5() {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PersonDAO personDAO = new PersonDAO(connection);
        int personId = personDAO.add(PersonUtil.generatePerson());
        StudentDAO studentDAO = new StudentDAO(connection);
        studentDAO.addStudent(PersonUtil.generateStudent(5), personId);
        ConnectionPool.getInstance().returnConnection(connection);
        }

    @Test
    public void addStudent6() {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PersonDAO personDAO = new PersonDAO(connection);
        int personId = personDAO.add(PersonUtil.generatePerson());
        StudentDAO studentDAO = new StudentDAO(connection);
        studentDAO.addStudent(PersonUtil.generateStudent(6), personId);
        ConnectionPool.getInstance().returnConnection(connection);
    }

    @Test
    public void addStudent7() {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PersonDAO personDAO = new PersonDAO(connection);
        int personId = personDAO.add(PersonUtil.generatePerson());
        StudentDAO studentDAO = new StudentDAO(connection);
        studentDAO.addStudent(PersonUtil.generateStudent(7), personId);
        ConnectionPool.getInstance().returnConnection(connection);
        }
}
