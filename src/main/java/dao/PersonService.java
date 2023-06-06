package dao;

import connection.ConnectionPool;
import models.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PersonService implements ICommonDAO<Person> {
    @Override
    public void add(Person person) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO person (name, date_of_birth, gender, address, email, phone_number) VALUES (?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, person.getName());
            preparedStatement.setDate(2, person.getDateOfBirth());
            preparedStatement.setString(3, person.getGender());
            preparedStatement.setString(4, person.getAddress());
            preparedStatement.setString(5, person.getEmail());
            preparedStatement.setString(6, person.getPhoneNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
             ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    @Override
    public List<Person> getAll() {
        return null;
    }

    @Override
    public Person getById(int id) {
        return null;
    }

    @Override
    public void remove(Person person) {

    }
}
