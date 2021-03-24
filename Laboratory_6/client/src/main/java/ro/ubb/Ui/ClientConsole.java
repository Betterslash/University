package ro.ubb.Ui;

import ro.ubb.Model.CustomADT.Pair;
import ro.ubb.Model.Station;
import ro.ubb.Model.Train;
import ro.ubb.Model.TrainsStationsEntity;
import ro.ubb.Services.EntityService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ClientConsole {
    private final EntityService<Integer, Train> trainService;
    private final EntityService<Integer, Station> stationService;
    private final EntityService<Pair<Integer, Integer>, TrainsStationsEntity<Integer, Integer>> timeTableService;

    public ClientConsole(EntityService<Integer, Train> trainService, EntityService<Integer, Station> stationService, EntityService<Pair<Integer, Integer>, TrainsStationsEntity<Integer, Integer>> timeTableService) {
        this.trainService = trainService;
        this.stationService = stationService;
        this.timeTableService = timeTableService;
    }


    public void run() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String choice = reader.readLine();
        switch (choice){
            case UIPrinter.TRAIN_CONSTANT:
                this.trainService.readEntities().forEach(System.out::println);
                break;
            case UIPrinter.STATION_CONSTANT:
                this.stationService.readEntities().forEach(System.out::println);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + choice);
        }
    }
}
