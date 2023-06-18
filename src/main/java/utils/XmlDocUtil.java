package utils;

import enums.Degree;
import enums.Qualification;
import enums.Title;
import models.Person;
import org.jdom2.Document;
import org.jdom2.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class XmlDocUtil {

    public static Document generatePersonXml() {
        Element personElement = new Element("person");
        Document document = new Document(personElement);
        Person person = PersonUtil.generatePerson();
        personElement.addContent(new Element("titleId").setText(String.valueOf(person.getTitleId())));
        personElement.addContent(new Element("fullName").setText(person.getFullName()));
        personElement.addContent(new Element("dateOfBirth").setText(String.valueOf(person.getDateOfBirth())));
        personElement.addContent(new Element("gender").setText(person.getGender()));
        personElement.addContent(new Element("address").setText(person.getAddress()));
        personElement.addContent(new Element("email").setText(person.getEmail()));
        personElement.addContent(new Element("phoneNumber").setText(person.getPhoneNumber()));
        return document;
    }

    public static Document generateRootXml() {
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
        Element rootElement = new Element("root");
        Document document = new Document(rootElement);
        Person person = PersonUtil.generatePerson();
        Element personElement = new Element("person");
        personElement.addContent(new Element("titleId")
                .setText(String.valueOf(random.nextInt(titleValues.size()))));
        personElement.addContent(new Element("fullName").setText(person.getFullName()));
        personElement.addContent(new Element("dateOfBirth").setText(String.valueOf(person.getDateOfBirth())));
        personElement.addContent(new Element("gender").setText(person.getGender()));
        personElement.addContent(new Element("address").setText(person.getAddress()));
        personElement.addContent(new Element("email").setText(person.getEmail()));
        personElement.addContent(new Element("phoneNumber").setText(person.getPhoneNumber()));
        rootElement.addContent(personElement);

        Element studentElement = new Element("student");
        studentElement.addContent(new Element("personId")
                .setText(String.valueOf(random.nextInt( 1, 10))));
        studentElement.addContent(new Element("eduGroupId")
                .setText(String.valueOf(random.nextInt( 1, 10))));
        rootElement.addContent(studentElement);

        Element graduateElement = new Element("graduate");
        graduateElement.addContent(new Element("personId")
                .setText(String.valueOf(random.nextInt( 1, 10))));
        graduateElement.addContent(new Element("degreeId")
                .setText(String.valueOf(random.nextInt(degreeValues.size()))));
        graduateElement.addContent(new Element("qualificationId")
                .setText(String.valueOf(random.nextInt(qualificationValues.size()))));
        rootElement.addContent(graduateElement);

        Element applicantElement = new Element("applicant");
        applicantElement.addContent(new Element("personId")
                .setText(String.valueOf(random.nextInt( 1, 10))));
        applicantElement.addContent(new Element("courseId")
                .setText(String.valueOf(random.nextInt( 1, 10))));
        rootElement.addContent(applicantElement);
        return document;
    }
}
