package ro.ubb;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ro.ubb.Model.CustomADT.Pair;
import ro.ubb.Model.Station;
import ro.ubb.Model.Train;
import ro.ubb.Model.TrainsStationsEntity;
import ro.ubb.Services.EntityService;
import ro.ubb.Services.StationService;
import ro.ubb.Services.TimeTableService;
import ro.ubb.Services.TrainService;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context=
                new AnnotationConfigApplicationContext("ro.ubb.Config");
        EntityService<Integer, Station> stationService = context.getBean(StationService.class);
        EntityService<Integer, Train> trainService = context.getBean(TrainService.class);
        EntityService<Pair<Integer, Integer>, TrainsStationsEntity<Integer, Integer>> timeTableService =
                context.getBean(TimeTableService.class);
        stationService.readEntities().forEach(System.out::println);
        trainService.readEntities().forEach(System.out::println);
        timeTableService.readEntities().forEach(System.out::println);
    }
}
