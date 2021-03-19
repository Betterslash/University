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
import ro.ubb.ServerTransferServices.StationTransferService;
import ro.ubb.ServerTransferServices.TimeTableTransferService;
import ro.ubb.ServerTransferServices.TrainTransferService;
import ro.ubb.TransferServices.ITransferService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.function.UnaryOperator;

@Data
public class TCPServer {

    private final ExecutorService executorService;
    private final int port;
    private final Map<String, UnaryOperator<Message>> methodHandlers;
    private final ITransferService<Integer, Train> transferTrainService;
    private final ITransferService<Integer, Station> transferStationService;
    private final ITransferService<Pair<Integer, Integer>, TrainsStationsEntity<Integer, Integer>> ttTransferService;
    public TCPServer(ExecutorService executorService, int port) {
        this.transferTrainService = new TrainTransferService(executorService);
        this.transferStationService = new StationTransferService(executorService);
        this.ttTransferService = new TimeTableTransferService(executorService);
        this.executorService = executorService;
        this.port = port;
        this.methodHandlers = new HashMap<>();
    }
    public void initializeHandlers(){
        this.methodHandlers.put(ITransferService.GET_TRAIN_ENTITIES, request -> {
            CompletableFuture<String> res = transferTrainService.getEntities();
            return getResponse(res);
        });
        this.methodHandlers.put(ITransferService.GET_STATION_ENTITIES, request -> {
            CompletableFuture<String> res = transferStationService.getEntities();
            return getResponse(res);
        });
        this.methodHandlers.put(ITransferService.ADD_TRAIN_ENTITY, request -> {
            CompletableFuture<String>res = transferTrainService.addEntity(Train.parseTrain(request.getBody()));
            return getResponse(res);
        });
        this.methodHandlers.put(ITransferService.UPDATE_TRAIN_ENTITY, request -> {
            CompletableFuture<String> res = transferTrainService.updateEntity(Train.parseTrain(request.getBody()));
            return getResponse(res);
        });
        this.methodHandlers.put(ITransferService.DELETE_TRAIN_ENTITY, request -> {
            CompletableFuture<String> res = transferTrainService.deleteEntity(Integer.parseInt(request.getBody().strip()));
            return getResponse(res);
        });
        this.methodHandlers.put(ITransferService.ADD_STATION_ENTITY, request -> {
            CompletableFuture<String>res = transferStationService.addEntity(Station.parseStation(request.getBody()));
            return getResponse(res);
        });
        this.methodHandlers.put(ITransferService.UPDATE_STATION_ENTITY, request -> {
            CompletableFuture<String> res = transferStationService.updateEntity(Station.parseStation(request.getBody()));
            return getResponse(res);
        });
        this.methodHandlers.put(ITransferService.DELETE_STATION_ENTITY, request -> {
            CompletableFuture<String> res = transferStationService.deleteEntity(Integer.parseInt(request.getBody().strip()));
            return getResponse(res);
        });
        this.methodHandlers.put(ITransferService.GET_TT_ENTITIES, request -> {
            CompletableFuture<String> response = this.ttTransferService.getEntities();
            return getResponse(response);
        });
        this.methodHandlers.put(ITransferService.ADD_TT_ENTITY, request -> {
            CompletableFuture<String> response = this.ttTransferService.addEntity(TrainsStationsEntity.parseTimeTable(request.getBody()));
            return getResponse(response);
        });
        this.methodHandlers.put(ITransferService.DELETE_TT_ENTITY, request -> {
            CompletableFuture<String> response = this.ttTransferService.deleteEntity(Pair.parsePair(request.getBody().strip()));
            return getResponse(response);
        });
        this.methodHandlers.put(ITransferService.UPDATE_TT_ENTITY, request -> {
            CompletableFuture<String> response = this.ttTransferService.updateEntity(TrainsStationsEntity.parseTimeTable(request.getBody()));
            return getResponse(response);
        });
    }
    private static Message getResponse(CompletableFuture<String> res){
        String response = res.join();
        return new Message(new Header(StatusCodes.OK, "Succes"), response);
    }

    public void startServer() {
        try (var serverSocket = new ServerSocket(this.port)) {
            System.out.println("Server started, waiting for clients...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("client connected");
                CompletableFuture.runAsync(new ClientHandler(clientSocket));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ClientHandler implements Runnable {
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
                throw new TCPServerException("Server failed to send data...");
            }
        }
    }
}
