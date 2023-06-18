package tests;

import com.fasterxml.jackson.databind.JsonNode;
import models.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jdom2.Document;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.*;

import javax.xml.bind.JAXBException;
import java.util.Objects;

public class ParsersTest {

    private static final Logger LOGGER = LogManager.getLogger();

    @Test
    public void testXmlParser() {
        String xsdSchemaFileName = "root.xsd";
        Document document = XmlDocUtil.generateRootXml();
        String xmlContent = OutputUtil.xmlDocToString(document);
        Assert.assertTrue(XmlParseUtil.validateXmlAgainstXsd(xsdSchemaFileName, xmlContent),
                "XML file does not match the XSD scheme.");
        OutputUtil.writeXmlDocToFile(document);
        Root root = XmlParseUtil.xmlToObjectsList(xmlContent, Root.class);
        Person person = Objects.requireNonNull(root).getPerson();
        Student student = root.getStudent();
        Graduate graduate = root.getGraduate();
        Applicant applicant = root.getApplicant();
        LOGGER.info("Person's name: {}; email: {}", person.getFullName(), person.getEmail());
        LOGGER.info("Student's personId: {}", student.getPersonId());
        LOGGER.info("Graduate's qualificationId: {}", graduate.getQualificationId());
        LOGGER.info("Applicant's courseId: {}", applicant.getCourseId());
    }

    @Test
    public void testJsonParser() {
        JsonNode jsonNode = JsonNodeUtil.generateRootJson();
        String jsonContent = OutputUtil.jsonNodeToString(jsonNode);
        OutputUtil.writeJsonNodeToFile(jsonNode, "root");
        Root root = JsonParseUtil.jsonToClass(jsonContent, Root.class);
        Person person = Objects.requireNonNull(root).getPerson();
        Student student = root.getStudent();
        Graduate graduate = root.getGraduate();
        Applicant applicant = root.getApplicant();
        LOGGER.info("Person's name: {}; email: {}", person.getFullName(), person.getEmail());
        LOGGER.info("Student's personId: {}", student.getPersonId());
        LOGGER.info("Graduate's qualificationId: {}", graduate.getQualificationId());
        LOGGER.info("Applicant's courseId: {}", applicant.getCourseId());
    }

    @Test
    public void testXmlPersonFile() throws JAXBException {
        Document document = XmlDocUtil.generatePersonXml();
        OutputUtil.writeXmlDocToFile(document);
        String xmlContent = OutputUtil.xmlDocToString(document);
        XmlParseUtil.validateXmlAgainstXsd("person.xsd", xmlContent);
        Person p = XmlParseUtil.xmlToObject(xmlContent, Person.class);
        System.out.println(p.getAddress());
        System.out.println(p.getDateOfBirth());
    }
}
