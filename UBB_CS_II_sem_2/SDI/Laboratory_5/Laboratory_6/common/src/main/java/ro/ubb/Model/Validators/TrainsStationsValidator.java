package ro.ubb.Model.Validators;

import ro.ubb.Model.Exceptions.DomainExceptions.DomainException;
import ro.ubb.Model.Exceptions.ValidatorException;
import ro.ubb.Model.TrainsStationsEntity;

import java.util.Optional;

public class TrainsStationsValidator implements Validator<TrainsStationsEntity<Integer, Integer>>{

    /**
     * validates an entity of type TimeTable
     * @param entity
     * @throws ValidatorException
     */
    @Override
    public void validate(TrainsStationsEntity<Integer, Integer> entity) throws ValidatorException {
        Optional.ofNullable(entity.getId())
                .filter(e-> e.getFirst() > 0)
                .filter(e-> e.getLast() > 0)
                .orElseThrow(() ->new DomainException("Invalid ID's"));

        Optional.ofNullable(entity.getArrivalTime())
                .filter(e-> e.compareTo(entity.getDepartureTime()) < 0)
                .orElseThrow(() ->new DomainException("Invalid Arrival Date"));

        Optional.ofNullable(entity.getDepartureTime())
                .filter(e-> e.compareTo(entity.getArrivalTime()) > 0)
                .orElseThrow(() ->new DomainException("Invalid Departure Date"));
     }
}
