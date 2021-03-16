package ro.ubb.Services;

import ro.ubb.Model.Exceptions.ValidatorException;
import ro.ubb.Model.CustomADT.Pair;
import ro.ubb.Model.TrainsStationsEntity;
import ro.ubb.Model.Validators.TrainsStationsValidator;
import ro.ubb.Repository.IRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
     * @throws IOException
     */
    public void executeCreate(TrainsStationsEntity<Integer, Integer> trainsStationsEntity) throws IOException {
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
     * @throws IOException
     */
    @Override
    public void executeUpdate(TrainsStationsEntity<Integer, Integer> object) throws IOException {
        repository.update(object);
    }

    /**
     * @param id the id of the route that will be deleted
     *
     */
    @Override
    public void executeDelete(Pair<Integer, Integer> id) throws IOException {
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
        Map<Integer, Integer> freqArray = new HashMap<>();
        repository.findAll().forEach(e -> freqArray.put(e.getId().getLast(), 0));
        repository.findAll().forEach(e ->
            {Integer currentValue = freqArray.get(e.getId().getLast());
            currentValue += 1;
            freqArray.put(e.getId().getLast(), currentValue);});

        Map.Entry<Integer, Integer> maxEntry = freqArray.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue()).get();
        return maxEntry.getKey();
    }

    /**
     *
     * @param numberOfTrains
     * @return the set of stations which are passed by every train
     */
    public static Set<Integer> getStationsPassedByEveryTrain(Integer numberOfTrains) throws SQLException {
        Map<Integer, Integer> freqArray = new HashMap<>();
        repository.findAll().forEach(e -> freqArray.put(e.getId().getLast(), 0));
        repository.findAll().forEach(e -> {Integer currentValue = freqArray.get(e.getId().getLast());
                                            currentValue += 1;
                                            freqArray.put(e.getId().getLast(), currentValue);});
        return freqArray.keySet()
                                    .stream()
                                    .filter(integer -> (freqArray.get(integer).equals(numberOfTrains)))
                                    .collect(Collectors.toSet());
    }

    /**
     *
     * @param stationNumber
     * @return the set of trains which pass every station
     */
    public static Set<Integer> getTrainsPassingEveryStation(Integer stationNumber) throws SQLException {
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
