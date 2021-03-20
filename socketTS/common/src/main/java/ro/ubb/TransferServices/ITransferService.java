package ro.ubb.TransferServices;

import ro.ubb.Model.BaseEntity;

import java.util.concurrent.CompletableFuture;

public interface ITransferService<ID, E extends BaseEntity<ID>> {
    int PORT = 8080;
    String HOST = "localhost";
    String CREATE_ENTITY = "create";
    String READ_ENTITIES = "read";
    String UPDATE_ENTITY = "update";
    String DELETE_ENTITY = "delete";
    String GET_TRAINS_PASSING_EVERY_STATION = "getTrainsPassingEveryStation";
    String GET_MOST_TRAVELED_STATION = "getMostTraveledStation";
    String GET_STATIONS_PASSED_BY_EVERY_TRAIN = "getStationsPassedByEveryTrain";
    String LINE_SEPARATOR = System.lineSeparator();
    CompletableFuture<String> getEntities();
    CompletableFuture<String> addEntity(E entity);
    CompletableFuture<String> deleteEntity(ID id);
    CompletableFuture<String> updateEntity(E entity);
}
