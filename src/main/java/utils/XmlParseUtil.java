package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jdom2.Document;
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
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Objects;

import org.jdom2.input.SAXBuilder;

public class XmlParseUtil {

    private static final Logger LOGGER = LogManager.getLogger();

    public static boolean validateXmlAgainstXsd(String fileName, String xmlString) {
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
            return true;
        } catch (IOException | SAXException e) {
            return false;
        }
    }

    public static <T> T xmlToObject(String xmlContent, Class<T> clazz) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        StringReader reader = new StringReader(xmlContent);
        return clazz.cast(unmarshaller.unmarshal(reader));
    }

    public static <T> T xmlToObjectsList(String xmlContent, Class<T> clazz) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return clazz.cast(jaxbUnmarshaller.unmarshal(new StringReader(xmlContent)));
        } catch (JAXBException e) {
            LOGGER.error("Failed when deserializing XML to objects list.");
        }
        return null;
    }

    public static Document convertXmlToDocument(String fileName) {
        try {
//            File xmlFile = new File(FileManagerUtil.getFilePath(fileName));
            File xmlFile = new File("src/main/resources/" + fileName);
            SAXBuilder builder = new SAXBuilder();
            return builder.build(xmlFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    @SneakyThrows
//    public static <T> T xmlToObj(String xmlString, Class<T> clazz) {
//        SAXBuilder saxBuilder = new SAXBuilder();
//        Document document;
//        try {
//            document = saxBuilder.build(new StringReader(xmlString));
//        } catch (JDOMException | IOException e) {
//            throw new RuntimeException(e);
//        }
//        Element rootElement = document.getRootElement();
//        T object;
//        try {
//            object = clazz.getDeclaredConstructor().newInstance();
//        } catch (InstantiationException | IllegalAccessException |
//                 NoSuchMethodException | InvocationTargetException e) {
//            throw new RuntimeException(e);
//        }
//        Field[] fields = clazz.getDeclaredFields();
//        for (Field field : fields) {
//            field.setAccessible(true);
//            String fieldName = field.getName();
//            Element element = rootElement.getChild(fieldName);
//            if (element != null) {
//                String fieldValue = element.getText();
//                Class<?> fieldType = field.getType();
//                if (fieldType == Integer.class) {
//                    Integer intValue = Integer.valueOf(fieldValue);
//                    field.set(object, intValue);
//                } else if (fieldType == Date.class) {
//                    Date date = Date.valueOf(LocalDate.parse(fieldValue));
//                    field.set(object, date);
//                } else {
//                    try {
//                        field.set(object, fieldValue);
//                    } catch (IllegalAccessException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//            }
//        }
//        return object;
//    }
}
