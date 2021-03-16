package ro.ubb.Services;


import java.io.IOException;
import java.util.Set;

/**
 * @param <K> ID type
 * @param <T> entity type
 */
public interface Service<K, T> {
    /**
     * @return a set with all entities
     */
    Set<T> getAllEntities();

    /**
     * @param object create and store an object
     */
    void executeCreate(T object) throws IOException;

    /**
     * @param object updates an object in the repository
     */
    void executeUpdate(T object) throws IOException;

    /**
     * @param id deletes an element with the specified id
     */
    void executeDelete(K id) throws IOException;
}
