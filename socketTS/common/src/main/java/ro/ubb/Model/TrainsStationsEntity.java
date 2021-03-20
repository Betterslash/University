package ro.ubb.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ro.ubb.Model.CustomADT.Pair;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    /**
     *
     * @returns the CSV-File-Format of the entity
     */
    @Override
    public String csvFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String arrival=this.arrivalTime.format(formatter);
        String departure=this.departureTime.format(formatter);
        return this.Id.getFirst().toString() +", "+this.Id.getLast().toString() +", "+arrival +", "+departure + "\n";
    }

    /**
     *
     * @returns the string representation
     */
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
}
