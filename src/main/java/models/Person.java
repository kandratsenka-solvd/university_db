package models;

import lombok.Data;

import java.sql.Date;

@Data
public class Person {
    private Integer titleId;
    private String fullName;
    private Date dateOfBirth;
    private String gender;
    private String address;
    private String email;
    private String phoneNumber;
}
