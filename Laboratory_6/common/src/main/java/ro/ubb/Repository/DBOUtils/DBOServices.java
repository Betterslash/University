package ro.ubb.Repository.DBOUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import ro.ubb.Model.BaseEntity;

import java.util.Map;

public abstract class DBOServices<ID, T extends BaseEntity<ID>>{
    @Autowired
    protected JdbcOperations jdbcOperations;
    public abstract Map<ID, T> getEntities();
    public abstract void saveEntity(T entity);
    public abstract void deleteEntity(ID id);
    public abstract void updateEntity(T entity);
}
