package ro.ubb.Repository.Savers;

import org.xml.sax.SAXException;
import ro.ubb.Model.BaseEntity;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.Map;

public abstract class AbstractSaver<K, T extends BaseEntity<K>> {
    protected final String filePath;
    protected AbstractSaver(String filePath){ this.filePath = filePath;}
    public abstract void saveToFile(Map<K, T> entities) throws IOException, TransformerException, ParserConfigurationException, SAXException;
}
