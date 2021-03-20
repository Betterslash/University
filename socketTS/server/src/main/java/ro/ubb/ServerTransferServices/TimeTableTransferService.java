package ro.ubb.ServerTransferServices;

import ro.ubb.Model.CustomADT.Pair;
import ro.ubb.Model.Exceptions.DBOServiceExceptions.DBOServiceException;
import ro.ubb.Model.Station;
import ro.ubb.Model.Train;
import ro.ubb.Model.TrainsStationsEntity;
import ro.ubb.Repository.IRepository;
import ro.ubb.Repository.Repositories.CRUDRepository;
import ro.ubb.Repository.Repositories.CRUDUtils.TimeTableDBOService;
import ro.ubb.Services.TrainsStationsService;
import ro.ubb.TransferServices.ServerAbstractions.AbstractFeaturesTransferService;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class TimeTableTransferService extends AbstractFeaturesTransferService<Pair<Integer, Integer>, TrainsStationsEntity<Integer, Integer>> {
    private final TrainsStationsService ttService;
    public TimeTableTransferService() {
        super(TIME_TABLE_SIGNATURE);
        IRepository<Pair<Integer, Integer>, TrainsStationsEntity<Integer, Integer>> ttRepository = new CRUDRepository<>(new TimeTableDBOService());
        this.ttService = new TrainsStationsService(ttRepository);
    }

    /**
     *
     * @returns all objects of type TimeTable
     */
    @Override
    public CompletableFuture<String> getEntities() {
        return CompletableFuture.supplyAsync(() -> ttService.getAllEntities()
        .stream()
        .map(TrainsStationsEntity::toString)
        .reduce((acc ,elem) -> acc + "\n" + elem)
        .orElseThrow(() -> new DBOServiceException("Couldn't get entities from time tables table!")));
    }

    /**
     * adds an entity
     * @returns message based on the success of the execution
     */
    @Override
    public CompletableFuture<String> addEntity(TrainsStationsEntity<Integer, Integer> entity) {
        return CompletableFuture.supplyAsync(() -> {
            ttService.executeCreate(entity);
            return "Succesfully added " + entity + " !";
        });
    }

    /**
     * deletes an entity
     * @returns message based on the success of the execution
     */
    @Override
    public CompletableFuture<String> deleteEntity(Pair<Integer, Integer> integerIntegerPair) {
        return CompletableFuture.supplyAsync(() -> {
            ttService.executeDelete(integerIntegerPair);
            return "Succesfully deleted time table with ids " + integerIntegerPair + " !";
        });
    }

    /**
     * updates an entity
     * @returns message based on the success of the execution
     */
    @Override
    public CompletableFuture<String> updateEntity(TrainsStationsEntity<Integer, Integer> entity) {
        return CompletableFuture.supplyAsync(() -> {
            ttService.executeUpdate(entity);
            return "Succesfully updated " + entity + " !";
        });
    }

    /**
     *
     * @returns message containing the trains that are passing every station
     */
    @Override
    public CompletableFuture<String> getTrainsPassingEveryStation() {
        return CompletableFuture.supplyAsync(() -> {
            Set<Train> trains = ttService.getTrainsPassingEveryStation();
            return "Trains passing every station are: " + trains.toString();
        });
    }

    /**
     *
     * @returns message containing the most traveled station
     */
    @Override
    public CompletableFuture<String> getMostTraveledStation() {
        return CompletableFuture.supplyAsync(() -> {
            Set<Station> stations = ttService.getMostTraveledStation();
            return "Most traveled station is: " +stations.toString();
        });
    }

    /**
     *
     * @returns message containing the stations that are passed by every train
     */
    @Override
    public CompletableFuture<String> getStationsPassedByEveryTrain() {
        return CompletableFuture.supplyAsync(() -> {
            Set<Station> stations = ttService.getStationsPassedByEveryTrain();
            return "Stations passed by every train are: " + stations.toString();
        });
    }

}
