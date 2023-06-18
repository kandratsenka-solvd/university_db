package models;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@NoArgsConstructor
@XmlRootElement(name = "root")
@XmlAccessorType(XmlAccessType.FIELD)
public class Root {

    private Person person;
    private Student student;
    private Graduate graduate;
    private Applicant applicant;
    private Faculty faculty;

    @JsonCreator
    public Root(@JsonProperty("person") Person person,
                @JsonProperty("student") Student student,
                @JsonProperty("graduate") Graduate graduate,
                @JsonProperty("applicant") Applicant applicant,
                @JsonProperty("faculty") Faculty faculty) {
        this.person = person;
        this.student = student;
        this.graduate = graduate;
        this.applicant = applicant;
        this.faculty = faculty;
    }
}



