package ro.ubb.tcp;

import lombok.Data;
import ro.ubb.CommunicationCommons.CustomEntities.Header;
import ro.ubb.CommunicationCommons.CustomEntities.StatusCodes;
import ro.ubb.CommunicationCommons.Message;
import ro.ubb.Model.CustomADT.Pair;
import ro.ubb.Model.Exceptions.ServerExceptions.TCPServerException;
import ro.ubb.Model.Station;
import ro.ubb.Model.Train;
import ro.ubb.Model.TrainsStationsEntity;
import ro.ubb.Model.Parsers.StationParser;
import ro.ubb.Model.Parsers.TimeTableParser;
import ro.ubb.Model.Parsers.TrainParser;
import ro.ubb.ServerTransferServices.ServerAbstraction.AbstractFServerTService;
import ro.ubb.ServerTransferServices.ServerAbstraction.AbstractServerTService;
import ro.ubb.ServerTransferServices.StationTService;
import ro.ubb.ServerTransferServices.TimeTableTransferService;
import ro.ubb.ServerTransferServices.TrainTService;
import ro.ubb.Services.StationService;
import ro.ubb.Services.TrainService;
import ro.ubb.TransferServices.ITransferService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.UnaryOperator;

@Data
public class TCPServer {
    public static final ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private final int port;
    private static final Map<String, UnaryOperator<Message>> methodHandlers  = new HashMap<>();
    private final AbstractServerTService<Integer, Train> transferTrainService;
    private final AbstractServerTService<Integer, Station> transferStationService;
    private final AbstractFServerTService<Pair<Integer, Integer>, TrainsStationsEntity<Integer, Integer>> ttTransferService;
    public TCPServer( int port) {
        this.transferTrainService = new TrainTService();
        this.transferStationService = new StationTService();
        this.ttTransferService = new TimeTableTransferService((TrainService) this.transferTrainService.getService(),(StationService) this.transferStationService.getService());
        this.port = port;
    }

    public void initializeHandlers(){
        new CRUDInitializer<Integer, Train>().initialize(methodHandlers, this.transferTrainService, new TrainParser());
        new CRUDInitializer<Integer, Station>().initialize(methodHandlers, this.transferStationService, new StationParser());
        new CRUDInitializer<Pair<Integer, Integer>, TrainsStationsEntity<Integer, Integer>>().initialize(methodHandlers, this.ttTransferService, new TimeTableParser());
        methodHandlers.put(ITransferService.GET_TRAINS_PASSING_EVERY_STATION, request -> {
            CompletableFuture<String> response = this.ttTransferService.getTrainsPassingEveryStation();
            return CompletableFuture.supplyAsync(() ->getResponse(response), executor).join();
        });
        methodHandlers.put(ITransferService.GET_STATIONS_PASSED_BY_EVERY_TRAIN, request -> {
            CompletableFuture<String> response = this.ttTransferService.getStationsPassedByEveryTrain();
            return CompletableFuture.supplyAsync(() ->getResponse(response), executor).join();
        });
        methodHandlers.put(ITransferService.GET_MOST_TRAVELED_STATION, request -> {
            CompletableFuture<String> response = this.ttTransferService.getMostTraveledStation();
            return CompletableFuture.supplyAsync(() ->getResponse(response), executor).join();
        });
    }
    public static Message getResponse(CompletableFuture<String> res) {
        String response;
        try {
            response = res.get();
            return new Message(new Header(StatusCodes.OK, "Succes"), response);
        } catch (InterruptedException | ExecutionException e) {
            return new Message(new Header(StatusCodes.SERVER_ERROR, "ServerFailure"), e.getMessage());
        }
    }

    public void startServer() {
        try (var serverSocket = new ServerSocket(this.port)) {
            System.out.println("Server started, waiting for clients...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("client connected");
                CompletableFuture.runAsync(new ClientHandler(clientSocket), executor).join();
            }

        } catch (IOException e) {
            executor.shutdown();
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        private final Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (var is = socket.getInputStream();
                 var os  = socket.getOutputStream()) {

                //read the request (of type Message) from client
                Message request = new Message();
                request.readFrom(is);
                System.out.println("received request: " + request);

                // compute response (of type Message)
                Message response = methodHandlers.get(request.getHeader().getMethodName()).apply(request);
                System.out.println("computed response: " + response);

                //send response (of type Message) to client
                response.writeTo(os);
                System.out.println("response sent to client");

            } catch (Exception e) {
                throw new TCPServerException(e.getMessage());
            }
        }
    }
}