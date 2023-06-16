package tests;

import models.Person;
import org.jdom2.Document;
import org.testng.annotations.Test;
import utils.FileManagerUtil;
import utils.OutputUtil;
import utils.XmlUtil;

import javax.xml.bind.JAXBException;

public class MappersTest {

    @Test
    public void testSome() throws JAXBException {
        Document document = XmlUtil.generatePersonXml();
        OutputUtil.writeXmlToFile(document);
        String s = FileManagerUtil.getFileAsString("person.xml");
        XmlUtil.validateXmlAgainstXsd("person.xsd", s);
//        Person p = XmlUtil.xmlToEgz(document, Person.class);
        Person p = XmlUtil.xmlToInstance(s, Person.class);
//        Person p = XmlUtil.xmlToObject(s, Person.class);
        System.out.println(p.getAddress());
        System.out.println(p.getDateOfBirth());
    }
}
