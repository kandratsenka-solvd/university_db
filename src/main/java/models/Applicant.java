package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@EqualsAndHashCode(callSuper = true)
@XmlRootElement(name = "applicant")
@XmlAccessorType(XmlAccessType.FIELD)
public class Applicant extends Person {

    private Integer personId;
    private Integer courseId;

    public Applicant(@JsonProperty("personId") Integer personId,
                     @JsonProperty("courseId") Integer courseId) {
        this.personId = personId;
        this.courseId = courseId;
    }
}

