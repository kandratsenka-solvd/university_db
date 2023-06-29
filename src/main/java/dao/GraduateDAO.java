package dao;

import utils.FileManagerUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GraduateDAO extends PersonDAO {

    private final Connection connection;

    public GraduateDAO(Connection connection) {
        super(connection);
        this.connection = connection;
    }

    public int addGraduate(Integer personId, Integer degreeId, Integer qualificationId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FileManagerUtil
                    .getFileAsString("queries/create/insertGraduate.sql"), PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, personId);
            preparedStatement.setInt(2, degreeId);
            preparedStatement.setInt(3, qualificationId);
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

    public ResultSet getGraduateById(Integer graduateId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FileManagerUtil
                    .getFileAsString("queries/read/getGraduateById.sql"));
            preparedStatement.setInt(1, graduateId);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteGraduateById(Integer graduateId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FileManagerUtil
                    .getFileAsString("queries/delete/deleteGraduateById.sql"));
            preparedStatement.setInt(1, graduateId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
