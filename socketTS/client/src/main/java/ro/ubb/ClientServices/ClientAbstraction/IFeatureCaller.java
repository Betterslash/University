package ro.ubb.ClientServices.ClientAbstraction;

import ro.ubb.Model.BaseEntity;
import ro.ubb.Model.Parsers.IParser;
import ro.ubb.tcp.TcpClient;

import java.util.concurrent.CompletableFuture;

public abstract class IFeatureCaller<ID, E extends BaseEntity<ID>> extends AbstractClientTransferService<ID, E>{
    public IFeatureCaller(TcpClient tcpClient, String signature, IParser<ID, E> parser) {
        super(tcpClient, signature, parser);
    }

    public abstract CompletableFuture<String> getTrainsPassingEveryStation();
    public abstract CompletableFuture<String> getMostTraveledStation();
    public abstract CompletableFuture<String> getStationsPassedByEveryTrain();
}
