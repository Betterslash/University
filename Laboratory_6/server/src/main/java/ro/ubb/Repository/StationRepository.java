package ro.ubb.Repository;

import ro.ubb.Model.Station;
import ro.ubb.Repository.CRUDUtils.StationDBOService;

public class StationRepository extends CRUDRepository<Integer, Station>{

    public StationRepository() {
        super(new StationDBOService());
    }
}
