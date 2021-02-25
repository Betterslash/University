package TrainStations.View;

import TrainStations.CustomExceptions.MyCustomDTOControllerException;
import TrainStations.DBOConnectivityUtils.DboCRUDController;
import TrainStations.DBOConnectivityUtils.StationsController;
import TrainStations.DBOConnectivityUtils.TrainsController;
import TrainStations.Model.DTOCustomObj;
import TrainStations.Model.Station;

import java.sql.SQLException;
import java.util.List;

public class MainView {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, MyCustomDTOControllerException {
        DboCRUDController trainsController = new TrainsController();
        List<DTOCustomObj> trainsList = trainsController.executeRead();
        trainsList.forEach(System.out::println);

        DboCRUDController stationsController = new StationsController();
        stationsController.executeCreate(new Station(1, "Remaru", 16));
        List<DTOCustomObj> stationsList = stationsController.executeRead();
        stationsList.forEach(System.out::println);
    }
}
