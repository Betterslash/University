package View;

import Controller.Controller;
import Model.PrgState;
import Model.Values.Value;
import Model.adt.MyTPair;
import Model.except.MyException;
import Model.stmt.IStmt;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class PrgRunController implements Initializable {

    Controller myController;
    @FXML
    Button oneStepButton;
    @FXML
    TableView<HashMap.Entry<Integer, Value>> heapTable;
    @FXML
    TableColumn<HashMap.Entry<Integer, Value>, String> heapTableAdress;
    @FXML
    TableColumn<HashMap.Entry<Integer, Value>, String> heapTableValue;
    @FXML
    TableColumn<HashMap.Entry<String, Value>, String> symTableId;
    @FXML
    TableColumn<HashMap.Entry<String, Value>, String> symTableValue;
    @FXML
    ListView<String> outputList;
    @FXML
    ListView<String> prgStateList;
    @FXML
    TableView<HashMap.Entry<String, Value>> symTable;
    @FXML
    ListView<String> exeStackList;
    @FXML
    Label numberLabel;
    @FXML
    GridPane mainStage;
    @FXML
    ListView<String> fileTable;
    @FXML
    TableView<Map.Entry<Integer, MyTPair<Integer, List<Integer>, Integer>>> semaphoreTable;
    @FXML
    TableColumn<Map.Entry<Integer, MyTPair<Integer, List<Integer>, Integer>>, String> semId;
    @FXML
    TableColumn<Map.Entry<Integer, MyTPair<Integer, List<Integer>, Integer>>, String> semVal;

    public PrgRunController(Controller myController) {
        this.myController = myController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initialRun();
        prgStateList.setOnMouseClicked(event -> setSymTableAndExeStack());
        oneStepButton.setOnAction(e -> {
            try {
                myController.allStep();
                //myController.getRepository().setProgramList(myController.removeCompleted(myController.getRepository().getPrgList()));
                if(myController.getRepository().getPrgList().size() == 0){
                    throw new MyException("Finished!");
                }else{
                    updateUIComponents();
                }
            } catch (InterruptedException | MyException e1) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Toy Language - Current program finished");
                alert.setHeaderText(null);
                alert.setContentText("Program successfully finished!");
                Button confirm = (Button) alert.getDialogPane().lookupButton( ButtonType.OK );
                confirm.setDefaultButton(false);
                confirm.setStyle("-fx-focus-color: transparent; -fx-faint-focus-color: transparent;");
                alert.showAndWait();
                mainStage.getScene().getWindow().hide();
            }
        });
    }

    public void initialRun() {
        setNumberLabel();
        setHeapTable();
        setOutList();
        setPrgStateList();
        setFileTable();
        prgStateList.getSelectionModel().selectFirst();
        setSymTableAndExeStack();
    }

    private void setNumberLabel() {
        numberLabel.setText(Integer.toString(myController.getRepository().getPrgList().size()));
    }

    public void updateUIComponents() {
        setNumberLabel();
        setSemaphoreTable();
        setHeapTable();
        setOutList();
        setFileTable();
        setPrgStateList();
        if(prgStateList.getSelectionModel().getSelectedItem() == null) {
            prgStateList.getSelectionModel().selectFirst();
        }
        setSymTableAndExeStack();
    }

    private void setFileTable() {
        ObservableList<String> fileObservableList = FXCollections.observableArrayList();
        fileObservableList.addAll(myController.getRepository().getPrgList().get(0).getFileTable().getRepresentation().keySet());
        fileTable.refresh();
        fileTable.setItems(fileObservableList);
    }

    public void setHeapTable() {
        ObservableList<HashMap.Entry<Integer, Value>> heapTableList = FXCollections.observableArrayList();
        heapTableAdress.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(Integer.toString(cellData.getValue().getKey())));
        heapTableValue.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getValue().toString()));
        heapTableList.addAll(myController.getRepository().getPrgList().get(0).getHeap().getRepresentation().entrySet());
        heapTable.refresh();
        heapTable.setItems(heapTableList);
    }
    public void setSemaphoreTable() {
        ObservableList<Map.Entry<Integer, MyTPair<Integer, List<Integer>, Integer>>> semList = FXCollections.observableArrayList();
        semId.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(Integer.toString(cellData.getValue().getKey())));
        semVal.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getValue().toString()));
        semList.addAll(myController.getRepository().getPrgList().get(0).getSemaphoreTable().getRepresentation().entrySet());
        semaphoreTable.refresh();
        semaphoreTable.setItems(semList);
    }

    public void setOutList() {
        ObservableList<String> outObservableList = FXCollections.observableArrayList();
        for(Value e : myController.getRepository().getPrgList().get(0).getOut().getList()) {
            outObservableList.add(e.toString());
        }
        outputList.setItems(outObservableList);
    }


    public void setPrgStateList() {
        ObservableList<String> prgStateIdList = FXCollections.observableArrayList();
        for(PrgState e : myController.getRepository().getPrgList()) {
            prgStateIdList.add(Integer.toString(e.getId()));
        }
        prgStateList.setItems(prgStateIdList);
    }

    public void setSymTableAndExeStack() {
        ObservableList<HashMap.Entry<String, Value>> symTableList = FXCollections.observableArrayList();
        ObservableList<String> exeStackItemsList = FXCollections.observableArrayList();
        symTableId.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getKey()));
        symTableValue.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getValue().toString()));
        List<PrgState> allPrgs = myController.getRepository().getPrgList();
        PrgState prgResult = null;
        for(PrgState e : allPrgs) {
            if(e.getId() == Integer.parseInt(prgStateList.getSelectionModel().getSelectedItem())) {
                prgResult = e;
                break;
            }
        }
        if(prgResult != null) {
            symTableList.addAll(prgResult.getSymTable().getRepresentation().entrySet());
            for(IStmt e : prgResult.getExeStack().getStack()) {
                exeStackItemsList.add(e.toString());
            }
            symTable.refresh();
            symTable.setItems(symTableList);
            exeStackList.refresh();
            exeStackList.setItems(exeStackItemsList);
        }
    }

}
