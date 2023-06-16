package utils;

import lombok.SneakyThrows;
import models.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;


public class XmlUtil {

    private static final Logger LOGGER = LogManager.getLogger();

    public static void validateXmlAgainstXsd(String fileName, String xmlString) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        URL url = classloader.getResource(fileName);
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema;
        try {
            schema = schemaFactory.newSchema(new File(String.valueOf(Paths.get(Objects.requireNonNull(url).toURI()))));
        } catch (SAXException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
        Validator validator = schema.newValidator();
        try {
            validator.validate(new StreamSource(new StringReader(xmlString)));
        } catch (IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T xmlToInstance(String xmlString, Class<T> clazz) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        StringReader reader = new StringReader(xmlString);
        return clazz.cast(unmarshaller.unmarshal(reader));
    }

    @SneakyThrows
    public static <T> T xmlToObject(String xmlString, Class<T> clazz) {
        SAXBuilder saxBuilder = new SAXBuilder();
        Document document;
        try {
            document = saxBuilder.build(new StringReader(xmlString));
        } catch (JDOMException | IOException e) {
            throw new RuntimeException(e);
        }
        Element rootElement = document.getRootElement();
        T object;
        try {
            object = clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException |
                 NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Element element = rootElement.getChild(fieldName);
            if (element != null) {
                String fieldValue = element.getText();
                Class<?> fieldType = field.getType();
                if (fieldType == Integer.class) {
                    Integer intValue = Integer.valueOf(fieldValue);
                    field.set(object, intValue);
                } else if (fieldType == Date.class) {
                    Date date = Date.valueOf(LocalDate.parse(fieldValue));
                    field.set(object, date);
                } else {
                    try {
                        field.set(object, fieldValue);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return object;
    }


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
}
