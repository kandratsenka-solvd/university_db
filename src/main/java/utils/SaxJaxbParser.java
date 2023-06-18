package utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.StringReader;
import java.io.IOException;

public class SaxJaxbParser {
    public static <T> T xmlToObjectsList(String xmlContent, Class<T> clazz) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            SAXBuilder saxBuilder = new SAXBuilder();
            Document jdomDocument = saxBuilder.build(new StringReader(xmlContent));
            org.jdom2.output.DOMOutputter domOutputter = new org.jdom2.output.DOMOutputter();
            org.w3c.dom.Document w3cDocument = domOutputter.output(jdomDocument);
            javax.xml.transform.dom.DOMSource source = new javax.xml.transform.dom.DOMSource(w3cDocument);
            return clazz.cast(jaxbUnmarshaller.unmarshal(source));
        } catch (JAXBException | JDOMException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}