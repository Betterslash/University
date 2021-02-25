package TrainStations.DBOConnectivityUtils;

import TrainStations.CustomExceptions.MyCustomDTOControllerException;
import TrainStations.Model.DTOCustomObj;
import TrainStations.Model.Station;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StationsController implements DboCRUDController {
    @Override
    public void executeCreate(DTOCustomObj dtoCustomObj) throws ClassNotFoundException, SQLException, MyCustomDTOControllerException {
        if(dtoCustomObj instanceof Station) {
            Station station = (Station) dtoCustomObj;
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver);
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/trains_stations?characterEncoding=utf8", "root", "root");
            Statement stmt = con.createStatement();
            String command = "INSERT INTO stations(station_name, population_rate) VALUES ";
            command += "('" + station.getName() + "', " + station.getPopulationRate() + ");";
            System.out.println(command);
            stmt.execute(command);
            con.close();
        }else {
            throw new MyCustomDTOControllerException("You need to provide a Station!");
        }
    }

    @Override
    public List<DTOCustomObj> executeRead() throws SQLException, ClassNotFoundException {
        List<DTOCustomObj> returnList = new ArrayList<>();
        String driver = "com.mysql.jdbc.Driver";
        Class.forName(driver);
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/trains_stations?characterEncoding=utf8", "root", "root");
        Statement stmt = con.createStatement();
        String command = "SELECT * FROM stations";
        ResultSet resultSet = stmt.executeQuery(command);
        while(resultSet.next()){
            returnList.add(new Station(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getInt(3)));
        }
        con.close();
        return returnList;
    }
}
