package TrainStations.Repository;

import TrainStations.Model.DTOCustomObj;

public interface IRepository<T extends DTOCustomObj> {
    void save(T object);
    void printAll();
    void delete(T object);
    void update(T object);
}
