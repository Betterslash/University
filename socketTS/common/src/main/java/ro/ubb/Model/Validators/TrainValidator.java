package ro.ubb.Model.Validators;

import ro.ubb.Model.Exceptions.DomainExceptions.TrainException;
import ro.ubb.Model.Train;

import java.time.LocalDate;
import java.util.Optional;

public class TrainValidator implements Validator<Train>{

    /**
     * compares the current date with a specified one
     * @param givenDate
     * @returns a boolean
     */
    private static boolean checkDate(LocalDate givenDate){
        LocalDate currentDate = LocalDate.now();
        return currentDate.compareTo(givenDate) > 0;
    }

    /**
     * validates an entity of type Train
     * @param entity
     * @throws TrainException
     */
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
