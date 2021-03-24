package ro.ubb.Services;

import ro.ubb.Model.Train;
import ro.ubb.Repository.TrainRepository;

public class TrainService extends ServiceImpl<Integer, Train> {
    public TrainService() {
        super(new TrainRepository());
    }
}
