package ro.ubb.ServerTransferServices.ServerAbstraction;

import ro.ubb.Model.BaseEntity;
import ro.ubb.Model.Exceptions.DBOServiceExceptions.DBOServiceException;
import ro.ubb.Services.ServiceAbstractions.Service;
import ro.ubb.TransferServices.ServerAbstractions.AbstractTransferServices;

import java.util.concurrent.CompletableFuture;

public abstract class AbstractServerTService<ID, E extends BaseEntity<ID>> extends AbstractTransferServices<ID, E> {
    private final Service<ID, E> service;

    protected AbstractServerTService(Service<ID, E> service, String signature) {
        super(signature);
        this.service = service;
    }

    /**
     *
     * @returns all objects of type Station
     */
    @Override
    public CompletableFuture<String> getEntities() {
        return CompletableFuture.supplyAsync(() ->
                this.service.getAllEntities()
                        .stream()
                        .map(BaseEntity::toString)
                        .reduce((acc, elem) -> acc +"\n" +elem)
                        .orElseThrow(() -> new DBOServiceException("Couldn't get entities!")));
    }

    /**
     * adds an entity
     * @param entity
     * @returns message based on the success of the execution
     */
    public CompletableFuture<String> addEntity(E entity) {
        return CompletableFuture.supplyAsync(() -> {
            this.service.executeCreate(entity);
            return "Succesfully added " + entity + " !";
        });
    }

    /**
     * deletes an entity
     * @param integer
     * @returns message based on the success of the execution
     */
    @Override
    public CompletableFuture<String> deleteEntity(ID integer) {
        return CompletableFuture.supplyAsync(() -> {
            this.service.executeDelete(integer);
            return "Succesfully deleted entity with id " + integer + " !";
        });
    }

    /**
     * updates an entity
     * @param entity
     * @returns message based on the success of the execution
     */
    public CompletableFuture<String> updateEntity(E entity) {
        return CompletableFuture.supplyAsync(() ->{
            this.service.executeUpdate(entity);
            return "Succesfully updated " + entity + " !";
        });
    }

    public Service<ID, E> getService() {
        return service;
    }
}
