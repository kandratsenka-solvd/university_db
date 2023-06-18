package utils;

import org.jdom2.Document;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.FileOutputStream;

public class OutputUtil {

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
}

