package ro.ubb.Repository.Repositories;


import ro.ubb.Exceptions.ValidatorException;
import ro.ubb.Model.BaseEntity;
import ro.ubb.Repository.IRepository;
import ro.ubb.Repository.Repositories.CRUDUtils.DBOServices;

import java.io.IOException;
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
    public Optional<T> save(T entity) throws ValidatorException, IOException{
        this.entityService.saveEntity(entity);
        return Optional.empty();
    }

    @Override
    public Optional<T> delete(ID id) throws IOException {
        this.entityService.deleteEntity(id);
        return Optional.empty();
    }

    @Override
    public Optional<T> update(T entity) throws ValidatorException, IOException{
        this.entityService .updateEntity(entity);
        return Optional.empty();
    }

}
