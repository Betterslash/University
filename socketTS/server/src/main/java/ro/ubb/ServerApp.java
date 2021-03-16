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

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ServerApp {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );
        IRepository<Integer, Train> trainIRepository = new CRUDRepository<>(new TrainDBOService());
        TrainService trainService = new TrainService(trainIRepository);
        TCPServer tcpServer = new TCPServer(executorService, ITransferService.PORT);
        ITransferService<Integer, Train> helloService = new TrainTransferService(executorService, trainService);

        tcpServer.addHandler("getEntities", request -> {
            Future<String> res = helloService.getEntities();
            try {
                String response = res.get();
                return new Message(new Header(StatusCodes.OK, ""), response);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message(new Header(StatusCodes.SERVER_ERROR, ""), e.getMessage());
            }
        });


        tcpServer.startServer();


        System.out.println("bye server");
    }
}
