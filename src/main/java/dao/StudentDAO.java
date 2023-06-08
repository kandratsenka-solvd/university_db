package dao;
import connection.ConnectionPool;
import models.Student;
import utils.FileManagerUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDAO extends PersonDAO {

    public void addStudent(Student student) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FileManagerUtil
                    .getFileAsString("queries/addStudent.sql"));
            preparedStatement.setInt(1, 1);
            preparedStatement.setInt(2, student.getGroupId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }
}

