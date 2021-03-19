package ro.ubb.Services;


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
    void executeCreate(T object);

    /**
     * @param object updates an object in the repository
     */
    void executeUpdate(T object);

    /**
     * @param id deletes an element with the specified id
     */
    void executeDelete(K id);
}
