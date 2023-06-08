package models;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class Student extends Person {
    private Integer personId;
    private Integer groupId;
}
