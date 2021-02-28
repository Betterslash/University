package TrainStations.Repository;

import TrainStations.Model.DTOCustomObj;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CRUDLocalRepository<Q extends DTOCustomObj> implements IRepository<Q> {
    private List<Q> representation;

    public CRUDLocalRepository() {
        this.representation = new ArrayList<>();
    }

    @Override
    public void save(Q object) {
        this.representation.add(object);
    }

    @Override
    public void printAll() {
        this.representation.forEach(System.out::println);
    }

    @Override
    public void delete(Q object) {
        this.representation = this.representation.stream()
                .filter(e -> e.getId() != object.getId())
                .collect(Collectors.toList());
    }

    @Override
    public void update(Q object) {
        this.representation = this.representation.stream()
                .map(e ->{
                    if(e.getId() == object.getId()){
                        return object;
                    }else{
                        return e;
                    }
                }).collect(Collectors.toList());
    }
}
