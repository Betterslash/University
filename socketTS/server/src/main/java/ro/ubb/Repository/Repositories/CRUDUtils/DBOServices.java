package ro.ubb.Repository.Repositories.CRUDUtils;

import ro.ubb.Model.BaseEntity;

import java.util.Map;

public abstract class DBOServices<ID, T extends BaseEntity<ID>>{
    protected final String url = "jdbc:postgresql://localhost/trains_stations";
    protected final String user = "postgres";
    protected final String password = "root";
    public abstract Map<ID, T> getEntities();
    public abstract void saveEntity(T entity);
    public abstract void deleteEntity(ID id);
    public abstract void updateEntity(T entity);
}
