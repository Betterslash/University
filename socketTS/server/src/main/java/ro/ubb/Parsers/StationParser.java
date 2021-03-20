package ro.ubb.Parsers;

import ro.ubb.Model.Station;
import ro.ubb.Model.Validators.StationValidator;

import java.util.Arrays;
import java.util.List;

public class StationParser implements IParser<Integer, Station> {
    @Override
    public Station parse(String body) {
        List<String> attrs = Arrays.asList(body.split(", "));
        Integer id = Integer.parseInt(attrs.get(0));
        String name = attrs.get(1);
        String populationRate = attrs.get(2);
        Station station = new Station(id, name, populationRate);
        new StationValidator().validate(station);
        return station;
    }

    @Override
    public Integer parseID(String id) {
        return Integer.parseInt(id.strip());
    }
}
