package ro.ubb.Repository;

import ro.ubb.Model.Train;
import ro.ubb.Repository.CRUDUtils.TrainDBOService;

public class TrainRepository extends CRUDRepository<Integer, Train> {
    public TrainRepository() {
        super(new TrainDBOService());
    }
}
