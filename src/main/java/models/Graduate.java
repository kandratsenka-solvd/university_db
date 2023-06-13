package models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Graduate extends Person {
    private Integer personId;
    private Integer degreeId;
    private Integer qualificationId;
}
