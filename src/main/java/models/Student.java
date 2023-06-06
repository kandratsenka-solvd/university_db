package models;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class Student extends Person {
    private Integer studentId;
    private Integer facultyId;
    private Integer specialtyId;
    private Integer degreeId;
    private Integer groupId;
}
