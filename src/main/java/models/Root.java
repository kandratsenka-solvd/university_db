package models;
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

    @XmlElement(name = "person")
    private List<Person> persons;
    @XmlElement(name = "student")
    private List<Student> students;
    @XmlElement(name = "graduate")
    private List<Graduate> graduates;
    @XmlElement(name = "applicant")
    private List<Applicant> applicants;
}



