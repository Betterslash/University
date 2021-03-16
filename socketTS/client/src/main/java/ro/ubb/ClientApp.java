package ro.ubb;

import ro.ubb.ClientServices.ClientStationService;
import ro.ubb.ClientServices.ClientTrainService;
import ro.ubb.ClientServices.TimeTableService;
import ro.ubb.Model.CustomADT.Pair;
import ro.ubb.Model.Station;
import ro.ubb.Model.Train;
import ro.ubb.Model.TrainsStationsEntity;
import ro.ubb.TransferServices.ITransferService;
import ro.ubb.UI.Console;
import ro.ubb.tcp.TcpClient;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientApp {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );

        TcpClient tcpClient = new TcpClient(executorService);
        ITransferService<Integer, Train> iTransferService = new ClientTrainService(executorService, tcpClient);
        ITransferService<Integer, Station> stationTransferService = new ClientStationService(executorService, tcpClient);
        ITransferService<Pair<Integer, Integer>, TrainsStationsEntity<Integer, Integer>> ttTransferService = new TimeTableService(executorService, tcpClient);
        Console console = new Console(iTransferService, stationTransferService, ttTransferService);
        console.runConsole();
        System.out.println("bye client");

        executorService.shutdown();
    }
}
