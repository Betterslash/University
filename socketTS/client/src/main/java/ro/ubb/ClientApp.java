package ro.ubb;

import ro.ubb.ClientServices.ClientAbstraction.AbstractClientTransferService;
import ro.ubb.ClientServices.ClientAbstraction.IFeatureCaller;
import ro.ubb.ClientServices.ClientStationService;
import ro.ubb.ClientServices.ClientTrainService;
import ro.ubb.ClientServices.TimeTableService;
import ro.ubb.Model.CustomADT.Pair;
import ro.ubb.Model.Parsers.StationParser;
import ro.ubb.Model.Parsers.TimeTableParser;
import ro.ubb.Model.Parsers.TrainParser;
import ro.ubb.Model.Station;
import ro.ubb.Model.Train;
import ro.ubb.Model.TrainsStationsEntity;
import ro.ubb.UI.Console;
import ro.ubb.tcp.TcpClient;

import static ro.ubb.TransferServices.ServerAbstractions.AbstractTransferServices.*;

public class ClientApp {
    public static void main(String[] args) {

        TcpClient tcpClient = new TcpClient();
        AbstractClientTransferService<Integer, Train> iTransferService = new ClientTrainService(tcpClient, TRAIN_SIGNATURE, new TrainParser());
        AbstractClientTransferService<Integer, Station> stationTransferService = new ClientStationService(tcpClient, STATION_SIGNATURE, new StationParser());
        IFeatureCaller<Pair<Integer, Integer>, TrainsStationsEntity<Integer, Integer>> ttTransferService = new TimeTableService(tcpClient, TIME_TABLE_SIGNATURE, new TimeTableParser());
        Console console = new Console(iTransferService, stationTransferService, ttTransferService);
        console.runConsole();
        System.out.println("bye client");

    }
}
