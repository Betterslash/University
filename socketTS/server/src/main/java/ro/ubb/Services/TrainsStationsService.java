package ro.ubb.Services;

import ro.ubb.Model.CustomADT.Pair;
import ro.ubb.Model.Exceptions.ServiceExceptions.TrainServiceException;
import ro.ubb.Model.Station;
import ro.ubb.Model.Train;
import ro.ubb.Model.TrainsStationsEntity;
import ro.ubb.Repository.IRepository;
import ro.ubb.Services.ServiceAbstractions.AbstractIService;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 *
 */
public class TrainsStationsService extends AbstractIService<Pair<Integer, Integer>, TrainsStationsEntity<Integer, Integer>> {
    private static IRepository<Pair<Integer, Integer>, TrainsStationsEntity<Integer, Integer>> repository = null;
    private final TrainService trainsService;
    private final StationService stationService;
    public TrainsStationsService(IRepository<Pair<Integer, Integer>, TrainsStationsEntity<Integer, Integer>> repository, TrainService trainsService, StationService stationService)
    {
        super(repository);
        TrainsStationsService.repository = repository;
        this.trainsService = trainsService;
        this.stationService = stationService;
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
        return this.stationService.getStations().stream()
                                                    .filter(e-> e.getId().equals(mostTraveledStationID))
                                                    .collect(Collectors.toSet());
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
        Set<Integer> stationIDs = this.getStationsPassedByNumberOfTrain(this.trainsService.getAllEntities().size());
        Set<Station> result = new HashSet<>();
        this.stationService.getStations().forEach(e -> {if (stationIDs.contains(e.getId())) result.add(e);});
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
        Set<Integer> trainIDs = this.getTrainsPassingSpecifiedNumberOfStations(this.stationService.getAllEntities().size());
        Set<Train> result = new HashSet<>();
        this.trainsService.getAllEntities().forEach(e -> {if (trainIDs.contains(e.getId())) result.add(e);});
        return result;
    }
}
