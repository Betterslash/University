package TrainStations.View;

import TrainStations.DBOConnectivityUtils.DboController;

import java.sql.SQLException;

public class MainView {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DboController dboController = new DboController();
        dboController.execute();
        System.out.println("Hello World!");
    }
}
