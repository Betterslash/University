package ro.ubb.ServerTransferServices;

import ro.ubb.Model.Train;
import ro.ubb.Repository.IRepository;
import ro.ubb.Repository.Repositories.CRUDRepository;
import ro.ubb.Repository.Repositories.CRUDUtils.TrainDBOService;
import ro.ubb.Services.TrainService;
import ro.ubb.TransferServices.ITransferService;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class TrainTransferService implements ITransferService<Train> {
    private final ExecutorService executorService;
    private final TrainService trainService;
    public TrainTransferService(ExecutorService executorService) {
        IRepository<Integer, Train> trainRepository = new CRUDRepository<>(new TrainDBOService());
        this.trainService = new TrainService(trainRepository);
        this.executorService = executorService;
    }

    @Override
    public Future<Set<Train>> getEntities() {
        return executorService.submit(this.trainService::getAllEntities);
    }
}
