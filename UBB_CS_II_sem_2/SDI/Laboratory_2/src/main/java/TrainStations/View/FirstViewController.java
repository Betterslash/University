package TrainStations.View;

import TrainStations.DBOConnectivityUtils.DboCRUDController;
import TrainStations.DBOConnectivityUtils.TrainsController;
import TrainStations.Model.DTOCustomObj;
import TrainStations.Model.Train;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import lombok.SneakyThrows;
import sun.jvm.hotspot.utilities.Observable;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class FirstViewController implements Initializable {
    public AnchorPane root;
    public TableView<List<DTOCustomObj>> trainTable;
    public TableColumn<Train, Integer> idColumn;
    public TableColumn<Train, String> typeColumn;
    public TableColumn<Train, String> colorColumn;
    public TableColumn<Train, String> fabricationDateColumn;

    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            DboCRUDController trainsController = new TrainsController();
            List<DTOCustomObj> trains = trainsController.executeRead();
            idColumn.setCellValueFactory(cellValue -> new SimpleIntegerProperty(cellValue.getValue().getId()).asObject());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
