package factories;

import com.github.javafaker.Faker;
import models.Lecturer;
import models.Person;
import models.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class PersonFactory {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final Faker faker;

    static {
        faker = new Faker();
    }

    public static Person get(String personType, Integer personId) {
        return switch (personType.toLowerCase()) {
            case "person" -> generatePerson(personId);
            case "student" -> generateStudent(personId);
            case "lecturer" -> generateLecturer(personId);
            default -> {
                LOGGER.error("Invalid person type: " + personType);
                throw new IllegalArgumentException();
            }
        };
    }

    private static Person generatePerson(int personId) {
        Person person = new Person();
        person.setPersonId(personId);
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

    private static Student generateStudent(int personId) {
        Student student = new Student();
        student.setPersonId(personId);
        student.setEduGroupId(faker.number().numberBetween(1, 18));
        return student;
    }

    private static Lecturer generateLecturer(int personId) {
        Lecturer lecturer = new Lecturer();
        lecturer.setPersonId(personId);
        lecturer.setDegreeId(faker.number().numberBetween(1, 3));
        return lecturer;
    }
}