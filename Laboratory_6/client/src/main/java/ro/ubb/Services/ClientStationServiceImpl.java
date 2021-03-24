package ro.ubb.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ro.ubb.Model.Station;

import java.util.Set;

public class ClientStationServiceImpl implements EntityService<Integer, Station> {
    @Autowired
    @Qualifier("station")
    private EntityService<Integer, Station> entityService;

    @Override
    public Set<Station> readEntities() {
        return entityService.readEntities();
    }

    @Override
    public void createEntity(Station entity) {
        entityService.createEntity(entity);
    }

    @Override
    public Station deleteEntity(Integer integer) {
        return entityService.deleteEntity(integer);
    }

    @Override
    public void updateEntity(Station entity) {
        entityService.updateEntity(entity);
    }

}
