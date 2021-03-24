package ro.ubb.Services;

import ro.ubb.Model.Station;
import ro.ubb.Repository.StationRepository;

public class StationService extends ServiceImpl<Integer, Station>{
    public StationService() {
        super(new StationRepository());
    }
}
