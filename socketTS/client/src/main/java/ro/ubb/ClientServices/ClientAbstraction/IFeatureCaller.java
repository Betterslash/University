package ro.ubb.ClientServices.ClientAbstraction;

import ro.ubb.Model.BaseEntity;
import ro.ubb.tcp.TcpClient;

import java.util.concurrent.CompletableFuture;

public abstract class IFeatureCaller<ID, E extends BaseEntity<ID>> extends AbstractClientTransferService<ID, E>{
    public IFeatureCaller(TcpClient tcpClient, String signature) {
        super(tcpClient, signature);
    }

    public abstract CompletableFuture<String> getTrainsPassingEveryStation();
    public abstract CompletableFuture<String> getMostTraveledStation();
    public abstract CompletableFuture<String> getStationsPassedByEveryTrain();
}
