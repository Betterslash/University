package ro.ubb.ServerTransferServices;

import ro.ubb.Model.Exceptions.DBOServiceException;
import ro.ubb.Model.Station;
import ro.ubb.Repository.IRepository;
import ro.ubb.Repository.Repositories.CRUDRepository;
import ro.ubb.Repository.Repositories.CRUDUtils.StationDBOService;
import ro.ubb.Services.StationService;
import ro.ubb.TransferServices.ITransferService;

import java.util.concurrent.CompletableFuture;

public class StationTransferService implements ITransferService<Integer, Station> {

    private final StationService stationService;

    public StationTransferService() {
        IRepository<Integer, Station> stationIRepository = new CRUDRepository<>(new StationDBOService());
        this.stationService  = new StationService(stationIRepository);
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
