package ro.ubb.ServerTransferServices;

import ro.ubb.Model.Train;
import ro.ubb.Services.TrainService;
import ro.ubb.TransferServices.ITransferService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class TrainTransferService implements ITransferService<Integer, Train> {
    private final ExecutorService executorService;
    private final TrainService trainService;
    public TrainTransferService(ExecutorService executorService, TrainService trainService) {
         this.trainService = trainService;
        this.executorService = executorService;
    }

    @Override
    public Future<String> getEntities() {
        System.out.println("here");
        return executorService.submit(() -> this.trainService.getAllEntities().stream()
                .map(Train::toString)
                .reduce((a ,b) -> a + "\n" + b).orElse(""));

    }
}
