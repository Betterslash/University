package ro.ubb.Repository;


import org.springframework.beans.factory.annotation.Autowired;
import ro.ubb.Model.BaseEntity;
import ro.ubb.Model.Exceptions.ValidatorException;
import ro.ubb.Repository.DBOUtils.DBOServices;

import java.util.Optional;

public class CRUDRepository<ID, T extends BaseEntity<ID>> implements Repository<ID, T> {
    @Autowired
    private DBOServices<ID, T> entityService;

    @Override
    public Iterable<T> read() {
        return entityService .getEntities().values();
    }

    @Override
    public Optional<T> save(T entity) throws ValidatorException {
        this.entityService.saveEntity(entity);
        return Optional.empty();
    }

    @Override
    public Optional<T> delete(ID id){
        this.entityService.deleteEntity(id);
        return Optional.empty();
    }

    @Override
    public Optional<T> update(T entity) throws ValidatorException{
        this.entityService .updateEntity(entity);
        return Optional.empty();
    }

}
