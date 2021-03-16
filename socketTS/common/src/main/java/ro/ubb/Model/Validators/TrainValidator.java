package ro.ubb.Model.Validators;

import ro.ubb.Model.Exceptions.TrainException;
import ro.ubb.Model.Train;

import java.time.LocalDate;
import java.util.Optional;

public class TrainValidator implements Validator<Train>{
    private static boolean checkDate(LocalDate givenDate){
        LocalDate currentDate = LocalDate.now();
        return currentDate.compareTo(givenDate) > 0;
    }
    @Override
    public void validate(Train entity) throws TrainException {
        Optional.ofNullable(entity.getId())
                .filter(e -> e > 0)
                .orElseThrow(() ->new TrainException("Invalid id!"));
        Optional.ofNullable(entity.getTrainType())
                .filter(e -> e.length() >= 2)
                .orElseThrow(() ->new TrainException("Invalid type!"));
        Optional.ofNullable(entity.getTrainColor())
                .filter(e -> e.length() >= 2)
                .orElseThrow(() ->new TrainException("Invalid color!"));
        Optional.ofNullable(entity.getDate())
                .filter(TrainValidator::checkDate)
                .orElseThrow(() ->new TrainException("Invalid date!"));
    }
}
