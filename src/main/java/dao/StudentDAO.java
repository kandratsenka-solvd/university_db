package dao;
import models.Student;
import utils.FileManagerUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO extends PersonDAO {

    private final Connection connection;

    public StudentDAO(Connection connection) {
        super(connection);
        this.connection = connection;
    }

    public int addStudent(Student student, Integer personId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FileManagerUtil
                    .getFileAsString("queries/addStudent.sql"), PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, personId);
            preparedStatement.setInt(2, student.getGroupId());
            preparedStatement.executeUpdate();
            int generatedId = 0;
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                generatedId = generatedKeys.getInt(1);
            }
            generatedKeys.close();
            return generatedId;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getStudentById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FileManagerUtil
                    .getFileAsString("queries/getStudentById.sql"));
            preparedStatement.setInt(1, id);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateStudent(Integer studentId, Integer groupId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FileManagerUtil
                    .getFileAsString("queries/updateStudent.sql"));
            preparedStatement.setInt(1, groupId);
            preparedStatement.setInt(2, studentId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteStudentById(Integer studentId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FileManagerUtil
                    .getFileAsString("queries/removeStudentById.sql"));
            preparedStatement.setInt(1, studentId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void graduateStudent(Integer personId, Integer qualification_id, Integer degree_id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FileManagerUtil
                    .getFileAsString("queries/graduateStudentById.sql"));
            preparedStatement.setInt(1, personId);
            preparedStatement.setInt(2, qualification_id);
            preparedStatement.setInt(3, degree_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}