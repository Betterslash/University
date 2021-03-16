package ro.ubb.TransferServices;

import ro.ubb.Model.BaseEntity;

import java.util.concurrent.CompletableFuture;

public interface ITransferService<ID, E extends BaseEntity<ID>> {
    int PORT = 8080;
    String HOST = "localhost";
    String GET_TRAIN_ENTITIES = "getTrainEntities";
    String UPDATE_TRAIN_ENTITY = "updateTrainEntity";
    String DELETE_TRAIN_ENTITY = "deleteTrainEntity";
    String ADD_TRAIN_ENTITY = "addTrainEntity";
    String GET_STATION_ENTITIES = "getStationEntities";
    String UPDATE_STATION_ENTITY = "updateStationEntity";
    String DELETE_STATION_ENTITY = "deleteStationEntity";
    String ADD_STATION_ENTITY = "addStationEntity";
    CompletableFuture<String> getEntities();
    CompletableFuture<String> addEntity(E entity);
    CompletableFuture<String> deleteEntity(ID id);
    CompletableFuture<String> updateEntity(E entity);
}
