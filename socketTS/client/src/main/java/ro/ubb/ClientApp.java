package ro.ubb;

import ro.ubb.ClientServices.ClientStationService;
import ro.ubb.ClientServices.ClientTrainService;
import ro.ubb.Model.Station;
import ro.ubb.Model.Train;
import ro.ubb.TransferServices.ITransferService;
import ro.ubb.UI.Console;
import ro.ubb.tcp.TcpClient;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientApp {
    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );

        TcpClient tcpClient = new TcpClient(executorService);
        ITransferService<Integer, Train> iTransferService = new ClientTrainService(executorService, tcpClient);
        ITransferService<Integer, Station> stationTransferService = new ClientStationService(executorService, tcpClient);
        Console console = new Console(iTransferService, stationTransferService);
        console.runConsole();
        System.out.println("bye client");

        executorService.shutdown();
    }
}
