package dao;

import utils.FileManagerUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ApplicantDAO extends PersonDAO {

    private final Connection connection;

    public ApplicantDAO(Connection connection) {
        super(connection);
        this.connection = connection;
    }

    public int addApplicant(Integer personId, Integer courseId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FileManagerUtil
                    .getFileAsString("queries/create/insertApplicant.sql"), PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, personId);
            preparedStatement.setInt(2, courseId);
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

    public ResultSet getApplicantById(Integer applicantId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FileManagerUtil
                    .getFileAsString("queries/read/getApplicantById.sql"));
            preparedStatement.setInt(1, applicantId);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteApplicantById(Integer applicantId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FileManagerUtil
                    .getFileAsString("queries/delete/deleteApplicantById.sql"));
            preparedStatement.setInt(1, applicantId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
