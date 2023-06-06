package models;

import lombok.Data;

import java.sql.Date;

@Data
public class Person {
    private String name;
    private Date dateOfBirth;
    private String gender;
    private String address;
    private String email;
    private String phoneNumber;
}
