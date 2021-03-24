package ro.ubb.Services;

import ro.ubb.Model.CustomADT.Pair;
import ro.ubb.Model.TrainsStationsEntity;
import ro.ubb.Repository.TimeTableRepository;

public class TimeTableService extends ServiceImpl<Pair<Integer, Integer>, TrainsStationsEntity<Integer, Integer>> {
    public TimeTableService() {
        super(new TimeTableRepository()) ;
    }
}
