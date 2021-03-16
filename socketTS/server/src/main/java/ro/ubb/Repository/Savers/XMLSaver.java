package ro.ubb.Repository.Savers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import ro.ubb.Model.Exceptions.SaverException;
import ro.ubb.Model.BaseEntity;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Map;

public class XMLSaver<K, T extends BaseEntity<K>> extends AbstractSaver<K, T> {

    public XMLSaver(String filePath) {
        super(filePath);
    }

    @Override
    public void saveToFile(Map<K, T> entities) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(super.filePath);
            document.getDocumentElement().normalize();

            while(document.getDocumentElement().hasChildNodes())
                document.getDocumentElement().removeChild(document.getDocumentElement().getFirstChild());

            Element root = document.getDocumentElement();

            entities.forEach((key, value) -> {
                Node entityNode = value.createNodeFromEntity(document);
                root.appendChild(entityNode);
            });

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            transformer.transform(
                    new DOMSource(document),
                    new StreamResult(new PrintWriter(new FileOutputStream(super.filePath,false)))
            );
        }
        catch (Exception e) {
            throw new SaverException(e.getMessage());
        }
    }
}
