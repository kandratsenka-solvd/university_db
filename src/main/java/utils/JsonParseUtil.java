package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;


public class JsonParseUtil {

    private static final Logger LOGGER = LogManager.getLogger();
    static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T jsonToClass(String strJson, Class<T> clazz) {

        try {
            LOGGER.info(String.format("Creating an instance of the %s from a json file", clazz));
            return objectMapper.readValue(strJson, clazz);
        } catch (IOException e) {
            LOGGER.error(String.format("Error when trying to create an instance of the %s", clazz));
            throw new RuntimeException(e);
        }
    }
}
