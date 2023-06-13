package models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Applicant extends Person {
    private Integer personId;
    private Integer courseId;
}
