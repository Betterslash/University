package ro.ubb.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.Model.BaseEntity;
import ro.ubb.Model.Exceptions.ServiceExceptions.ServiceException;
import ro.ubb.Repository.Repository;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public abstract class ServiceImpl<ID, E extends BaseEntity<ID>> implements EntityService<ID, E>{
    @Autowired
    private Repository<ID, E> repository;
    @Override
    public Set<E> readEntities() {
        return StreamSupport.stream(repository.read().spliterator(), false).collect(Collectors.toSet());
    }

    @Override
    public void createEntity(E entity) {
        repository.save(entity);
    }

    @Override
    public void deleteEntity(ID id) {
       repository.delete(id);
    }

    @Override
    public void updateEntity(E entity) {
        repository.update(entity);
    }
}
