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
@XmlRootElement(name = "lecturer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Lecturer extends Person {

    private Integer lecturerId;
    private Integer personId;
    private Integer degreeId;

    @JsonCreator
    public Lecturer(@JsonProperty("lecturerId") Integer lecturerId,
                   @JsonProperty("personId") Integer personId,
                   @JsonProperty("degreeId") Integer degreeId) {
        this.lecturerId = lecturerId;
        this.personId = personId;
        this.degreeId = degreeId;
    }
}

