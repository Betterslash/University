package ro.ubb.Repository.XMLUtils.XMLLoaders;

import org.w3c.dom.Element;
import ro.ubb.Model.BaseEntity;
import ro.ubb.Model.Train;

import java.time.LocalDate;

public class TrainsXMLLoader<K, V extends BaseEntity<K>> extends XMLLoader<K, V> {
    public TrainsXMLLoader(String xmlFilePath) {
        super(xmlFilePath);
    }
    @Override
    @SuppressWarnings("unchecked")
    protected V createEntity(Element element){
        Integer id = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
        String type = element.getElementsByTagName("type").item(0).getTextContent();
        String color = element.getElementsByTagName("color").item(0).getTextContent();
        LocalDate localDate = LocalDate.parse(element.getElementsByTagName("fabricationDate").item(0).getTextContent());
        Train train = new Train(id, type, color, localDate);
        return (V)train;
    }
}
