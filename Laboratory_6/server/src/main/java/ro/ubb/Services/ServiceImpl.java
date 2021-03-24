package ro.ubb.Services;

import ro.ubb.Model.BaseEntity;
import ro.ubb.Model.Exceptions.ServiceExceptions.ServiceException;
import ro.ubb.Repository.CRUDRepository;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public abstract class ServiceImpl<ID, E extends BaseEntity<ID>> implements EntityService<ID, E>{
    private final CRUDRepository<ID, E> repository;

    protected ServiceImpl(CRUDRepository<ID, E> repository) {
        this.repository = repository;
    }

    @Override
    public Set<E> readEntities() {
        return StreamSupport.stream(repository.read().spliterator(), false).collect(Collectors.toSet());
    }

    @Override
    public void createEntity(E entity) {
        repository.save(entity);
    }

    @Override
    public E deleteEntity(ID id) {
       return repository.delete(id).orElseThrow(() -> new ServiceException("Something went wrong for deleting !"));
    }

    @Override
    public void updateEntity(E entity) {
        repository.update(entity);
    }
}
