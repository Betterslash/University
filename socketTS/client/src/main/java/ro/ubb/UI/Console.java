package ro.ubb.UI;

import ro.ubb.Model.CustomADT.Pair;
import ro.ubb.Model.Station;
import ro.ubb.Model.Train;
import ro.ubb.Model.TrainsStationsEntity;
import ro.ubb.TransferServices.ITransferService;
import ro.ubb.UI.EntityManagers.StationCreator;
import ro.ubb.UI.EntityManagers.TimeTableCreator;
import ro.ubb.UI.EntityManagers.TrainCreator;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Console {
    private final ITransferService<Integer, Train> trainTransferService;
    private final ITransferService<Integer, Station> stationTransferService;
    private final ITransferService<Pair<Integer, Integer>, TrainsStationsEntity<Integer, Integer>> ttTransferService;
    private final Scanner scanner = new Scanner(System.in);
    public Console(ITransferService<Integer, Train> iTransferService, ITransferService<Integer, Station> stationTransferService, ITransferService<Pair<Integer, Integer>, TrainsStationsEntity<Integer, Integer>> ttTransferService) {
        this.trainTransferService = iTransferService;
        this.stationTransferService = stationTransferService;
        this.ttTransferService = ttTransferService;
    }
    public void runConsole() {

        String choice;
        Future<String> resultFuture = null;
        TrainCreator trainCreator = new TrainCreator();
        StationCreator stationCreator = new StationCreator();
        TimeTableCreator timeTableCreator = new TimeTableCreator();
        boolean running = true;
        while (running) {
            UIPrinter.printMainMenu();
            choice = scanner.nextLine();
            switch (choice) {
                case UIPrinter.TRAIN_CONSTANT -> {
                    UIPrinter.printTrainMenu();
                    choice = scanner.nextLine();
                    switch (choice) {
                        case "1" -> resultFuture = this.trainTransferService.getEntities();
                        case "2" -> resultFuture = this.trainTransferService.addEntity(trainCreator.createEntity());
                        case "4" -> resultFuture = this.trainTransferService.deleteEntity(trainCreator.createID());
                        case "3" -> resultFuture = this.trainTransferService.updateEntity(trainCreator.createEntity());
                        default -> throw new IllegalStateException("Unexpected value: " + choice);
                    }
                }
                case UIPrinter.STATION_CONSTANT -> {
                    UIPrinter.printStationMenu();
                    choice = scanner.nextLine();
                    switch (choice) {
                        case "1" -> resultFuture = this.stationTransferService.getEntities();
                        case "2" -> resultFuture = this.stationTransferService.addEntity(stationCreator.createEntity());
                        case "4" -> resultFuture = this.stationTransferService.deleteEntity(stationCreator.createID());
                        case "3" -> resultFuture = this.stationTransferService.updateEntity(stationCreator.createEntity());
                        default -> throw new IllegalStateException("Unexpected value: " + choice);
                    }
                }
                case UIPrinter.TT_CONSTANT -> {
                    UIPrinter.printTimeTablesMenu();
                    choice = scanner.nextLine();
                    switch (choice) {
                        case "1" -> resultFuture = this.ttTransferService.getEntities();
                        case "2" -> resultFuture = this.ttTransferService.addEntity(timeTableCreator.createEntity());
                        case "4" -> resultFuture = this.ttTransferService.deleteEntity(timeTableCreator.createID());
                        case "3" -> resultFuture = this.ttTransferService.updateEntity(timeTableCreator.createEntity());
                        default -> throw new IllegalStateException("Unexpected value: " + choice);
                    }
                }
                case UIPrinter.EXIT_VALUE -> running = false;
            }
            try {
                assert resultFuture != null;
                System.out.println(resultFuture.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
