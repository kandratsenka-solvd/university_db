package tests;

import dao.StudentDAO;
import org.testng.annotations.Test;
import utils.PersonUtil;

public class StudentTest {

    @Test
    public void addStudent1() {
        StudentDAO studentDAO = new StudentDAO();
        studentDAO.addStudent(PersonUtil.generateStudent());
        }

    @Test
    public void addStudent2() {
        StudentDAO studentDAO = new StudentDAO();
        studentDAO.addStudent(PersonUtil.generateStudent());
        }

    @Test
    public void addStudent3() {
        StudentDAO studentDAO = new StudentDAO();
        studentDAO.addStudent(PersonUtil.generateStudent());
        }

    @Test
    public void addStudent4() {
        StudentDAO studentDAO = new StudentDAO();
        studentDAO.addStudent(PersonUtil.generateStudent());
        }

    @Test
    public void addStudent5() {
        StudentDAO studentDAO = new StudentDAO();
        studentDAO.addStudent(PersonUtil.generateStudent());
        }

    @Test
    public void addStudent6() {
        StudentDAO studentDAO = new StudentDAO();
        studentDAO.addStudent(PersonUtil.generateStudent());
    }

    @Test
    public void addStudent7() {
        StudentDAO studentDAO = new StudentDAO();
        studentDAO.addStudent(PersonUtil.generateStudent());
        }
}
