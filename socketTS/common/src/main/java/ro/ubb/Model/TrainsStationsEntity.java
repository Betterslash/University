package ro.ubb.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import ro.ubb.Model.CustomADT.Pair;
import ro.ubb.Model.Validators.TrainsStationsValidator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class TrainsStationsEntity<T, T1>  extends BaseEntity<Pair<T, T1>>{
    private final Pair<T, T1> Id;
    private final LocalDateTime arrivalTime;
    private final LocalDateTime departureTime;

    public TrainsStationsEntity(Pair<T, T1> representation, LocalDateTime arrivalTime, LocalDateTime departureTime)
    {
        this.Id = representation;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }

    @Override
    public String csvFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String arrival=this.arrivalTime.format(formatter);
        String departure=this.departureTime.format(formatter);
        return this.Id.getFirst().toString() +", "+this.Id.getLast().toString() +", "+arrival +", "+departure + "\n";
    }



    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String arrival=this.arrivalTime.format(formatter);
        String departure=this.departureTime.format(formatter);
        return "TrainsStations{" +
                "ID:(TrainID: "+this.Id.getFirst().toString() + ", StationID: "+this.Id.getLast().toString() +"), "+
                "Arrival time: "+arrival + ", "+
                "Departure time: "+departure + "}";
    }

    @Override
    public Node createNodeFromEntity(Document document) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String arrival=this.arrivalTime.format(formatter);
        String departure=this.departureTime.format(formatter);

        Element timetableElement = document.createElement("timetable");

        Element childElement1=document.createElement("trainId");
        childElement1.setTextContent(String.valueOf(this.Id.getFirst()));
        timetableElement.appendChild(childElement1);

        Element childElement2=document.createElement("stationId");
        childElement2.setTextContent(String.valueOf(this.Id.getLast()));
        timetableElement.appendChild(childElement2);

        Element childElement3=document.createElement("arrivalDate");
        childElement3.setTextContent(arrival);
        timetableElement.appendChild(childElement3);

        Element childElement4=document.createElement("departureDate");
        childElement4.setTextContent(departure);
        timetableElement.appendChild(childElement4);

        return timetableElement;
    }
    public static TrainsStationsEntity<Integer, Integer> parseTimeTable(String body){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        List<String> attrs = Arrays.asList(body.split(", "));
        Pair<Integer, Integer> pair = new Pair<>(Integer.parseInt(attrs.get(0)),Integer.parseInt(attrs.get(1)));
        LocalDateTime arrivalTime = LocalDateTime.parse(attrs.get(2), formatter);
        LocalDateTime departureTime = LocalDateTime.parse(attrs.get(3).strip(), formatter);
        TrainsStationsEntity<Integer, Integer> ttEntity = new TrainsStationsEntity<>(pair, arrivalTime, departureTime);
        new TrainsStationsValidator().validate(ttEntity);
        return ttEntity;

    }
}
