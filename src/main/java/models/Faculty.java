package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@XmlRootElement(name = "faculty")
@XmlAccessorType(XmlAccessType.FIELD)
public class Faculty {

    @JsonProperty("facultyId")
    private Integer facultyId;
    @JsonProperty("facultyName")
    private String facultyName;
}
