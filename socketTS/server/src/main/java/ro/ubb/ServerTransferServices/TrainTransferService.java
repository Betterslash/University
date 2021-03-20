package ro.ubb.ServerTransferServices;

import ro.ubb.Model.Train;
import ro.ubb.Repository.IRepository;
import ro.ubb.Repository.Repositories.CRUDRepository;
import ro.ubb.Repository.Repositories.CRUDUtils.TrainDBOService;
import ro.ubb.Services.TrainService;
import ro.ubb.TransferServices.ServerAbstractions.AbstractTransferServices;

import java.util.concurrent.CompletableFuture;

public class TrainTransferService extends AbstractTransferServices<Integer, Train> {
    private final TrainService trainService;
    public TrainTransferService() {
        super(TRAIN_SIGNATURE);
        IRepository<Integer, Train> trainIRepository = new CRUDRepository<>(new TrainDBOService());
        this.trainService = new TrainService(trainIRepository);
    }

    /**
     *
     * @returns all objects of type Train
     */
    @Override
    public CompletableFuture<String> getEntities() {
        return CompletableFuture.supplyAsync(() -> this.trainService.getAllEntities().stream()
                .map(Train::toString)
                .reduce((a ,b) -> a + "\n" + b).orElse(""));
    }

    /**
     * adds an entity
     * @param entity entity to be added
     * @returns message based on the success of the execution
     */
    @Override
    public CompletableFuture<String> addEntity(Train entity) {
        return CompletableFuture.supplyAsync(() -> {
            this.trainService.executeCreate(entity);
            return "Train " + entity.toString() + " was added !";});
    }

    /**
     * deletes an entity
     * @param integer id for deletion
     * @returns message based on the success of the execution
     */
    @Override
    public CompletableFuture<String> deleteEntity(Integer integer) {

        return CompletableFuture.supplyAsync(() ->
        {   this.trainService.executeDelete(integer);
            return "Train with id " + integer + " was deleted !";
        });
    }

    /**
     * updates an entity
     * @param entity enityt to be updated
     * @returns message based on the success of the execution
     */
    @Override
    public CompletableFuture<String> updateEntity(Train entity) {
        return CompletableFuture.supplyAsync(() -> {this.trainService.executeUpdate(entity);
        return "Train " + entity.toString() + " was updated !";});
    }
}
