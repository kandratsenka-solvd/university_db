package utils;

import com.github.javafaker.Faker;
import models.Person;
import models.Student;


public final class PersonUtil {

    private static final Faker faker;

    static {
        faker = new Faker();
    }

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

    public static Student generateStudent() {
        Student student = new Student();
        student.setPersonId(1);
        student.setEduGroupId(faker.number().numberBetween(1, 18));
        return student;
    }
}