import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class XmlTest {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse(new File("D:\\yonyou\\git\\occ-2.3.0-930sp\\occ-2.3.0\\occ-stock-web\\src\\main\\resources\\dataTransform\\SaleOutToRequiredIn.xml"));
        NodeList display = document.getElementsByTagName("display");
        int length = display.getLength();
        for (int i = 0; i < length; i++) {
            Node item = display.item(i);
            NamedNodeMap attributes = item.getAttributes();
            int attributesLength = attributes.getLength();
            for (int j = 0; j < attributesLength; j++) {
                Node node = attributes.item(j);
                System.out.println(node);
            }
        }
    }
}
