package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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


public class XmlParseUtil {

    private static final Logger LOGGER = LogManager.getLogger();

    public static boolean validateXmlAgainstXsd(String xsdFileName, String xmlContentString) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        URL url = classloader.getResource(xsdFileName);
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema;
        try {
            LOGGER.info("Validating XML file against [{}] scheme.", xsdFileName);
            schema = schemaFactory.newSchema(new File(String.valueOf(Paths.get(Objects.requireNonNull(url).toURI()))));
        } catch (SAXException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
        Validator validator = schema.newValidator();
        try {
            validator.validate(new StreamSource(new StringReader(xmlContentString)));
            LOGGER.info("True: file corresponds to the scheme.");
            return true;
        } catch (IOException | SAXException e) {
            LOGGER.warn("False: file does not match the scheme.");
            return false;
        }
    }

    public static <T> T xmlToObject(String xmlContent, Class<T> clazz) {
        try {
            LOGGER.info("Creating an instance of the {} from XML.", clazz);
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return clazz.cast(jaxbUnmarshaller.unmarshal(new StringReader(xmlContent)));
        } catch (JAXBException e) {
            LOGGER.error("Failed when deserializing XML to object.");
        }
        return null;
    }
}