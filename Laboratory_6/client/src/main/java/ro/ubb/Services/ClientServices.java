package ro.ubb.Services;

import ro.ubb.Model.BaseEntity;

import java.util.Set;

public abstract class ClientServices<ID, E extends BaseEntity<ID>> implements EntityService<ID, E>{
    protected EntityService<ID, E> entityService;
    protected void setEntityService(EntityService<ID, E> entityService) {
        this.entityService = entityService;
    }

    @Override
    public Set<E> readEntities() {
        return entityService.readEntities();
    }

    @Override
    public void createEntity(E entity) {
        entityService.createEntity(entity);
    }

    @Override
    public void deleteEntity(ID integer) {
        entityService.deleteEntity(integer);
    }

    @Override
    public void updateEntity(E entity) {
        entityService.updateEntity(entity);
    }

}
