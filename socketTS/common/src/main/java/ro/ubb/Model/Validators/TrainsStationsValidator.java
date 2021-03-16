package ro.ubb.Model.Validators;

import ro.ubb.Model.Exceptions.DomainException;
import ro.ubb.Model.Exceptions.ValidatorException;
import ro.ubb.Model.TrainsStationsEntity;

import java.util.Optional;

public class TrainsStationsValidator implements Validator<TrainsStationsEntity<Integer, Integer>>{
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
        //Set<Integer> trainIDs = TrainService.getEntitiesIds();
        //Set<Integer> stationIDs = StationService.getEntitiesIds();
     /*   Optional.ofNullable(entity.getId())
                .filter(e -> trainIDs.contains(e.getFirst()))
                .filter(e -> stationIDs.contains(e.getLast()))
                .orElseThrow(() -> new DomainException("The ids of trains and stations must exist!"));
   */ }
}
