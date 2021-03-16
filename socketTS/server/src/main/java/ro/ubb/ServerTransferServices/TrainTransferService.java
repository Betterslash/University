package ro.ubb.ServerTransferServices;

import ro.ubb.Model.Train;
import ro.ubb.Services.TrainService;
import ro.ubb.TransferServices.ITransferService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public class TrainTransferService implements ITransferService<Integer, Train> {
    private final ExecutorService executorService;
    private final TrainService trainService;
    public TrainTransferService(ExecutorService executorService, TrainService trainService) {
         this.trainService = trainService;
        this.executorService = executorService;
    }

    @Override
    public CompletableFuture<String> getEntities() {
        System.out.println("here");
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
        this.trainService.executeUpdate(entity);
        return CompletableFuture.supplyAsync(() -> "Train " + entity.toString() + " was updated !");
    }
}
