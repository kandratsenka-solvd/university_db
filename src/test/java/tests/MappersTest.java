package tests;

import models.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jdom2.Document;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.OutputUtil;
import utils.XmlDocUtil;
import utils.XmlUtil;

import javax.xml.bind.JAXBException;
import java.util.List;
import java.util.Objects;

public class MappersTest {

    private static final Logger LOGGER = LogManager.getLogger();

    @Test
    public void test1() {

    }

    @Test
    public void test2() {
        Document document = XmlDocUtil.generateRootXml();
        OutputUtil.writeXmlDocToFile(document);
        Assert.assertTrue(XmlUtil.validateXmlAgainstXsd("root.xsd", OutputUtil.xmlDocToString(document)));
    }

    @Test
    public void testGroup() {
        String xsdSchemaFileName = "root.xsd";
        Document document = XmlDocUtil.generateRootXml();
        String xmlContent = OutputUtil.xmlDocToString(document);
        Assert.assertTrue(XmlUtil.validateXmlAgainstXsd(xsdSchemaFileName, xmlContent));
        OutputUtil.writeXmlDocToFile(document);
        Root root = XmlUtil.xmlToObjectsList(xmlContent, Root.class);
        List<Person> persons = Objects.requireNonNull(root).getPersons();
        List<Student> students = root.getStudents();
        List<Graduate> graduates = root.getGraduates();
        List<Applicant> applicants = root.getApplicants();
        LOGGER.info("Person's name: {}; email: {}", persons.get(0).getFullName(), persons.get(0).getEmail());
        LOGGER.info("Student's personId: {}", students.get(0).getPersonId());
        LOGGER.info("Graduate's qualificationId: {}", graduates.get(0).getQualificationId());
        LOGGER.info("Applicant's courseId: {}", applicants.get(0).getCourseId());
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
