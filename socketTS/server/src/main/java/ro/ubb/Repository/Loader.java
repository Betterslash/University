package ro.ubb.Repository;

import org.xml.sax.SAXException;
import ro.ubb.Model.Exceptions.LoaderException;
import ro.ubb.Model.BaseEntity;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Map;

/**
 * @param <K> ID type
 * @param <V> entity type
 */
public abstract class Loader<K, V extends BaseEntity<K>>{
    public String filePath;
    public Loader(String filePath){this.filePath = filePath;}

    /**
     * @return an adt to as a result of itterating through a file and loading its objects
     * @throws IOException in case of reading or parsing errors
     * @throws SAXException in case of XML parsing errors
     * @throws ParserConfigurationException in case of XML parsing error
     * @throws LoaderException in case of any load related error
     */
    public abstract Map<K,V> load() throws IOException, SAXException, ParserConfigurationException, LoaderException;
}
