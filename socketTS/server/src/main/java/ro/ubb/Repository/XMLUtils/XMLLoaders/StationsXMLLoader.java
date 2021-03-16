package ro.ubb.Repository.XMLUtils.XMLLoaders;

import org.w3c.dom.Element;
import ro.ubb.Model.BaseEntity;
import ro.ubb.Model.Station;

public class StationsXMLLoader<K, V extends BaseEntity<K>> extends XMLLoader<K, V> {
    public StationsXMLLoader(String xmlFilePath) {
        super(xmlFilePath);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected V createEntity(Element element) {
        Integer id = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
        String name = element.getElementsByTagName("name").item(0).getTextContent();
        String populationRate = element.getElementsByTagName("populationRate").item(0).getTextContent();
        Station station = new Station(id, name, populationRate);
        return (V)station;
    }
}
