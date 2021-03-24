package ro.ubb.Ui;

import org.springframework.beans.factory.annotation.Autowired;
import ro.ubb.EntityManagers.IEntityCreator;
import ro.ubb.Model.CustomADT.Pair;
import ro.ubb.Model.Station;
import ro.ubb.Model.Train;
import ro.ubb.Model.TrainsStationsEntity;
import ro.ubb.Services.EntityService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientConsole {
    private final EntityService<Integer, Train> trainService;
    private final EntityService<Integer, Station> stationService;
    private final EntityService<Pair<Integer, Integer>, TrainsStationsEntity<Integer, Integer>> timeTableService;
    @Autowired
    private IEntityCreator<Integer, Station> stationCreator;
    @Autowired
    private IEntityCreator<Pair<Integer, Integer>, TrainsStationsEntity<Integer, Integer>> timeTableCreator;
    @Autowired
    private IEntityCreator<Integer, Train> trainCreator;
    public ClientConsole(EntityService<Integer, Train> trainService, EntityService<Integer, Station> stationService, EntityService<Pair<Integer, Integer>, TrainsStationsEntity<Integer, Integer>> timeTableService) {
        this.trainService = trainService;
        this.stationService = stationService;
        this.timeTableService = timeTableService;
    }


    public void run() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String choice;
        while(true){
            UiPrinter.printMainMenu();
            choice = reader.readLine();
            switch (choice) {
                case UiPrinter.TRAIN_CONSTANT -> {
                    UiPrinter.printTrainMenu();
                    choice = reader.readLine();
                    switch (choice) {
                        case "1" -> this.trainService.readEntities().forEach(System.out::println);
                        case "2" -> this.trainService.createEntity(trainCreator.createEntity());
                        case "3" -> this.trainService.updateEntity(trainCreator.createEntity());
                        case "4" -> this.trainService.deleteEntity(trainCreator.createID());
                        case "x" -> {}
                    }
                }
                case UiPrinter.STATION_CONSTANT -> {
                    UiPrinter.printStationMenu();
                    choice = reader.readLine();
                    switch (choice) {
                        case "1" -> this.stationService.readEntities().forEach(System.out::println);
                        case "2" -> this.stationService.createEntity(stationCreator.createEntity());
                        case "3" -> this.stationService.updateEntity(stationCreator.createEntity());
                        case "4" -> this.stationService.deleteEntity(stationCreator.createID());
                        case "x" -> {}
                    }
                }
                case UiPrinter.TT_CONSTANT -> {
                    UiPrinter.printTimeTablesMenu();
                    choice = reader.readLine();
                    switch (choice) {
                        case "1" -> this.timeTableService.readEntities().forEach(System.out::println);
                        case "2" -> this.timeTableService.createEntity(timeTableCreator.createEntity());
                        case "3" -> this.timeTableService.updateEntity(timeTableCreator.createEntity());
                        case "4" -> this.timeTableService.deleteEntity(timeTableCreator.createID());
                        case "x" -> {}
                    }
                }
                case "x" -> {
                    return;
                }
            }
        }
    }
}
