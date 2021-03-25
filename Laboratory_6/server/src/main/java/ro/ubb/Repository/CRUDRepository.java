package ro.ubb.Repository;


import org.springframework.beans.factory.annotation.Autowired;
import ro.ubb.Model.BaseEntity;
import ro.ubb.Model.Exceptions.ValidatorException;
import ro.ubb.Repository.DBOUtils.DBOServices;

@org.springframework.stereotype.Repository
public abstract class CRUDRepository<ID, T extends BaseEntity<ID>> implements Repository<ID, T> {
    @Autowired
    private DBOServices<ID, T> entityService;

    @Override
    public Iterable<T> read() {
        return entityService .getEntities().values();
    }

    @Override
    public void save(T entity) throws ValidatorException {
        this.entityService.saveEntity(entity);
    }

    @Override
    public void delete(ID id){
        this.entityService.deleteEntity(id);
    }

    @Override
    public void update(T entity) throws ValidatorException{
        this.entityService .updateEntity(entity);
    }

}