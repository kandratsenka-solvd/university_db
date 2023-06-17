package tests;

import models.*;
import org.jdom2.Document;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.FileManagerUtil;
import utils.OutputUtil;
import utils.XmlUtil;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.List;

public class MappersTest {

    @Test
    public void test1() {
        File xmlFile = new File("src/main/resources/root.xml");
        System.out.println(xmlFile.getAbsolutePath());
        System.out.println(FileManagerUtil.getFilePath("root.xml"));
    }

    @Test
    public void test2() {
        Document document = XmlUtil.generateRootXml();
        OutputUtil.writeXmlToFile(document);
        Assert.assertTrue(XmlUtil.validateXmlAgainstXsd("root.xsd", OutputUtil.xmlDocumentToString(document)));
    }

    @Test
    public void testGroup() throws JAXBException {



        Document document = XmlUtil.convertXmlToDocument("root.xml");


        XmlUtil.validateXmlAgainstXsd("root.xsd", OutputUtil.xmlDocumentToString(document));

        Root root = XmlUtil.xmlToObjectsList();


        List<Person> persons = root.getPersons();
        List<Student> students = root.getStudents();
        List<Graduate> graduates = root.getGraduates();
        List<Applicant> applicants = root.getApplicants();

        System.out.println(persons.get(0).getAddress());

    }

    @Test
    public void testSome() throws JAXBException {
        Document document = XmlUtil.generatePersonXml();
        OutputUtil.writeXmlToFile(document);
//        String xmlString = FileManagerUtil.getFileAsString("person.xml");
        String xmlString = OutputUtil.xmlDocumentToString(document);
        XmlUtil.validateXmlAgainstXsd("person.xsd", xmlString);

        Person p = XmlUtil.xmlToObject(xmlString, Person.class);
//        Person p = XmlUtil.xmlToObject(xmlString, Person.class);
        System.out.println(p.getAddress());
        System.out.println(p.getDateOfBirth());
    }
}
