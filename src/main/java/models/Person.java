package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Data
@NoArgsConstructor
@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {
    private Integer titleId;
    private String fullName;
    private Date dateOfBirth;
    private String gender;
    private String address;
    private String email;
    private String phoneNumber;

    public Person(@JsonProperty("titleId") Integer titleId,
                  @JsonProperty("fullName") String fullName,
                  @JsonProperty("dateOfBirth") Date dateOfBirth,
                  @JsonProperty("gender") String gender,
                  @JsonProperty("address") String address,
                  @JsonProperty("email") String email,
                  @JsonProperty("phoneNumber") String phoneNumber) {
        this.titleId = titleId;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
