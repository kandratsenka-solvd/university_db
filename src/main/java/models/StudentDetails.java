package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class StudentDetails {
    private Integer studentId;
    private String fullName;
    private String email;
    private String groupName;

    @JsonCreator
    public StudentDetails(@JsonProperty("studentId") Integer studentId,
                          @JsonProperty("fullName") String fullName,
                          @JsonProperty("email") String email,
                          @JsonProperty("groupName") String groupName) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.email = email;
        this.groupName = groupName;
    }
}
