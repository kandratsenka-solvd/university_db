package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student extends Person {

    private Integer studentId;
    private Integer personId;
    private Integer eduGroupId;

    @JsonCreator
    public Student(@JsonProperty("studentId") Integer studentId,
                   @JsonProperty("personId") Integer personId,
                   @JsonProperty("eduGroupId") Integer eduGroupId) {
        this.studentId = studentId;
        this.personId = personId;
        this.eduGroupId = eduGroupId;
    }
}

