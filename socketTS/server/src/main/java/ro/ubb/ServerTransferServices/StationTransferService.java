package ro.ubb.ServerTransferServices;

import ro.ubb.Model.Exceptions.DBOServiceException;
import ro.ubb.Model.Station;
import ro.ubb.Services.StationService;
import ro.ubb.TransferServices.ITransferService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public class StationTransferService implements ITransferService<Integer, Station> {
    private final ExecutorService executorService;
    private final StationService stationService;

    public StationTransferService(ExecutorService executorService, StationService stationService) {
        this.executorService = executorService;
        this.stationService = stationService;
    }

    @Override
    public CompletableFuture<String> getEntities() {
        return CompletableFuture.supplyAsync(() ->
            this.stationService.getAllEntities()
                    .stream()
                    .map(Station::toString)
                    .reduce((acc, elem) -> acc +"\n" +elem)
                    .orElseThrow(() -> new DBOServiceException("Couldn't get entities!")));
    }

    @Override
    public CompletableFuture<String> addEntity(Station entity) {
        return CompletableFuture.supplyAsync(() -> {
            this.stationService.executeCreate(entity);
            return "Succesfully added " + entity + " !";
        });
    }

    @Override
    public CompletableFuture<String> deleteEntity(Integer integer) {
        return CompletableFuture.supplyAsync(() -> {
            this.stationService.executeDelete(integer);
            return "Succesfully deleted station with id " + integer + " !";
        });
    }

    @Override
    public CompletableFuture<String> updateEntity(Station entity) {
        return CompletableFuture.supplyAsync(() ->{
            this.stationService.executeUpdate(entity);
            return "Succesfully updated " + entity + " !";
        });
    }
}
