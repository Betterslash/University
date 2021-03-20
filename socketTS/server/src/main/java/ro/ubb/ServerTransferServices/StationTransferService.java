package ro.ubb.ServerTransferServices;

import ro.ubb.Model.Exceptions.DBOServiceException;
import ro.ubb.Model.Station;
import ro.ubb.Repository.IRepository;
import ro.ubb.Repository.Repositories.CRUDRepository;
import ro.ubb.Repository.Repositories.CRUDUtils.StationDBOService;
import ro.ubb.TransferServices.ServerAbstractions.AbstractServerTransferServices;
import ro.ubb.Services.StationService;

import java.util.concurrent.CompletableFuture;

public class StationTransferService extends AbstractServerTransferServices<Integer, Station> {
    private final StationService stationService;

    public StationTransferService() {
        super(STATION_SIGNATURE);
        IRepository<Integer, Station> stationIRepository = new CRUDRepository<>(new StationDBOService());
        this.stationService  = new StationService(stationIRepository);
    }

    public StationService getStationService()
    {
        return this.stationService;
    }

    /**
     *
     * @returns all objects of type Station
     */
    @Override
    public CompletableFuture<String> getEntities() {
        return CompletableFuture.supplyAsync(() ->
            this.stationService.getAllEntities()
                    .stream()
                    .map(Station::toString)
                    .reduce((acc, elem) -> acc +"\n" +elem)
                    .orElseThrow(() -> new DBOServiceException("Couldn't get entities!")));
    }

    /**
     * adds an entity
     * @param entity
     * @returns message based on the success of the execution
     */
    public CompletableFuture<String> addEntity(Station entity) {
        return CompletableFuture.supplyAsync(() -> {
            this.stationService.executeCreate(entity);
            return "Succesfully added " + entity + " !";
        });
    }

    /**
     * deletes an entity
     * @param integer
     * @returns message based on the success of the execution
     */
    @Override
    public CompletableFuture<String> deleteEntity(Integer integer) {
        return CompletableFuture.supplyAsync(() -> {
            this.stationService.executeDelete(integer);
            return "Succesfully deleted station with id " + integer + " !";
        });
    }

    /**
     * updates an entity
     * @param entity
     * @returns message based on the success of the execution
     */
    public CompletableFuture<String> updateEntity(Station entity) {
        return CompletableFuture.supplyAsync(() ->{
            this.stationService.executeUpdate(entity);
            return "Succesfully updated " + entity + " !";
        });
    }
}
