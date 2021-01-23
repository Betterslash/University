package View;

import Controller.Controller;
import Model.PrgState;
import Model.Types.IntType;
import Model.Types.RefType;
import Model.Values.IntValue;
import Model.Values.StringValue;
import Model.adt.MyDict;
import Model.except.MyException;
import Model.exp.*;
import Model.stmt.*;
import Repository.Repository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class PrgListController implements Initializable {

    static Repository repository1;
    static Repository repository2;
    static Repository repository3;
    static Repository repository4;
    static Controller controller1;
    static Controller controller2;
    static Controller controller3;
    static Controller controller4;

    @FXML
    private ListView<Controller> programsListView;
    @FXML
    private Button chooseProgram;

    public void typecheckSetUp(List<IStmt> statementsList){
        statementsList.forEach(e -> {
            try {
                e.typecheck(new MyDict<>());
            } catch (MyException myException) {
                myException.printStackTrace();
            }
        });
    }

    public void setUp() {
        IStmt ex1 = new CompStmt(new VarDeclStmt("a", new IntType()),
                new CompStmt(new VarDeclStmt("b", new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithmExp('+', new ValueExp(new IntValue(2)),
                                new ArithmExp('*', new ValueExp(new IntValue(3)),
                                        new ValueExp(new IntValue(5))))),
                                new CompStmt(new AssignStmt("b", new ArithmExp('+', new VarExp("a"),
                                        new ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));
        VarDeclStmt v = new VarDeclStmt("v", new RefType(new IntType()));
        HeapAllocStmt heapAllocStmt = new HeapAllocStmt("v", new ValueExp(new IntValue(20)));
        VarDeclStmt rdc = new VarDeclStmt("a", new RefType(new RefType(new IntType())));
        HeapAllocStmt rfc = new HeapAllocStmt("a", new VarExp("v"));
        HeapAllocStmt rfc1 = new HeapAllocStmt("v", new ValueExp(new IntValue(30)));
        CompStmt cms = new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v"))), new PrintStmt(new ReadHeapExp(new ReadHeapExp(new VarExp("a")))));
        IStmt ex2 = new CompStmt(v, new CompStmt(heapAllocStmt, new CompStmt(rdc, new CompStmt(rfc, new CompStmt(rfc1, cms)))));
        IStmt ex3 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new CompStmt(new HeapAllocStmt("v", new ValueExp(new IntValue(20))),
                        new HeapAllocStmt("v", new ValueExp(new IntValue(33)))),
                        new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v"))),
                                new CompStmt(new CompStmt(new HeapWriteStmt("v", new ValueExp(new IntValue(30))),
                                        new HeapAllocStmt("v", new ValueExp(new IntValue(40)))),
                                        new PrintStmt(new ArithmExp('+', new ValueExp(new IntValue(5)), new ReadHeapExp(new VarExp("v"))))))));
        IStmt ex4 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(4))), new CompStmt(
                        new WhileStmt(new RelationalExp(">", new VarExp("v"), new ValueExp(new IntValue(0))),
                                new CompStmt(
                                        new ForkStmt(new PrintStmt(new VarExp("v"))),
                                        new AssignStmt("v", new ArithmExp('-',
                                                new VarExp("v"),
                                                new ValueExp(new IntValue(1))))
                                )), new PrintStmt(new VarExp("v")))));
        IStmt ex5 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
                        new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))),
                                new CompStmt(new HeapAllocStmt("a", new ValueExp(new IntValue(22))),
                                        new CompStmt(new ForkStmt(new ForkStmt(new CompStmt(new HeapWriteStmt("a", new ValueExp(new IntValue(30))),
                                                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))),
                                                        new ForkStmt(new CompStmt(new PrintStmt(new VarExp("v")),
                                                                new PrintStmt(new ReadHeapExp(new VarExp("a"))))))))), new CompStmt(new PrintStmt(new VarExp("v")),
                                                new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("a"))),
                                                        new CompStmt(new OpenRFile(new ValueExp(new StringValue("data.in"))),
                                                                new CloseRFile(new ValueExp(new StringValue("data.in")))))))))));
        List<IStmt> typeCheckStmtList = Arrays.asList(ex1, ex2, ex3, ex4, ex5);
        this.typecheckSetUp(typeCheckStmtList);

        PrgState prgState1 = new PrgState(ex1);
        PrgState prgState2 = new PrgState(ex2);
        PrgState prgState3 = new PrgState(ex3);
        PrgState prgState4 = new PrgState(ex4);


        repository1 = new Repository("out1.out");
        repository2 = new Repository("out2.out");
        repository3 = new Repository("out3.out");
        repository4 = new Repository("out4.out");


        repository1.addToRepository(prgState1);
        repository2.addToRepository(prgState2);
        repository3.addToRepository(prgState3);
        repository4.addToRepository(prgState4);


        controller1 = new Controller(repository1);
        controller2 = new Controller(repository2);
        controller3 = new Controller(repository3);
        controller4 = new Controller(repository4);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUp();
        ObservableList<Controller> myData = FXCollections.observableArrayList();
        myData.add(controller1);
        myData.add(controller2);
        myData.add(controller3);
        myData.add(controller4);
        programsListView.setItems(myData);
        programsListView.getSelectionModel().selectFirst();
        chooseProgram.setOnAction(e -> {
            Stage programStage = new Stage();
            Parent programRoot;
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-window.fxml"));
                if(programsListView.getSelectionModel().getSelectedItem().getRepository().getPrgList().size() > 0) {
                    PrgRunController mainWindowController = new PrgRunController(programsListView.getSelectionModel().getSelectedItem());
                    fxmlLoader.setController(mainWindowController);
                    programRoot = fxmlLoader.load();
                    Scene scene = new Scene(programRoot, 1300, 720);
                    programStage.setScene(scene);
                    programStage.show();
                }else{
                    alertUser();
                }
            } catch (IOException ioException) {
                alertUser();
            }

        });
    }

    private void alertUser() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Toy Language - Already finished!");
        alert.setHeaderText(null);
        alert.setContentText("Program already finished!");
        Button confirm = (Button) alert.getDialogPane().lookupButton( ButtonType.OK );
        confirm.setDefaultButton(false);
        confirm.setStyle("-fx-focus-color: transparent; -fx-faint-focus-color: transparent;");
        alert.showAndWait();
    }
}
