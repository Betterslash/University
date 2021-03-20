package ro.ubb.ClientServices;

import ro.ubb.CommunicationCommons.CustomEntities.Header;
import ro.ubb.CommunicationCommons.CustomEntities.StatusCodes;
import ro.ubb.CommunicationCommons.Message;
import ro.ubb.Model.Station;
import ro.ubb.TransferServices.ITransferService;
import ro.ubb.tcp.TcpClient;

import java.util.concurrent.CompletableFuture;

public class ClientStationService implements ITransferService<Integer, Station> {
    private final TcpClient tcpClient;

    public ClientStationService(TcpClient tcpClient) {
        this.tcpClient = tcpClient;
    }

    /**
     *
     * @returns all objects of type Station
     */
    @Override
    public CompletableFuture<String> getEntities() {
        return CompletableFuture.supplyAsync(() ->{
            Message request = new Message(new Header(StatusCodes.OK, ITransferService.GET_STATION_ENTITIES), "");
            Message response = this.tcpClient.sendAndReceive(request);
            return response.getBody();
        });
    }

    /**
     * adds an entity
     * @param entity
     * @returns message based on the success of the execution
     */
    @Override
    public CompletableFuture<String> addEntity(Station entity) {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(new Header(StatusCodes.OK, ITransferService.ADD_STATION_ENTITY), entity.csvFileFormat());
            Message response = this.tcpClient.sendAndReceive(request);
            return response.getBody();
        });
    }

    /**
     * deletes an entity
     * @param integer
     * @returns message based on the success of the execution
     */
    @Override
    public CompletableFuture<String> deleteEntity(Integer integer) {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(new Header(StatusCodes.OK, ITransferService.DELETE_STATION_ENTITY), integer.toString() + LINE_SEPARATOR);
            Message response = this.tcpClient.sendAndReceive(request);
            return response.getBody();
        });
    }

    /**
     * updates an entity
     * @param entity
     * @returns message based on the success of the execution
     */
    @Override
    public CompletableFuture<String> updateEntity(Station entity) {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(new Header(StatusCodes.OK, ITransferService.UPDATE_STATION_ENTITY), entity.csvFileFormat());
            Message response = this.tcpClient.sendAndReceive(request);
            return response.getBody();
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
