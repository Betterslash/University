package ro.ubb.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ro.ubb.Model.Train;

import java.util.Set;

public class ClientTrainServiceImpl implements EntityService<Integer, Train>{
    @Autowired
    @Qualifier("train")
    private EntityService<Integer, Train> entityService;

    @Override
    public Set<Train> readEntities() {
        return entityService.readEntities();
    }

    @Override
    public void createEntity(Train entity) {
        entityService.createEntity(entity);
    }

    @Override
    public Train deleteEntity(Integer integer) {
        return entityService.deleteEntity(integer);
    }

    @Override
    public void updateEntity(Train entity) {
        entityService.updateEntity(entity);
    }
}
