package ro.ubb.Repository;

import ro.ubb.Model.BaseEntity;

public interface Repository<ID, E extends BaseEntity<ID>>{
    void save(E entity);
    void update(E entity);
    void delete(ID id);
    Iterable<E> read();
}
