package ro.ubb;

import ro.ubb.CommunicationCommons.CustomEntities.Header;
import ro.ubb.CommunicationCommons.CustomEntities.StatusCodes;
import ro.ubb.CommunicationCommons.Message;
import ro.ubb.Model.Train;
import ro.ubb.Repository.IRepository;
import ro.ubb.Repository.Repositories.CRUDRepository;
import ro.ubb.Repository.Repositories.CRUDUtils.TrainDBOService;
import ro.ubb.ServerTransferServices.TrainTransferService;
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
        TCPServer tcpServer = new TCPServer(executorService, ITransferService.PORT);
        ITransferService<Integer, Train> helloService = new TrainTransferService(executorService, trainService);

        tcpServer.addHandler(ITransferService.GET_ENTITIES, request -> {
            CompletableFuture<String> res = helloService.getEntities();
            return getResponse(res);
        });
        tcpServer.addHandler(ITransferService.ADD_ENTITY, request -> {
            CompletableFuture<String>res = helloService.addEntity(Train.parseTrain(request.getBody()));
            return getResponse(res);
        });
        tcpServer.addHandler(ITransferService.UPDATE_ENTITY, request -> {
            CompletableFuture<String> res = helloService.updateEntity(Train.parseTrain(request.getBody()));
            return getResponse(res);
        });
        tcpServer.addHandler(ITransferService.DELETE_ENTITY, request -> {
            CompletableFuture<String> res = helloService.deleteEntity(Integer.parseInt(request.getBody()));
            return getResponse(res);
        });


        tcpServer.startServer();


        System.out.println("bye server");
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
