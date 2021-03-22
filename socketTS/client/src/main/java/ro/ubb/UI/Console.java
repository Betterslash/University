package ro.ubb.UI;

import ro.ubb.ClientServices.ClientAbstraction.AbstractClientTransferService;
import ro.ubb.ClientServices.ClientAbstraction.IFeatureCaller;
import ro.ubb.Model.CustomADT.Pair;
import ro.ubb.Model.Station;
import ro.ubb.Model.Train;
import ro.ubb.Model.TrainsStationsEntity;
import ro.ubb.UI.EntityManagers.StationCreator;
import ro.ubb.UI.EntityManagers.TimeTableCreator;
import ro.ubb.UI.EntityManagers.TrainCreator;

import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

public class Console {
    private final AbstractClientTransferService<Integer, Train> trainTransferService;
    private final AbstractClientTransferService<Integer, Station> stationTransferService;
    private final IFeatureCaller<Pair<Integer, Integer>, TrainsStationsEntity<Integer, Integer>> ttTransferService;
    private final Scanner scanner = new Scanner(System.in);
    public Console(AbstractClientTransferService<Integer, Train> iTransferService,
                   AbstractClientTransferService<Integer, Station> stationTransferService,
                   IFeatureCaller<Pair<Integer, Integer>, TrainsStationsEntity<Integer, Integer>> ttTransferService) {
        this.trainTransferService = iTransferService;
        this.stationTransferService = stationTransferService;
        this.ttTransferService = ttTransferService;
    }
    public void runConsole() {

        String choice;
        CompletableFuture<String> resultFuture = null;
        TrainCreator trainCreator = new TrainCreator();
        StationCreator stationCreator = new StationCreator();
        TimeTableCreator timeTableCreator = new TimeTableCreator();
        boolean running = true;
        while (running) {
            UIPrinter.printMainMenu();
            choice = scanner.nextLine();
            try{
                switch (choice) {
                    case UIPrinter.TRAIN_CONSTANT -> {
                        UIPrinter.printTrainMenu();
                        choice = scanner.nextLine();
                        switch (choice) {
                            case "1" -> resultFuture = CompletableFuture.supplyAsync(() -> this.trainTransferService.getEntities().join().stream()
                                    .map(Train::toString)
                                    .reduce((a, b) -> a + "\n" + b )
                                    .orElseThrow());
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
                            case "1" -> resultFuture = CompletableFuture.supplyAsync(() -> this.stationTransferService.getEntities().join().stream()
                                    .map(Station::toString)
                                    .reduce((a, b) -> a + "\n" + b )
                                    .orElseThrow());
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
                            case "1" -> resultFuture = CompletableFuture.supplyAsync(() -> this.ttTransferService.getEntities().join()
                            .stream()
                                    .map(TrainsStationsEntity::toString)
                                    .reduce((a, b) -> a + "\n" + b )
                                    .orElseThrow());
                            case "2" -> resultFuture = this.ttTransferService.addEntity(timeTableCreator.createEntity());
                            case "4" -> resultFuture = this.ttTransferService.deleteEntity(timeTableCreator.createID());
                            case "3" -> resultFuture = this.ttTransferService.updateEntity(timeTableCreator.createEntity());
                            default -> throw new IllegalStateException("Unexpected value: " + choice);
                        }
                    }
                    case UIPrinter.FEATURE_CONSTANT -> {
                        UIPrinter.printFeatureMenu();
                        choice = scanner.nextLine();
                        switch (choice){
                            case "1" -> resultFuture = this.ttTransferService.getTrainsPassingEveryStation();
                            case "2" -> resultFuture = this.ttTransferService.getStationsPassedByEveryTrain();
                            case "3" -> resultFuture = this.ttTransferService.getMostTraveledStation();
                            default -> throw new IllegalStateException("Unexpected value: " + choice);
                        }
                    }
                    case UIPrinter.EXIT_VALUE -> running = false;
                    default -> throw new IllegalStateException("Next time please give a valid option!");
                }
                assert resultFuture != null;
                System.out.println(resultFuture.join());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
