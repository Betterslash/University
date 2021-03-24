package ro.ubb.Services;

import org.springframework.beans.factory.annotation.Autowired;
import ro.ubb.Model.CustomADT.Pair;
import ro.ubb.Model.TrainsStationsEntity;

import java.util.Set;

public class ClientTimeTableServiceImpl implements EntityService<Pair<Integer, Integer>, TrainsStationsEntity<Integer, Integer>>{
    @Autowired
    private EntityService<Pair<Integer, Integer>, TrainsStationsEntity<Integer, Integer>> entityService;

    @Override
    public Set<TrainsStationsEntity<Integer, Integer>> readEntities() {
        return entityService.readEntities();
    }

    @Override
    public void createEntity(TrainsStationsEntity<Integer, Integer> entity) {
        entityService.createEntity(entity);
    }

    @Override
    public TrainsStationsEntity<Integer, Integer> deleteEntity(Pair<Integer, Integer> integerIntegerPair) {
        return entityService.deleteEntity(integerIntegerPair);
    }

    @Override
    public void updateEntity(TrainsStationsEntity<Integer, Integer> entity) {
        entityService.updateEntity(entity);
    }
}
