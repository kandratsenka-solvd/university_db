package dao;

import models.Faculty;
import utils.FileManagerUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FacultyDAO extends PersonDAO {

    private final Connection connection;

    public FacultyDAO(Connection connection) {
        super(connection);
        this.connection = connection;
    }

    public int addFaculty(Faculty faculty, Integer personId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FileManagerUtil
                    .getFileAsString("queries/create/insertFaculty.sql"), PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, personId);
            preparedStatement.setString(2, faculty.getFacultyName());
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

    public ResultSet getFacultyById(Integer facultyId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FileManagerUtil
                    .getFileAsString("queries/read/getFacultyById.sql"));
            preparedStatement.setInt(1, facultyId);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteFacultyById(Integer facultyId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FileManagerUtil
                    .getFileAsString("queries/delete/deleteFacultyById.sql"));
            preparedStatement.setInt(1, facultyId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
