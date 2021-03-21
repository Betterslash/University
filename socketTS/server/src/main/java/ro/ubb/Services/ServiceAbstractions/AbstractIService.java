package ro.ubb.Services.ServiceAbstractions;

import ro.ubb.Model.BaseEntity;
import ro.ubb.Model.Exceptions.ServiceExceptions.AbstractServiceException;
import ro.ubb.Repository.IRepository;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class AbstractIService<ID, E extends BaseEntity<ID>> implements Service<ID, E>{

    protected final IRepository<ID, E> repository;

    public AbstractIService(IRepository<ID, E> repository) {
        this.repository = repository;
    }

    /**
     * @param entity the train to be addded
     */
    public void executeCreate(E entity) {
        try {
            repository.save(entity);
        }catch (Exception runtimeException){
            throw new AbstractServiceException("Something went wrong when trying to save an entry!");
        }
    }

    /**
     * @returns a set of all trains
     */
    public Set<E> getAllEntities() {
        Iterable<E> trains = repository.findAll();
        return StreamSupport.stream(trains.spliterator(), false).collect(Collectors.toSet());
    }

    /**
     *
     * @param entity updates the train in the repository
     */
    public void executeUpdate(E entity) {
        try {
            repository.update(entity);
        }catch (Exception runtimeException){
            throw new AbstractServiceException("Something went wrong when trying to update an entry!");
        }
    }

    /**
     *
     * @param id deletes an element with the specified id
     */
    public void executeDelete(ID id) {
        try {
            repository.delete(id);
        }catch (Exception runtimeException){
            throw new AbstractServiceException("Something went wrong when trying to delete an entry!");
        }
    }
}
