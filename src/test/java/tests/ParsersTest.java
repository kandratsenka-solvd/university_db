package tests;

import com.fasterxml.jackson.databind.node.ObjectNode;
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
    public void testJsonParser() {
        ObjectNode jsonNode = JsonCreatorUtil.generateRootJson();
        String jsonContent = OutputUtil.jsonToString(jsonNode);
        Root root = JsonUtil.jsonToClass(jsonContent, Root.class);
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
    public void testXmlParser() {
        String xsdSchemaFileName = "root.xsd";
        Document document = XmlDocUtil.generateRootXml();
        String xmlContent = OutputUtil.xmlDocToString(document);
        Assert.assertTrue(XmlUtil.validateXmlAgainstXsd(xsdSchemaFileName, xmlContent),
                "XML file does not match the XSD scheme.");
        OutputUtil.writeXmlDocToFile(document);
        Root root = XmlUtil.xmlToObjectsList(xmlContent, Root.class);
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
    public void testSome() throws JAXBException {
        Document document = XmlDocUtil.generatePersonXml();
        OutputUtil.writeXmlDocToFile(document);
        String xmlContent = OutputUtil.xmlDocToString(document);
        XmlUtil.validateXmlAgainstXsd("person.xsd", xmlContent);

        Person p = XmlUtil.xmlToObject(xmlContent, Person.class);
//        Person p = XmlUtil.xmlToObject(xmlContent, Person.class);
        System.out.println(p.getAddress());
        System.out.println(p.getDateOfBirth());
    }
}
