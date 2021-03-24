package ro.ubb.Ui;

import ro.ubb.Model.CustomADT.Pair;
import ro.ubb.Model.Station;
import ro.ubb.Model.Train;
import ro.ubb.Model.TrainsStationsEntity;
import ro.ubb.Services.EntityService;

public class ClientConsole {
    private final EntityService<Integer, Train> trainService;
    private final EntityService<Integer, Station> stationService;
    private final EntityService<Pair<Integer, Integer>, TrainsStationsEntity<Integer, Integer>> timeTableService;

    public ClientConsole(EntityService<Integer, Train> trainService, EntityService<Integer, Station> stationService, EntityService<Pair<Integer, Integer>, TrainsStationsEntity<Integer, Integer>> timeTableService) {
        this.trainService = trainService;
        this.stationService = stationService;
        this.timeTableService = timeTableService;
    }


    public void run(){
        trainService.readEntities().forEach(System.out::println);
    }
}
