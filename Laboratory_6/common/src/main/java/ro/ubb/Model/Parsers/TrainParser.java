package ro.ubb.Model.Parsers;

import ro.ubb.Model.Train;
import ro.ubb.Model.Validators.TrainValidator;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class TrainParser implements IParser<Integer, Train> {

    @Override
    public Train parse(String body) {
        List<String> items = Arrays.asList(body.split(", "));
        Integer id = Integer.parseInt(items.get(0));
        String type = items.get(1);
        String color = items.get(2);
        LocalDate localDate = LocalDate.parse(items.get(3).strip());
        Train train = new Train(id, type, color, localDate);
        new TrainValidator().validate(train);
        return train;
    }

    @Override
    public Integer parseID(String body) {
        return Integer.parseInt(body.strip());
    }
}
