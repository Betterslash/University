package ro.ubb.Services;

import ro.ubb.Model.BaseEntity;

import java.util.Set;

public interface EntityService<ID, E extends BaseEntity<ID>> {
    Set<E> readEntities();
    void createEntity(E entity);
    void deleteEntity(ID id);
    void updateEntity(E entity);
}
