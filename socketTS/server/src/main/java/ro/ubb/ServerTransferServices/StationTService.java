package ro.ubb.ServerTransferServices;

import ro.ubb.Model.Station;
import ro.ubb.Repository.Repositories.CRUDRepository;
import ro.ubb.Repository.Repositories.CRUDUtils.StationDBOService;
import ro.ubb.ServerTransferServices.ServerAbstraction.AbstractServerTService;
import ro.ubb.Services.StationService;

public class StationTService extends AbstractServerTService<Integer, Station> {
    public StationTService() {
        super( new StationService(new CRUDRepository<>(new StationDBOService())), STATION_SIGNATURE);
    }
}
