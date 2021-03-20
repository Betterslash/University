package ro.ubb.ClientServices;

import ro.ubb.CommunicationCommons.CustomEntities.Header;
import ro.ubb.CommunicationCommons.CustomEntities.StatusCodes;
import ro.ubb.CommunicationCommons.Message;
import ro.ubb.Model.CustomADT.Pair;
import ro.ubb.Model.TrainsStationsEntity;
import ro.ubb.TransferServices.ITransferService;
import ro.ubb.tcp.TcpClient;

import java.util.concurrent.CompletableFuture;

public class TimeTableService implements ITransferService<Pair<Integer, Integer>, TrainsStationsEntity<Integer, Integer>> {
    private final TcpClient tcpClient;

    public TimeTableService(TcpClient tcpClient) {
        this.tcpClient = tcpClient;
    }

    @Override
    public CompletableFuture<String> getEntities() {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(new Header(StatusCodes.OK, ITransferService.GET_TT_ENTITIES), "");
            Message response = tcpClient.sendAndReceive(request);
            return response.getBody();
        });
    }

    @Override
    public CompletableFuture<String> addEntity(TrainsStationsEntity<Integer, Integer> entity) {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(new Header(StatusCodes.OK, ITransferService.ADD_TT_ENTITY), entity.csvFileFormat());
            Message response = tcpClient.sendAndReceive(request);
            return response.getBody();
        });
    }

    @Override
    public CompletableFuture<String> deleteEntity(Pair<Integer, Integer> integerIntegerPair) {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(new Header(StatusCodes.OK, ITransferService.DELETE_TT_ENTITY), integerIntegerPair.asCsv() + LINE_SEPARATOR);
            Message response = tcpClient.sendAndReceive(request);
            return response.getBody();
        });
    }

    @Override
    public CompletableFuture<String> updateEntity(TrainsStationsEntity<Integer, Integer> entity) {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(new Header(StatusCodes.OK, ITransferService.UPDATE_TT_ENTITY), entity.csvFileFormat());
            Message response = tcpClient.sendAndReceive(request);
            return response.getBody();
        });
    }
}
