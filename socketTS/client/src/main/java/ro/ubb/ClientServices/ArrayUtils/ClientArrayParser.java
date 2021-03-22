package ro.ubb.ClientServices.ArrayUtils;

import ro.ubb.Model.CustomADT.Pair;
import ro.ubb.Model.Station;
import ro.ubb.Model.Train;
import ro.ubb.Model.TrainsStationsEntity;
import ro.ubb.Model.Validators.StationValidator;
import ro.ubb.Model.Validators.TrainValidator;
import ro.ubb.Model.Validators.TrainsStationsValidator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class ClientArrayParser {
    public static Station parseStation(String body) {
        List<String> attrs = Arrays.asList(body.split(", "));
        Integer id = Integer.parseInt(attrs.get(0));
        String name = attrs.get(1);
        String populationRate = attrs.get(2);
        Station station = new Station(id, name, populationRate);
        new StationValidator().validate(station);
        return station;
    }
    public static Train parseTrain(String body) {
        List<String> items = Arrays.asList(body.split(", "));
        Integer id = Integer.parseInt(items.get(0));
        String type = items.get(1);
        String color = items.get(2);
        LocalDate localDate = LocalDate.parse(items.get(3).strip());
        Train train = new Train(id, type, color, localDate);
        new TrainValidator().validate(train);
        return train;
    }
    public TrainsStationsEntity<Integer, Integer> parseTimeTable(String body){
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
