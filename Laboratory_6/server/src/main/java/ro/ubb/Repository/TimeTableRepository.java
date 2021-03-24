package ro.ubb.Repository;

import ro.ubb.Model.CustomADT.Pair;
import ro.ubb.Model.TrainsStationsEntity;
import ro.ubb.Repository.CRUDUtils.TimeTableDBOService;

public class TimeTableRepository extends CRUDRepository<Pair<Integer, Integer>, TrainsStationsEntity<Integer, Integer>>{
    public TimeTableRepository() {
        super(new TimeTableDBOService());
    }
}
