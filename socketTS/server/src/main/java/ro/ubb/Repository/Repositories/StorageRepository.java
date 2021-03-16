package ro.ubb.Repository.Repositories;

import ro.ubb.Exceptions.StorageRepositoryException;
import ro.ubb.Exceptions.ValidatorException;
import ro.ubb.Model.BaseEntity;
import ro.ubb.Model.Validators.Validator;
import ro.ubb.Repository.IRepository;
import ro.ubb.Repository.Loader;
import ro.ubb.Repository.Savers.AbstractSaver;
import ro.ubb.Repository.Savers.FileSaver;
import ro.ubb.Repository.Savers.XMLSaver;
import ro.ubb.Repository.XMLUtils.XMLLoaders.XMLLoader;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;

public class StorageRepository<ID, T  extends BaseEntity<ID>> implements IRepository<ID, T> {
    private final Map<ID, T> entities;
    private final Validator<T> validator;
    private final AbstractSaver<ID, T> abstractSaver;
    /**
     * @param validator validates the entities
     * @param loader an interface to load entities from file
     */
    public StorageRepository(Validator<T> validator, Loader<ID, T> loader) {
        this.validator = validator;
        if(loader instanceof XMLLoader){
            this.abstractSaver = new XMLSaver<>(loader.filePath);
        }else{
            this.abstractSaver = new FileSaver<>(loader.filePath);
        }
        try {
            this.entities = loader.load();
        }catch (Exception e){
            throw new StorageRepositoryException(e.getMessage());
        }
    }

    /**
     * uses the file saver object to save entities to file
     */
    public void saveToFile(){
        try{
            this.abstractSaver.saveToFile(this.entities);
        }catch (Exception e){
            throw new StorageRepositoryException("Smething went wrong during the writting !");
        }
    }
    /**
     * @param id must be not null.
     * @return Optional of element/ it's id
     */
    @Override
    public Optional<T> findOne(ID id) {
        if (id == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        return Optional.ofNullable(entities.get(id));
    }

    /**
     * @return a iterable set of entitites
     */
    @Override
    public Iterable<T> findAll() {
        return new HashSet<>(entities.values());
    }

    /**
     * @param entity must not be null.
     * @return Optional of entity
     * @throws ValidatorException to prevent code from running further
     */
    @Override
    public Optional<T> save(T entity) throws ValidatorException {
        if (entity == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        validator.validate(entity);
        entities.putIfAbsent(entity.getId(), entity);
        this.saveToFile();
        return Optional.empty();
    }

    /**
     * @param id must not be null.
     * @return the performing of removal
     */
    @Override
    public Optional<T> delete(ID id) {
        if (id == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        entities.remove(id);
        this.saveToFile();
        return Optional.empty();
    }

    /**
     * @param entity must not be null.
     * @return the execution of update
     * @throws ValidatorException to prevent code from running further
     */
    @Override
    public Optional<T> update(T entity) throws ValidatorException{
        if (entity == null) {
            throw new IllegalArgumentException("entity must not be null");
        }
        validator.validate(entity);
        entities.computeIfPresent(entity.getId(), (k, v) -> entity);
        this.saveToFile();
        return Optional.empty();
    }

}
