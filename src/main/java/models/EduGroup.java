package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@XmlRootElement(name = "edu_group")
@XmlAccessorType(XmlAccessType.FIELD)
public class EduGroup {

    @JsonProperty("eduGroupId")
    private Integer eduGroupId;
    @JsonProperty("groupName")
    private String groupName;
    @JsonProperty("degreeId")
    private Integer degreeId;
    @JsonProperty("departmentId")
    private Integer departmentId;
}
