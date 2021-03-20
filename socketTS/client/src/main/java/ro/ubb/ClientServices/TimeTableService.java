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

    /**
     *
     * @returns all objects of type TimeTable
     */
    @Override
    public CompletableFuture<String> getEntities() {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(new Header(StatusCodes.OK, ITransferService.GET_TT_ENTITIES), "");
            Message response = tcpClient.sendAndReceive(request);
            return response.getBody();
        });
    }

    /**
     * adds an entity
     * @param entity
     * @returns message based on the success of the execution
     */
    @Override
    public CompletableFuture<String> addEntity(TrainsStationsEntity<Integer, Integer> entity) {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(new Header(StatusCodes.OK, ITransferService.ADD_TT_ENTITY), entity.csvFileFormat());
            Message response = tcpClient.sendAndReceive(request);
            return response.getBody();
        });
    }

    /**
     * deletes an entity
     * @param integerIntegerPair
     * @returns message based on the success of the execution
     */
    @Override
    public CompletableFuture<String> deleteEntity(Pair<Integer, Integer> integerIntegerPair) {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(new Header(StatusCodes.OK, ITransferService.DELETE_TT_ENTITY), integerIntegerPair.asCsv() + LINE_SEPARATOR);
            Message response = tcpClient.sendAndReceive(request);
            return response.getBody();
        });
    }

    /**
     * updates an entity
     * @param entity
     * @returns message based on the success of the execution
     */
    @Override
    public CompletableFuture<String> updateEntity(TrainsStationsEntity<Integer, Integer> entity) {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(new Header(StatusCodes.OK, ITransferService.UPDATE_TT_ENTITY), entity.csvFileFormat());
            Message response = tcpClient.sendAndReceive(request);
            return response.getBody();
        });
    }

    /**
     *
     * @returns message containing the trains that are passing every station
     */
    @Override
    public CompletableFuture<String> getTrainsPassingEveryStation() {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(new Header(StatusCodes.OK, ITransferService.GET_TRAINS_PASSING_EVERY_STATION), "");
            Message response = tcpClient.sendAndReceive(request);
            return response.getBody();
        });
    }

    /**
     *
     * @returns message containing the most traveled station
     */
    @Override
    public CompletableFuture<String> getMostTraveledStation() {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(new Header(StatusCodes.OK, ITransferService.GET_MOST_TRAVELED_STATION), "");
            Message response = tcpClient.sendAndReceive(request);
            return response.getBody();
        });
    }

    /**
     *
     * @returns message containing the stations that are passed by every train
     */
    @Override
    public CompletableFuture<String> getStationsPassedByEveryTrain() {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(new Header(StatusCodes.OK, ITransferService.GET_STATIONS_PASSED_BY_EVERY_TRAIN), "");
            Message response = tcpClient.sendAndReceive(request);
            return response.getBody();
        });
    }

}
