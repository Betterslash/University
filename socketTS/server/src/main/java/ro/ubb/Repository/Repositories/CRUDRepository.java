package ro.ubb.Repository.Repositories;


import ro.ubb.Model.BaseEntity;
import ro.ubb.Model.Exceptions.ValidatorException;
import ro.ubb.Repository.IRepository;
import ro.ubb.Repository.Repositories.CRUDUtils.DBOServices;

import java.util.Optional;

public class CRUDRepository<ID, T extends BaseEntity<ID>> implements IRepository<ID, T> {
    private final DBOServices<ID, T> entityService;

    public CRUDRepository(DBOServices<ID, T> entityCreator){
        this.entityService  = entityCreator;
    }

    @Override
    public Optional<T> findOne(ID id) {
        return Optional.empty();
    }

    @Override
    public Iterable<T> findAll() {
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
