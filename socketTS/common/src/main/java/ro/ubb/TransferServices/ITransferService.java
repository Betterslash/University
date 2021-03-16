package ro.ubb.TransferServices;

import ro.ubb.Model.BaseEntity;

import java.util.concurrent.CompletableFuture;

public interface ITransferService<ID, E extends BaseEntity<ID>> {
    int PORT = 8080;
    String HOST = "localhost";
    String GET_ENTITIES = "getEntities";
    String UPDATE_ENTITY = "updateEntity";
    String DELETE_ENTITY = "deleteEntity";
    String ADD_ENTITY = "addEntity";
    CompletableFuture<String> getEntities();
    CompletableFuture<String> addEntity(E entity);
    CompletableFuture<String> deleteEntity(ID id);
    CompletableFuture<String> updateEntity(E entity);
}
