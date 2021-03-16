package ro.ubb.Repository.FileUtils.FileLoaders;


import ro.ubb.Model.Exceptions.LoaderException;
import ro.ubb.Model.Station;
import ro.ubb.Model.Validators.StationValidator;
import ro.ubb.Repository.Loader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StationsLoader extends Loader<Integer, Station> {
    public StationsLoader(String filePath) {
        super(filePath);
    }

    /**
     *
     * @return a map containing all stations
     * @throws LoaderException
     */
    @Override
    public Map<Integer, Station> load() throws LoaderException {
        Map<Integer, Station> stations = new HashMap<>();
        Path path = Path.of(this.filePath);
        try{
        Files.lines(path)
                .forEach(line -> {
                    List<String> attrs = Arrays.asList(line.split(", "));
                    Integer id = Integer.parseInt(attrs.get(0));
                    String name = attrs.get(1);
                    String populationRate = attrs.get(2);
                    Station station = new Station(id, name, populationRate);
                    new StationValidator().validate(station);
                    stations.put(id, station);
                });}
        catch (Exception exception){
            throw new LoaderException("The file couldn't be parsed correctlly!");
        }
        return stations;
    }
}
