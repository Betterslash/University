package ro.ubb.TransferServices;

import java.util.Set;
import java.util.concurrent.Future;

public interface ITransferService<E> {
    int PORT = 8080;
    String HOST = "localhost";
    String GET_ENTITIES = "getEntities()";
    Future<Set<E>> getEntities();
}
