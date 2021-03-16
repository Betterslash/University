package ro.ubb.Model.Validators;

import ro.ubb.Exceptions.StationException;
import ro.ubb.Exceptions.ValidatorException;
import ro.ubb.Model.Station;
import java.util.Optional;

public class StationValidator implements Validator<Station>{
    @Override
    public void validate(Station entity) throws ValidatorException {
        Optional.ofNullable(entity.getId())
                .filter(e -> e > 0)
                .orElseThrow(() ->new StationException("Invalid id!"));
        Optional.ofNullable(entity.getStationName())
                .filter(e -> !e.isEmpty())
                .orElseThrow(() ->new StationException("Invalid station name!"));
        Optional.ofNullable(entity.getPopulationRate())
                .filter(e -> !e.isEmpty())
                .orElseThrow(() ->new StationException("Invalid population rate!"));
    }
}
