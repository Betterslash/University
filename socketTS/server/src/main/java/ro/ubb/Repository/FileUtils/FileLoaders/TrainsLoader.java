package ro.ubb.Repository.FileUtils.FileLoaders;


import ro.ubb.Model.Exceptions.LoaderException;
import ro.ubb.Model.Train;
import ro.ubb.Model.Validators.TrainValidator;
import ro.ubb.Repository.Loader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrainsLoader extends Loader<Integer, Train> {
    public TrainsLoader(String filePath) {
        super(filePath);
    }

    /**
     *
     * @return a map containing all trains
     * @throws LoaderException
     */
    public Map<Integer, Train> load() throws LoaderException {
        Path path = Paths.get(this.filePath);
        HashMap<Integer, Train> trains = new HashMap<>();
        try{
        Files.lines(path).forEach(
                line ->{
                    List<String> items = Arrays.asList(line.split(", "));
                    Integer id = Integer.parseInt(items.get(0));
                    String type = items.get(1);
                    String color = items.get(2);
                    LocalDate localDate = LocalDate.parse(items.get(3));
                    Train train = new Train(id, type, color, localDate);
                    new TrainValidator().validate(train);
                    trains.put(train.getId(),train);
                }
        );}
        catch (Exception exception){
            throw new LoaderException("The file couldn't be parsed correctlly!");
        }
        return trains;
    }
}
