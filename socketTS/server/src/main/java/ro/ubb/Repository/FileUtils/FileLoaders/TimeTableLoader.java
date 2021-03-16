package ro.ubb.Repository.FileUtils.FileLoaders;

import ro.ubb.Model.Exceptions.LoaderException;
import ro.ubb.Model.CustomADT.Pair;
import ro.ubb.Model.TrainsStationsEntity;
import ro.ubb.Model.Validators.TrainsStationsValidator;
import ro.ubb.Repository.Loader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeTableLoader extends Loader<Pair<Integer, Integer>, TrainsStationsEntity<Integer, Integer>> {
    public TimeTableLoader(String filePath) {
        super(filePath);
    }

    /**
     *
     * @return a map containing all time tables
     * @throws LoaderException
     */
    @Override
    public Map<Pair<Integer, Integer>, TrainsStationsEntity<Integer, Integer>> load() throws LoaderException {
        Path path = Path.of(this.filePath);
        HashMap<Pair<Integer, Integer>, TrainsStationsEntity<Integer, Integer>> timeTables = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try{
        Files.lines(path).forEach(line -> {
            List<String> attrs = Arrays.asList(line.split(", "));
            Pair<Integer, Integer> pair = new Pair<>(Integer.parseInt(attrs.get(0)),Integer.parseInt(attrs.get(1)));
            LocalDateTime arrivalTime = LocalDateTime.parse(attrs.get(2), formatter);
            LocalDateTime departureTime = LocalDateTime.parse(attrs.get(3), formatter);
            new TrainsStationsValidator().validate(new TrainsStationsEntity<>(pair, arrivalTime, departureTime));
            timeTables.put(pair, new TrainsStationsEntity<>(pair, arrivalTime, departureTime));
        });}
        catch (Exception exception){
            System.out.println(exception.getMessage());
            throw new LoaderException("The file couldn't be parsed correctlly!");
        }
        return timeTables ;
    }

}
