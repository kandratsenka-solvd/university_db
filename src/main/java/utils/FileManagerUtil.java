package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileManagerUtil {

    private static final Logger LOGGER = LogManager.getLogger();

    private FileManagerUtil(){}

    public static String getValue(String fileName, String key) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            LOGGER.info("Getting the [{}] value from the file [{}]", key, fileName);
            JsonNode rootNode = mapper.readTree(getFileAsString(fileName));
            JsonNode valueNode = rootNode.get(key);
            if (valueNode != null) {
                return valueNode.asText();
            }
        } catch (IOException e) {
            LOGGER.error("Error when reading JSON file: " + fileName);
            e.printStackTrace();
        }
        return null;
    }

    public static String getFileAsString(String fileName) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        URL url = classloader.getResource(fileName);
        if (url == null) {
            throw new IllegalArgumentException("Url is null, check the FilePath.");
        }
        try {
            LOGGER.info("Getting resource file as string.");
            return Files.readString(Paths.get(url.toURI()));
        } catch (IOException | URISyntaxException e) {
            LOGGER.error("Error when getting resource file as string.");
            throw new RuntimeException(e);
        }
    }
}