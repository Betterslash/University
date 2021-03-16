package ro.ubb.Repository.XMLUtils.XMLLoaders;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ro.ubb.Model.BaseEntity;
import ro.ubb.Repository.Loader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @param <K> id type
 * @param <V> entity type
 */
public abstract class XMLLoader<K, V extends BaseEntity<K>> extends Loader<K, V> {
    protected String entityName;

    /**
     * @param xmlFilePath instantiates the path to the source file as well as deciding it's name
     */
    protected XMLLoader(String xmlFilePath){
        super(xmlFilePath);
        if(this instanceof TrainsXMLLoader){
            entityName = "train";
        }else if(this instanceof StationsXMLLoader){
            entityName = "station";
        }else if(this instanceof TimeTableXMLLoader){
            entityName = "timetable";
        }
    }

    /**
     * @param element the entity to be transformed into an object inside the application
     * @return the new object created from the input entity
     */
    protected abstract V createEntity(Element element);
    public Map<K, V> load() throws IOException, SAXException, ParserConfigurationException {
        Map<K, V> returnMap = new HashMap<>();
        File sourceFile = new File(this.filePath);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(sourceFile);
        document.getDocumentElement().normalize();
        NodeList nodeList = document.getElementsByTagName(entityName);
        for(int i = 0; i < nodeList.getLength(); i++){
            Node node = nodeList.item(i);
            {
                Element eElement = (Element) node;
                V entity = this.createEntity(eElement);
                returnMap.put(entity.getId(), entity);
            }
        }
        return returnMap;
    }
}
