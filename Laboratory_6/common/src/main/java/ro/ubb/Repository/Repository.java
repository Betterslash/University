package ro.ubb.Repository;

import ro.ubb.Model.BaseEntity;

import java.util.Optional;

public interface Repository<ID, E extends BaseEntity<ID>>{
    void save(E entity);
    void update(E entity);
    Optional<E> delete(ID id);
    Iterable<E> read();
}
