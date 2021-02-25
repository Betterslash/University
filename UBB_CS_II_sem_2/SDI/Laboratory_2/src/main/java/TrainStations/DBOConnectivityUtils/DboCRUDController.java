package TrainStations.DBOConnectivityUtils;

import TrainStations.CustomExceptions.MyCustomDTOControllerException;
import TrainStations.Model.DTOCustomObj;

import java.sql.SQLException;
import java.util.List;

public interface DboCRUDController {
    void executeCreate(DTOCustomObj dtoCustomObj) throws ClassNotFoundException, SQLException, MyCustomDTOControllerException;
    List<DTOCustomObj> executeRead() throws SQLException, ClassNotFoundException;
}
