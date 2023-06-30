package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import enums.Degree;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@XmlRootElement(name = "degree")
@XmlAccessorType(XmlAccessType.FIELD)
public class DegreeModel {

    @JsonProperty("degreeId")
    private Integer degreeId;
    @JsonProperty("degreeName")
    private Degree degreeName;
}
