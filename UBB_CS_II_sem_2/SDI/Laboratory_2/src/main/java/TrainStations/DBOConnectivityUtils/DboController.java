package TrainStations.DBOConnectivityUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DboController {
    public void execute() throws SQLException, ClassNotFoundException {
        String url = "jdbc:sqlserver://192.168.1.2:1433;databaseName=DbTrainsStations;integratedSecurity=true";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection(url);
    }
}
