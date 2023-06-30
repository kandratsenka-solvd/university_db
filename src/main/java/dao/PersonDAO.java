package dao;

import connection.ConnectionPool;
import models.Person;
import utils.FileManagerUtil;

import java.sql.*;


public class PersonDAO implements ICommonDAO<Person> {

    private final Connection connection;

    public PersonDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int add(Person person) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FileManagerUtil
                    .getFileAsString("queries/create/insertPerson.sql"), PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, person.getTitleId());
            preparedStatement.setString(2, person.getFullName());
            preparedStatement.setDate(3, (Date) person.getDateOfBirth());
            preparedStatement.setString(4, person.getGender());
            preparedStatement.setString(5, person.getAddress());
            preparedStatement.setString(6, person.getEmail());
            preparedStatement.setString(7, person.getPhoneNumber());
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

    @Override
    public ResultSet getAll() {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            preparedStatement = connection.prepareStatement(FileManagerUtil
                    .getFileAsString("queries/read/getAllPerson.sql"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ConnectionPool.getInstance().returnConnection(connection);
        return resultSet;
    }

    @Override
    public Person getById(int personId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FileManagerUtil
                    .getFileAsString("queries/read/getPersonById.sql"));
            preparedStatement.setInt(1, personId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Person person = new Person();
                person.setTitleId(resultSet.getInt("title_id"));
                person.setFullName(resultSet.getString("full_name"));
                person.setDateOfBirth(resultSet.getDate("date_of_birth"));
                person.setGender(resultSet.getString("gender"));
                person.setAddress(resultSet.getString("address"));
                person.setEmail(resultSet.getString("email"));
                person.setPhoneNumber(resultSet.getString("phone_number"));
                return person;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int personId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FileManagerUtil
                    .getFileAsString("queries/delete/deletePersonById.sql"));
            preparedStatement.setInt(1, personId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
