package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@EqualsAndHashCode(callSuper = true)
@XmlRootElement(name = "graduate")
@XmlAccessorType(XmlAccessType.FIELD)
public class Graduate extends Person {

    private Integer personId;
    private Integer degreeId;
    private Integer qualificationId;

    public Graduate(@JsonProperty("personId") Integer personId,
                    @JsonProperty("degreeId") Integer degreeId,
                    @JsonProperty("qualificationId") Integer qualificationId) {
        this.personId = personId;
        this.degreeId = degreeId;
        this.qualificationId = qualificationId;
    }
}

