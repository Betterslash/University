package ro.ubb.ServerTransferServices;

import ro.ubb.Model.Train;
import ro.ubb.Repository.Repositories.CRUDRepository;
import ro.ubb.Repository.Repositories.CRUDUtils.TrainDBOService;
import ro.ubb.ServerTransferServices.ServerAbstraction.AbstractServerTService;
import ro.ubb.Services.TrainService;

public class TrainTService extends AbstractServerTService<Integer, Train> {
    public TrainTService() {
        super(new TrainService(new CRUDRepository<>( new TrainDBOService())), TRAIN_SIGNATURE);
    }
}
