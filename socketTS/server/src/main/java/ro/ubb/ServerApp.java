package ro.ubb;

import ro.ubb.CommunicationCommons.CustomEntities.Header;
import ro.ubb.CommunicationCommons.CustomEntities.StatusCodes;
import ro.ubb.CommunicationCommons.Message;
import ro.ubb.Model.Train;
import ro.ubb.ServerTransferServices.TrainTransferService;
import ro.ubb.TransferServices.ITransferService;
import ro.ubb.tcp.TCPServer;

import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ServerApp {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );

        TCPServer tcpServer = new TCPServer(executorService, ITransferService.PORT);
        ITransferService<Train> helloService = new TrainTransferService(executorService);

        tcpServer.addHandler("sayHello", request -> {
            Future<Set<Train>> res = helloService.getEntities();
            try {
                Set<Train> result = res.get();
                String resultInterpretation =
                result.stream()
                        .map(Train::toString)
                        .reduce((acc, e) -> acc + e)
                        .orElse("");
                return new Message(new Header(StatusCodes.OK, ""), resultInterpretation);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message(new Header(StatusCodes.SERVER_ERROR, ""), e.getMessage());
            }
        });


        tcpServer.startServer();


        System.out.println("bye server");
    }
}
