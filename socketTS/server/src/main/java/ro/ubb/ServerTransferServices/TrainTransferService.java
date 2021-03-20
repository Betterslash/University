package ro.ubb.ServerTransferServices;

import ro.ubb.Model.Train;
import ro.ubb.Repository.IRepository;
import ro.ubb.Repository.Repositories.CRUDRepository;
import ro.ubb.Repository.Repositories.CRUDUtils.TrainDBOService;
import ro.ubb.Services.TrainService;
import ro.ubb.TransferServices.ITransferService;

import java.util.concurrent.CompletableFuture;

public class TrainTransferService implements ITransferService<Integer, Train> {
    private final TrainService trainService;
    public TrainTransferService() {
        IRepository<Integer, Train> trainIRepository = new CRUDRepository<>(new TrainDBOService());
        this.trainService = new TrainService(trainIRepository);
    }

    @Override
    public CompletableFuture<String> getEntities() {
        return CompletableFuture.supplyAsync(() -> this.trainService.getAllEntities().stream()
                .map(Train::toString)
                .reduce((a ,b) -> a + "\n" + b).orElse(""));
    }

    @Override
    public CompletableFuture<String> addEntity(Train entity) {
        return CompletableFuture.supplyAsync(() -> {
            this.trainService.executeCreate(entity);
            return "Train " + entity.toString() + " was added !";});
    }

    @Override
    public CompletableFuture<String> deleteEntity(Integer integer) {

        return CompletableFuture.supplyAsync(() ->
        {   this.trainService.executeDelete(integer);
            return "Train with id " + integer + " was deleted !";
        });
    }

    @Override
    public CompletableFuture<String> updateEntity(Train entity) {
        return CompletableFuture.supplyAsync(() -> {this.trainService.executeUpdate(entity);
        return "Train " + entity.toString() + " was updated !";});
    }
}
