package TrainStations.DBOConnectivityUtils;

import TrainStations.Model.DTOCustomObj;
import TrainStations.Model.Train;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrainsController implements DboCRUDController{
    public void executeCreate(DTOCustomObj dtoCustomObj) throws SQLException, ClassNotFoundException {
        Train train = (Train) dtoCustomObj;
        Class.forName("com.mysql.jdbc.Driver");
        Connection con= DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/trains_stations?characterEncoding=utf8","root","root");
        Statement stmt=con.createStatement();
        String command = "INSERT INTO trains(train_type, train_color, fabrication_date) VALUES ";
        command += "('" + train.getType() +"', '" + train.getColor() + "', '" + train.getFabricationDate() +"');";
        System.out.println(command);
        stmt.execute(command);
        con.close();
    }

    @Override
    public List<DTOCustomObj> executeRead() throws SQLException, ClassNotFoundException {
        List<DTOCustomObj> returnList = new ArrayList<>();
        String driver = "com.mysql.jdbc.Driver";
        Class.forName(driver);
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/trains_stations?characterEncoding=utf8", "root", "root");
        Statement stmt = con.createStatement();
        String command = "SELECT * FROM trains";
        System.out.println(command);
        ResultSet resultSet = stmt.executeQuery(command);
        while(resultSet.next()){
            returnList.add(new Train(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)));
        }
        con.close();
        return returnList;
    }
}
