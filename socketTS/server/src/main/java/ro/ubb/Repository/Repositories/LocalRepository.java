package ro.ubb.Repository.Repositories;

import ro.ubb.Model.Exceptions.ValidatorException;
import ro.ubb.Model.BaseEntity;
import ro.ubb.Model.Validators.Validator;
import ro.ubb.Repository.IRepository;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;

/**
 * @param <ID>
 * @param <T>
 */
public class LocalRepository<ID, T extends BaseEntity<ID>> implements IRepository<ID, T> {
    private final Map<ID, T> entities;
    private final Validator<T> validator;

    /**
     * @param validator validates the entities
     */
    public LocalRepository(Validator<T> validator) {
        this.validator = validator;
        entities = new HashMap<>();
    }

    /**
     * @param id must be not null.
     * @return Optional of element
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
        return Optional.ofNullable(entities.putIfAbsent(entity.getId(), entity));
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
        return Optional.ofNullable(entities.remove(id));
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
        return Optional.ofNullable(entities.computeIfPresent(entity.getId(), (k, v) -> entity));
    }
}
