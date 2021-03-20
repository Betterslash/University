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

public class ClientApp {
    public static void main(String[] args) {

        TcpClient tcpClient = new TcpClient();
        ITransferService<Integer, Train> iTransferService = new ClientTrainService(tcpClient);
        ITransferService<Integer, Station> stationTransferService = new ClientStationService(tcpClient);
        ITransferService<Pair<Integer, Integer>, TrainsStationsEntity<Integer, Integer>> ttTransferService = new TimeTableService(tcpClient);
        Console console = new Console(iTransferService, stationTransferService, ttTransferService);
        console.runConsole();
        System.out.println("bye client");

    }
}
