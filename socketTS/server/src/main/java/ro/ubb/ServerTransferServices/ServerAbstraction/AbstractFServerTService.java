package ro.ubb.ServerTransferServices.ServerAbstraction;

import ro.ubb.Model.BaseEntity;
import ro.ubb.Services.ServiceAbstractions.Service;

import java.util.concurrent.CompletableFuture;

public abstract class AbstractFServerTService<ID, E extends BaseEntity<ID>> extends AbstractServerTService<ID, E> {
    protected AbstractFServerTService(Service<ID , E> service, String signature) {
        super(service, signature);
    }
    public abstract CompletableFuture<String> getTrainsPassingEveryStation();
    public abstract CompletableFuture<String> getMostTraveledStation();
    public abstract CompletableFuture<String> getStationsPassedByEveryTrain();
}
