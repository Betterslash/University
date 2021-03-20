package ro.ubb.Services;

import ro.ubb.Model.CustomADT.Pair;
import ro.ubb.Model.Exceptions.ServiceExceptions.TrainServiceException;
import ro.ubb.Model.Exceptions.ValidatorException;
import ro.ubb.Model.Station;
import ro.ubb.Model.Train;
import ro.ubb.Model.TrainsStationsEntity;
import ro.ubb.Repository.IRepository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 *
 */
public class TrainsStationsService implements Service<Pair<Integer, Integer>, TrainsStationsEntity<Integer, Integer>>
{
    //TODO - MULTIPLE REPOSITORIES
    private static IRepository<Pair<Integer, Integer>, TrainsStationsEntity<Integer, Integer>> repository = null;
    public TrainsStationsService(IRepository<Pair<Integer, Integer>, TrainsStationsEntity<Integer, Integer>> repository)
    {
        TrainsStationsService.repository = repository;
    }

    /**
     * @param trainsStationsEntity adds a route in the repository
     */
    public void executeCreate(TrainsStationsEntity<Integer, Integer> trainsStationsEntity){
        Set<Integer> trainIDs=TrainService.getEntitiesIds();
        Set<Integer> stationIDs=StationService.getEntitiesIds();
        Optional.ofNullable(trainsStationsEntity.getId())
                .filter(e -> trainIDs.contains(e.getFirst()))
                .filter(e -> stationIDs.contains(e.getLast()))
                .orElseThrow(() -> new ValidatorException("ID of train and station must be valid!"));
        repository.save(trainsStationsEntity);
    }

    /**
     *
     * @param object updates an object in the repository

     */
    @Override
    public void executeUpdate(TrainsStationsEntity<Integer, Integer> object){
        repository.update(object);
    }

    /**
     * @param id the id of the route that will be deleted
     *
     */
    @Override
    public void executeDelete(Pair<Integer, Integer> id) {
        repository.delete(id);
    }

    /**
     *
     * @returns a set of all routes
     */
    public Set<TrainsStationsEntity<Integer, Integer>> getAllEntities() {
        Iterable<TrainsStationsEntity<Integer, Integer>> trainsAndStations = repository.findAll();
        return StreamSupport.stream(trainsAndStations.spliterator(), false).collect(Collectors.toSet());
    }

    /**
     * @returns the most visited station id
     */
    public Set<Station> getMostTraveledStation() {
        Map.Entry<Integer, Integer> maxEntry = initializeMap().entrySet()
                .stream()
                .max(Map.Entry.comparingByValue()).orElseThrow(() -> new TrainServiceException("Couldn't get most travelled stations!"));
        Integer mostTraveledStationID = maxEntry.getKey();
        Set<Station> mostTraveledStationSet = StationService.getStations().stream()
                                                    .filter(e-> e.getId().equals(mostTraveledStationID))
                                                    .collect(Collectors.toSet());
        return mostTraveledStationSet;
    }

    /**
     * initializes a map
     * @returns a map(key, value) which has the StationID as key and the number of trains that pass the station as value
     */
    private Map<Integer, Integer> initializeMap() {
        Map<Integer, Integer> freqArray = new HashMap<>();
        repository.findAll().forEach(e -> freqArray.put(e.getId().getLast(), 0));
        repository.findAll().forEach(e ->
            {Integer currentValue = freqArray.get(e.getId().getLast());
            currentValue += 1;
            freqArray.put(e.getId().getLast(), currentValue);});
        return freqArray;
    }

    /**
     *
     * @param numberOfTrains number of trains to be checked
     * @returns the set of StationIDs which are passed by every train
     */
    public Set<Integer> getStationsPassedByNumberOfTrain(Integer numberOfTrains) {
        Map<Integer, Integer> freqArr = initializeMap();
        return freqArr.keySet()
                .stream()
                .filter(integer -> (freqArr.get(integer).equals(numberOfTrains)))
                .collect(Collectors.toSet());
    }

    /**
     *
     * @returns the set of Stations which are passed by every train
     */
    public Set<Station> getStationsPassedByEveryTrain()
    {
        Set<Integer> stationIDs = this.getStationsPassedByNumberOfTrain(TrainService.getTrains().size());
        Set<Station> result = new HashSet<>();
        StationService.getStations().forEach(e -> {if (stationIDs.contains(e.getId())) result.add(e);});
        return result;
    }

    /**
     *
     * @param stationNumber stations to be checked
     * @returns the set of TrainIDs which pass every station
     */
    public Set<Integer> getTrainsPassingSpecifiedNumberOfStations(Integer stationNumber) {
        Map<Integer, Integer> freqArray = new HashMap<>();
        repository.findAll().forEach(e -> freqArray.put(e.getId().getFirst(), 0));
        repository.findAll().forEach(e -> {Integer currentValue = freqArray.get(e.getId().getFirst());
            currentValue += 1;
            freqArray.put(e.getId().getFirst(), currentValue);});
        return freqArray.keySet()
                .stream()
                .filter(integer -> (freqArray.get(integer).equals(stationNumber)))
                .collect(Collectors.toSet());
    }

    /**
     *
     * @returns the set of Trains which pass every station
     */
    public Set<Train> getTrainsPassingEveryStation()
    {
        Set<Integer> trainIDs = this.getTrainsPassingSpecifiedNumberOfStations(StationService.getStations().size());
        Set<Train> result = new HashSet<>();
        TrainService.getTrains().forEach(e -> {if (trainIDs.contains(e.getId())) result.add(e);});
        return result;
    }
}
