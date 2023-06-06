package utils;

import connection.DbConnectionUtil;
import models.NumberOne;
import models.Student;

import java.sql.*;

public class DbQueryUtil {

    public static void addPersonToDB() {
        try {
            Student student = PersonUtil.generateStudent();
            Connection connection = DbConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO person (name, date_of_birth, gender, address, email, phone_number) VALUES (?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, student.getName());
            preparedStatement.setDate(2, student.getDateOfBirth());
            preparedStatement.setString(3, student.getGender());
            preparedStatement.setString(4, student.getAddress());
            preparedStatement.setString(5, student.getEmail());
            preparedStatement.setString(6, student.getPhoneNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addStudentToDB() {
        try {
            Student student = PersonUtil.generateStudent();
            Connection connection = DbConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO students (person_id, faculty_id, specialty_id, level_id, group_id) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, 1);
            preparedStatement.setInt(2, student.getFacultyId());
            preparedStatement.setInt(3, student.getSpecialtyId());
            preparedStatement.setInt(4, student.getDegreeId());
            preparedStatement.setInt(5, student.getGroupId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addSomeToDB() {
        try {
            NumberOne numberOne = new NumberOne();
            Connection connection = DbConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO test1 (title) VALUES (?)");
            preparedStatement.setString(1, "this is a title");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
