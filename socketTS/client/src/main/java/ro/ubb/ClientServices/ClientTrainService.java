package ro.ubb.ClientServices;

import ro.ubb.CommunicationCommons.CustomEntities.Header;
import ro.ubb.CommunicationCommons.CustomEntities.StatusCodes;
import ro.ubb.CommunicationCommons.Message;
import ro.ubb.Model.Train;
import ro.ubb.TransferServices.ITransferService;
import ro.ubb.tcp.TcpClient;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public class ClientTrainService implements ITransferService<Integer, Train> {
    private final ExecutorService executorService;
    private final TcpClient tcpClient;
    public ClientTrainService(ExecutorService executorService, TcpClient tcpClient) {
        this.executorService = executorService;
        this.tcpClient = tcpClient;
    }

    @Override
    public CompletableFuture<String> getEntities() {
        return CompletableFuture.supplyAsync(() -> {
                    Message request = new Message();
                    request.setHeader(new Header(StatusCodes.OK, ITransferService.GET_TRAIN_ENTITIES));
                    request.setBody("");
                    Message res = tcpClient.sendAndReceive(request);
                    return res.getBody();
                });
    }

    @Override
    public CompletableFuture<String> addEntity(Train entity) {
        return CompletableFuture.supplyAsync(() -> {
            Message req = new Message();
            req.setHeader(new Header(StatusCodes.OK, ITransferService.ADD_TRAIN_ENTITY));
            req.setBody(entity.csvFileFormat());
            Message res = tcpClient.sendAndReceive(req);
            return res.getBody();
        });
    }

    @Override
    public CompletableFuture<String> deleteEntity(Integer integer) {
        return CompletableFuture.supplyAsync(() -> {
            Message req = new Message();
            req.setBody(integer.toString() + LINE_SEPARATOR);
            req.setHeader(new Header(StatusCodes.OK, ITransferService.DELETE_TRAIN_ENTITY));
            Message res = tcpClient.sendAndReceive(req);
            return res.getBody();
        });
    }

    @Override
    public CompletableFuture<String> updateEntity(Train entity) {
        return CompletableFuture.supplyAsync(() -> {
            Message req = new Message();
            req.setBody(entity.csvFileFormat());
            req.setHeader(new Header(StatusCodes.OK, ITransferService.UPDATE_TRAIN_ENTITY));
            Message res = tcpClient.sendAndReceive(req);
            return res.getBody();
        });
    }

    @Override
    public CompletableFuture<String> getTrainsPassingEveryStation() {
        return null;
    }

    @Override
    public CompletableFuture<String> getMostTraveledStation() {
        return null;
    }

    @Override
    public CompletableFuture<String> getStationsPassedByEveryTrain() {
        return null;
    }
}
