package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.jdom2.Document;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.FileOutputStream;
import java.io.IOException;

public class OutputUtil {

    private OutputUtil(){}

    public static void writeXmlDocToFile(Document document) {
        XMLOutputter outPutter = new XMLOutputter(Format.getPrettyFormat());
        try {
            outPutter.output(document, new FileOutputStream(document.getRootElement().getName() + ".xml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String xmlDocToString(Document document) {
        XMLOutputter outPutter = new XMLOutputter(Format.getPrettyFormat());
        try {
            return outPutter.outputString(document);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeJsonNodeToFile(JsonNode jsonNode, String filename) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            String json = objectMapper.writeValueAsString(jsonNode);
            FileOutputStream outputStream = new FileOutputStream(filename + ".json");
            outputStream.write(json.getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String jsonNodeToString(JsonNode jsonNode) {
        return jsonNode.toPrettyString();
    }
}