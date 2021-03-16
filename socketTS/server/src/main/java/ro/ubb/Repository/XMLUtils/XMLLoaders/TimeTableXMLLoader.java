package ro.ubb.Repository.XMLUtils.XMLLoaders;

import org.w3c.dom.Element;
import ro.ubb.Model.BaseEntity;
import ro.ubb.Model.CustomADT.Pair;
import ro.ubb.Model.TrainsStationsEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeTableXMLLoader<K, V extends BaseEntity<K>> extends XMLLoader<K, V> {
    public TimeTableXMLLoader(String xmlFilePath) {
        super(xmlFilePath);
    }
    @SuppressWarnings("unchecked")
    @Override
    protected V createEntity(Element element) {
        Integer trainId = Integer.parseInt( element.getElementsByTagName("trainId").item(0).getTextContent());
        Integer stationId = Integer.parseInt( element.getElementsByTagName("stationId").item(0).getTextContent());
        Pair<Integer, Integer> id = new Pair<>(trainId, stationId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime arrivalDate = LocalDateTime.parse(element.getElementsByTagName("arrivalDate").item(0).getTextContent(),formatter);
        LocalDateTime departureDate = LocalDateTime.parse(element.getElementsByTagName("departureDate").item(0).getTextContent(),formatter);
        return (V) new TrainsStationsEntity<>(id, arrivalDate, departureDate);
    }
}
