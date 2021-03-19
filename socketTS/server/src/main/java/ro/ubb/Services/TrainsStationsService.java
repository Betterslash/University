package ro.ubb.Services;

import ro.ubb.Model.CustomADT.Pair;
import ro.ubb.Model.Exceptions.ServiceExceptions.TrainServiceException;
import ro.ubb.Model.Exceptions.ValidatorException;
import ro.ubb.Model.TrainsStationsEntity;
import ro.ubb.Repository.IRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
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
     * @return a set of all routes
     */
    public Set<TrainsStationsEntity<Integer, Integer>> getAllEntities() {
        Iterable<TrainsStationsEntity<Integer, Integer>> trainsAndStations = repository.findAll();
        return StreamSupport.stream(trainsAndStations.spliterator(), false).collect(Collectors.toSet());
    }

    /**
     * @return the most visited station id
     */
    public static Integer getMostTraveledStation() {
        Map.Entry<Integer, Integer> maxEntry = initializeMap().entrySet()
                .stream()
                .max(Map.Entry.comparingByValue()).orElseThrow(() -> new TrainServiceException("Couldn't get most travelled stations!"));
        return maxEntry.getKey();
    }

    private static Map<Integer, Integer> initializeMap() {
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
     * @return the set of stations which are passed by every train
     */
    public static Set<Integer> getStationsPassedByEveryTrain(Integer numberOfTrains) {
        Map<Integer, Integer> freqArr = initializeMap();
        return freqArr.keySet()
                .stream()
                .filter(integer -> (freqArr.get(integer).equals(numberOfTrains)))
                .collect(Collectors.toSet());
    }

    /**
     *
     * @param stationNumber stations to be checked
     * @return the set of trains which pass every station
     */
    public static Set<Integer> getTrainsPassingEveryStation(Integer stationNumber) {
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
}
