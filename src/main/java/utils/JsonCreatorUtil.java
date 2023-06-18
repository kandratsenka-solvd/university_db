package utils;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import enums.Degree;
import enums.Qualification;
import enums.Title;
import models.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JsonCreatorUtil {
    public static ObjectNode generateRootJson() {
        Random random = new Random();
        List<Integer> degreeValues = new ArrayList<>();
        for (Degree degree : Degree.values()) {
            degreeValues.add(degree.getDegree());
        }
        List<Integer> qualificationValues = new ArrayList<>();
        for (Qualification qualification : Qualification.values()) {
            qualificationValues.add(qualification.getQualification());
        }
        List<Integer> titleValues = new ArrayList<>();
        for (Title title : Title.values()) {
            titleValues.add(title.getTitle());
        }

        ObjectNode rootJson = JsonNodeFactory.instance.objectNode();
        ObjectNode personJson = JsonNodeFactory.instance.objectNode();
        Person person = PersonUtil.generatePerson();
        personJson.put("titleId", random.nextInt(titleValues.size()));
        personJson.put("fullName", person.getFullName());
        personJson.put("dateOfBirth", String.valueOf(person.getDateOfBirth()));
        personJson.put("gender", person.getGender());
        personJson.put("address", person.getAddress());
        personJson.put("email", person.getEmail());
        personJson.put("phoneNumber", person.getPhoneNumber());
        rootJson.set("person", personJson);

        ObjectNode studentJson = JsonNodeFactory.instance.objectNode();
        studentJson.put("personId", random.nextInt(1, 10));
        studentJson.put("eduGroupId", random.nextInt(1, 10));
        rootJson.set("student", studentJson);

        ObjectNode graduateJson = JsonNodeFactory.instance.objectNode();
        graduateJson.put("personId", random.nextInt(1, 10));
        graduateJson.put("degreeId", random.nextInt(degreeValues.size()));
        graduateJson.put("qualificationId", random.nextInt(qualificationValues.size()));
        rootJson.set("graduate", graduateJson);

        ObjectNode applicantJson = JsonNodeFactory.instance.objectNode();
        applicantJson.put("personId", random.nextInt(1, 10));
        applicantJson.put("courseId", random.nextInt(1, 10));
        rootJson.set("applicant", applicantJson);

        return rootJson;
    }
}
