package ro.ubb.ServerTransferServices;

import ro.ubb.Model.CustomADT.Pair;
import ro.ubb.Model.Station;
import ro.ubb.Model.Train;
import ro.ubb.Model.TrainsStationsEntity;
import ro.ubb.Repository.Repositories.CRUDRepository;
import ro.ubb.Repository.Repositories.CRUDUtils.TimeTableDBOService;
import ro.ubb.ServerTransferServices.ServerAbstraction.AbstractFServerTService;
import ro.ubb.Services.StationService;
import ro.ubb.Services.TrainService;
import ro.ubb.Services.TrainsStationsService;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class TimeTableTransferService extends AbstractFServerTService<Pair<Integer, Integer>, TrainsStationsEntity<Integer, Integer>> {
    private static TrainsStationsService ttService;
    public TimeTableTransferService(TrainService trainService, StationService stationService) {
        super(ttService = new TrainsStationsService(new CRUDRepository<>(new TimeTableDBOService()), trainService, stationService), TIME_TABLE_SIGNATURE);
    }

    /**
     *
     * @returns message containing the trains that are passing every station
     */
    @Override
    public CompletableFuture<String> getTrainsPassingEveryStation() {
        return CompletableFuture.supplyAsync(() -> {
            Set<Train> trains = ttService.getTrainsPassingEveryStation();
            return "Trains passing every station are: " + trains.toString();
        });
    }

    /**
     *
     * @returns message containing the most traveled station
     */
    @Override
    public CompletableFuture<String> getMostTraveledStation() {
        return CompletableFuture.supplyAsync(() -> {
            Set<Station> stations = ttService.getMostTraveledStation();
            return "Most traveled station is: " +stations.toString();
        });
    }

    /**
     *
     * @returns message containing the stations that are passed by every train
     */
    @Override
    public CompletableFuture<String> getStationsPassedByEveryTrain() {
        return CompletableFuture.supplyAsync(() -> {
            Set<Station> stations = ttService.getStationsPassedByEveryTrain();
            return "Stations passed by every train are: " + stations.toString();
        });
    }

}
