package utils;

import models.Person;
import java.util.Date;

public class PersonBuilder {

    private Integer personId;
    private Integer titleId;
    private String fullName;
    private Date dateOfBirth;
    private String gender;
    private String address;
    private String email;
    private String phoneNumber;

    public PersonBuilder setPersonId(Integer personId) {
        this.personId = personId;
        return this;
    }

    public PersonBuilder setTitleId(Integer titleId) {
        this.titleId = titleId;
        return this;
    }

    public PersonBuilder setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public PersonBuilder setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public PersonBuilder setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public PersonBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public PersonBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public PersonBuilder setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Person build() {
        Person person = new Person();
        person.setPersonId(personId);
        person.setTitleId(titleId);
        person.setFullName(fullName);
        person.setDateOfBirth(dateOfBirth);
        person.setGender(gender);
        person.setAddress(address);
        person.setEmail(email);
        person.setPhoneNumber(phoneNumber);
        return person;
    }
}