package ro.ubb.TransferServices.ServerAbstractions;

import ro.ubb.Model.BaseEntity;

import java.util.concurrent.CompletableFuture;

public abstract class AbstractFeaturesTransferService<ID, E extends BaseEntity<ID>> extends AbstractServerTransferServices<ID, E> {
    protected AbstractFeaturesTransferService(String service_signature) {
        super(service_signature);
    }
    public abstract CompletableFuture<String> getTrainsPassingEveryStation();
    public abstract CompletableFuture<String> getMostTraveledStation();
    public abstract CompletableFuture<String> getStationsPassedByEveryTrain();
}
