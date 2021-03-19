package ro.ubb.ServerTransferServices;

import ro.ubb.Model.CustomADT.Pair;
import ro.ubb.Model.Exceptions.DBOServiceException;
import ro.ubb.Model.TrainsStationsEntity;
import ro.ubb.Services.TrainsStationsService;
import ro.ubb.TransferServices.ITransferService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public class TimeTableTransferService implements ITransferService<Pair<Integer, Integer>, TrainsStationsEntity<Integer, Integer>> {
    private final ExecutorService executorService;
    private final TrainsStationsService ttService;
    public TimeTableTransferService(ExecutorService executorService, TrainsStationsService ttService) {
        this.ttService = ttService;
        this.executorService = executorService;
    }
    @Override
    public CompletableFuture<String> getEntities() {
        return CompletableFuture.supplyAsync(() -> ttService.getAllEntities()
        .stream()
        .map(TrainsStationsEntity::toString)
        .reduce((acc ,elem) -> acc + "\n" + elem)
        .orElseThrow(() -> new DBOServiceException("Couldn't get entities from time tables table!")));
    }

    @Override
    public CompletableFuture<String> addEntity(TrainsStationsEntity<Integer, Integer> entity) {
        return CompletableFuture.supplyAsync(() -> {
            ttService.executeCreate(entity);
            return "Succesfully added " + entity + " !";
        });
    }

    @Override
    public CompletableFuture<String> deleteEntity(Pair<Integer, Integer> integerIntegerPair) {
        return CompletableFuture.supplyAsync(() -> {
            ttService.executeDelete(integerIntegerPair);
            return "Succesfully deleted time table with ids " + integerIntegerPair + " !";
        });
    }

    @Override
    public CompletableFuture<String> updateEntity(TrainsStationsEntity<Integer, Integer> entity) {
        return CompletableFuture.supplyAsync(() -> {
            ttService.executeUpdate(entity);
            return "Succesfully updated " + entity + " !";
        });
    }
}
