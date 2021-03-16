package ro.ubb;

import ro.ubb.CommunicationCommons.CustomEntities.Header;
import ro.ubb.CommunicationCommons.CustomEntities.StatusCodes;
import ro.ubb.CommunicationCommons.Message;
import ro.ubb.Model.Station;
import ro.ubb.Model.Train;
import ro.ubb.Repository.IRepository;
import ro.ubb.Repository.Repositories.CRUDRepository;
import ro.ubb.Repository.Repositories.CRUDUtils.StationDBOService;
import ro.ubb.Repository.Repositories.CRUDUtils.TrainDBOService;
import ro.ubb.ServerTransferServices.StationTransferService;
import ro.ubb.ServerTransferServices.TrainTransferService;
import ro.ubb.Services.StationService;
import ro.ubb.Services.TrainService;
import ro.ubb.TransferServices.ITransferService;
import ro.ubb.tcp.TCPServer;

import java.util.concurrent.*;

public class ServerApp {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );
        IRepository<Integer, Train> trainIRepository = new CRUDRepository<>(new TrainDBOService());
        TrainService trainService = new TrainService(trainIRepository);
        IRepository<Integer, Station> stationIRepository = new CRUDRepository<>(new StationDBOService());
        StationService stationService = new StationService(stationIRepository);

        TCPServer tcpServer = new TCPServer(executorService, ITransferService.PORT);
        ITransferService<Integer, Train> transferTrainService = new TrainTransferService(executorService, trainService);
        ITransferService<Integer, Station> transferStationService = new StationTransferService(executorService, stationService);

        tcpServer.addHandler(ITransferService.GET_TRAIN_ENTITIES, request -> {
            CompletableFuture<String> res = transferTrainService.getEntities();
            return getResponse(res);
        });
        tcpServer.addHandler(ITransferService.GET_STATION_ENTITIES, request -> {
            CompletableFuture<String> res = transferStationService.getEntities();
            return getResponse(res);
        });
        tcpServer.addHandler(ITransferService.ADD_TRAIN_ENTITY, request -> {
            CompletableFuture<String>res = transferTrainService.addEntity(Train.parseTrain(request.getBody()));
            return getResponse(res);
        });
        tcpServer.addHandler(ITransferService.UPDATE_TRAIN_ENTITY, request -> {
            CompletableFuture<String> res = transferTrainService.updateEntity(Train.parseTrain(request.getBody()));
            return getResponse(res);
        });
        tcpServer.addHandler(ITransferService.DELETE_TRAIN_ENTITY, request -> {
            CompletableFuture<String> res = transferTrainService.deleteEntity(Integer.parseInt(request.getBody()));
            return getResponse(res);
        });
        tcpServer.addHandler(ITransferService.ADD_STATION_ENTITY, request -> {
            CompletableFuture<String>res = transferStationService.addEntity(Station.parseStation(request.getBody()));
            return getResponse(res);
        });
        tcpServer.addHandler(ITransferService.UPDATE_STATION_ENTITY, request -> {
            CompletableFuture<String> res = transferStationService.updateEntity(Station.parseStation(request.getBody()));
            return getResponse(res);
        });
        tcpServer.addHandler(ITransferService.DELETE_STATION_ENTITY, request -> {
            CompletableFuture<String> res = transferStationService.deleteEntity(Integer.parseInt(request.getBody()));
            return getResponse(res);
        });

        tcpServer.startServer();


        System.out.println("Server shuted down...");
    }

    private static Message getResponse(CompletableFuture<String> res) {
        try {
            String response = res.get();
            return new Message(new Header(StatusCodes.OK, ""), response);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return new Message(new Header(StatusCodes.SERVER_ERROR, ""), e.getMessage());
        }
    }
}
