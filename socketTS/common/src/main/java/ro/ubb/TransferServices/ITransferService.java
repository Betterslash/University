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
    String GET_TT_ENTITIES = "getTTEntities";
    String UPDATE_TT_ENTITY = "updateTTEntity";
    String DELETE_TT_ENTITY = "deleteTTEntity";
    String ADD_TT_ENTITY = "addTTEntity";
    String GET_TRAINS_PASSING_EVERY_STATION = "getTrainsPassingEveryStation";
    String GET_MOST_TRAVELED_STATION = "getMostTraveledStation";
    String GET_STATIONS_PASSED_BY_EVERY_TRAIN = "getStationsPassedByEveryTrain";
    String LINE_SEPARATOR = System.lineSeparator();
    CompletableFuture<String> getEntities();
    CompletableFuture<String> addEntity(E entity);
    CompletableFuture<String> deleteEntity(ID id);
    CompletableFuture<String> updateEntity(E entity);
    CompletableFuture<String> getTrainsPassingEveryStation();
    CompletableFuture<String> getMostTraveledStation();
    CompletableFuture<String> getStationsPassedByEveryTrain();
}
