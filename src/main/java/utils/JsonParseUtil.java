package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;


public class JsonParseUtil {

    private static final Logger LOGGER = LogManager.getLogger();
    static ObjectMapper objectMapper = new ObjectMapper();

    private JsonParseUtil(){}

    public static <T> T jsonToObject(String strJson, Class<T> clazz) {

        try {
            LOGGER.info(String.format("Creating an instance of the %s from JSON.", clazz));
            return objectMapper.readValue(strJson, clazz);
        } catch (IOException e) {
            LOGGER.error(String.format("Failed when trying to create an instance of the %s", clazz));
            throw new RuntimeException(e);
        }
    }
}
