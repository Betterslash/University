package ro.ubb.TransferServices;

import ro.ubb.Model.BaseEntity;

import java.util.concurrent.Future;

public interface ITransferService<ID, E extends BaseEntity<ID>> {
    int PORT = 8080;
    String HOST = "localhost";
    String GET_ENTITIES = "getEntities";
    Future<String> getEntities();
}
