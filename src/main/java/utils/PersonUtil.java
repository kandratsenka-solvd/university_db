package utils;

import com.github.javafaker.Faker;
import models.Lecturer;
import models.Person;
import models.Student;


public class PersonUtil {

    private static final Faker faker;

    static {
        faker = new Faker();
    }

    private PersonUtil(){}

    public static Person generatePerson() {
        Person person = new Person();
        person.setTitleId(faker.number().numberBetween(1, 5));
        person.setFullName(faker.name().firstName() + " " +  faker.name().lastName());
        java.util.Date utilDate = faker.date().birthday();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        person.setDateOfBirth(sqlDate);
        person.setGender(faker.demographic().sex());
        person.setAddress(faker.address().streetAddress());
        person.setEmail(faker.internet().emailAddress());
        person.setPhoneNumber(faker.phoneNumber().cellPhone());
        return person;
    }

    public static Student generateStudent(int personId) {
        Student student = new Student();
        student.setPersonId(personId);
        student.setEduGroupId(faker.number().numberBetween(1, 18));
        return student;
    }

    public static Lecturer generateLecturer(int personId) {
        Lecturer lecturer = new Lecturer();
        lecturer.setPersonId(personId);
        lecturer.setDegreeId(faker.number().numberBetween(1, 3));
        return lecturer;
    }
}