package utils;

import com.github.javafaker.Faker;
import models.Student;

public final class PersonUtil {

    private static final Faker faker;

    static {
        faker = new Faker();
    }

    public static Student generateStudent() {
        Student student = new Student();
        student.setName(faker.name().fullName());
        java.util.Date utilDate = faker.date().birthday();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        student.setDateOfBirth(sqlDate);
        student.setGender(faker.options().option("Male", "Female"));
        student.setAddress(faker.address().streetAddress());
        student.setEmail(faker.internet().emailAddress());
        student.setPhoneNumber(faker.phoneNumber().cellPhone());
        student.setFacultyId(faker.number().numberBetween(1, 17));
        student.setSpecialtyId(faker.number().numberBetween(1, 51));
        student.setDegreeId(faker.number().numberBetween(1, 4));
        student.setGroupId(faker.number().numberBetween(1, 51));
        return student;
    }
}