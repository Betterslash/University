package ro.ubb.Parsers;

import ro.ubb.Model.CustomADT.Pair;
import ro.ubb.Model.TrainsStationsEntity;
import ro.ubb.Model.Validators.TrainsStationsValidator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class TimeTableParser implements IParser<Pair<Integer, Integer>, TrainsStationsEntity<Integer, Integer>>{
    @Override
    public TrainsStationsEntity<Integer, Integer> parse(String body){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        List<String> attrs = Arrays.asList(body.split(", "));
        Pair<Integer, Integer> pair = new Pair<>(Integer.parseInt(attrs.get(0)),Integer.parseInt(attrs.get(1)));
        LocalDateTime arrivalTime = LocalDateTime.parse(attrs.get(2), formatter);
        LocalDateTime departureTime = LocalDateTime.parse(attrs.get(3).strip(), formatter);
        TrainsStationsEntity<Integer, Integer> ttEntity = new TrainsStationsEntity<>(pair, arrivalTime, departureTime);
        new TrainsStationsValidator().validate(ttEntity);
        return ttEntity;
    }
    @Override
    public Pair<Integer, Integer> parseID(String body) {
        return Pair.parsePair(body.strip());
    }
}
