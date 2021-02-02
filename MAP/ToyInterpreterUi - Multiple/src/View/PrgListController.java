package View;

import Controller.Controller;
import Model.PrgState;
import Model.Procedure;
import Model.Types.IntType;
import Model.Types.RefType;
import Model.Values.IntValue;
import Model.Values.StringValue;
import Model.Values.Value;
import Model.adt.MyDict;
import Model.except.ExpressionException;
import Model.except.MyException;
import Model.except.TypeCheckException;
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
import java.util.*;

public class PrgListController implements Initializable {

    static Repository repository0;
    static Repository repository1;
    static Repository repository2;
    static Repository repository3;
    static Repository repository4;
    static Repository repository5;
    static Repository repository6;
    static Repository repository7;
    static Controller controller0;
    static Controller controller1;
    static Controller controller2;
    static Controller controller3;
    static Controller controller4;
    static Controller controller5;
    static Controller controller6;
    static Controller controller7;
    public Button addProcedureButton;
    public ListView<Procedure> procedureListView;

    @FXML
    private ListView<Controller> programsListView;
    @FXML
    private Button chooseProgram;


    public void typecheckSetUp(List<IStmt> statementsList){
        statementsList.forEach(e -> {
            try {
                e.typecheck(new MyDict<>());
            } catch (MyException | TypeCheckException | ExpressionException myException) {
                myException.printStackTrace();
            }
        });
    }

    public void setUp() {
        IStmt ex0 = new CompStmt(
                new CompStmt(new VarDeclStmt("v1", new RefType(new IntType())),new VarDeclStmt("cnt", new IntType())),
                new CompStmt(new HeapAllocStmt("v1", new ValueExp(new IntValue(1))),
                        new CompStmt(new CreateSemaphoreStmt("cnt", new ReadHeapExp(new VarExp("v1"))),
                                new CompStmt(new ForkStmt(new CompStmt(new AcquireSemaphoreStmt("cnt"),
                                        new CompStmt(new HeapWriteStmt("v1", new ArithmExp('*', new ReadHeapExp(new VarExp("v1")), new ValueExp(new IntValue(10)))),
                                                new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v1"))),
                                                        new ReleaseSemaphoreStmt("cnt")))
                                )),new CompStmt(
                                        new ForkStmt(new CompStmt(new AcquireSemaphoreStmt("cnt"),
                                                new CompStmt(new HeapWriteStmt("v1", new ArithmExp('*', new ReadHeapExp(new VarExp("v1")), new ValueExp(new IntValue(10)))),
                                                        new CompStmt(new CompStmt(new HeapWriteStmt("v1", new ArithmExp('*', new ReadHeapExp(new VarExp("v1")), new ValueExp(new IntValue(2)))),
                                                                new PrintStmt(new ReadHeapExp(new VarExp("v1")))),
                                                                new ReleaseSemaphoreStmt("cnt")))
                                        )),
                                        new CompStmt(new CompStmt(new AcquireSemaphoreStmt("cnt"),
                                                new PrintStmt(new ArithmExp('-', new ReadHeapExp(new VarExp("v1")), new ValueExp(new IntValue(1))))),
                                                new ReleaseSemaphoreStmt("cnt")))))
                ));
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
        IStmt exP = new CompStmt(new CompStmt(
                new VarDeclStmt("v" , new IntType()), new AssignStmt("v", new ValueExp(new IntValue(2)))),
                new CompStmt(new CompStmt(new VarDeclStmt("w", new IntType()), new AssignStmt("w", new ValueExp(new IntValue(5)))),
                        new CompStmt(new CompStmt(new CallProcStmt("sum", Arrays.asList(new ArithmExp('*', new VarExp("v"), new ValueExp(new IntValue(10))), new VarExp("w"))), new PrintStmt(new VarExp("v"))),
                                new CompStmt(new ForkStmt(new CompStmt(new CallProcStmt("product", Arrays.asList(new VarExp("v"), new VarExp("w"))),
                                        new ForkStmt(new CallProcStmt("sum", Arrays.asList(new VarExp("v"), new VarExp("w")))))),new NoStmt()))));
        IStmt swStmt =
                new CompStmt(new VarDeclStmt("a", new IntType()),
                        new CompStmt(new VarDeclStmt("b", new IntType()),
                                new CompStmt(new VarDeclStmt("c", new IntType()),
                                        new CompStmt(new AssignStmt("a", new ValueExp(new IntValue(1))),
                                                new CompStmt(new AssignStmt("b", new ValueExp(new IntValue(2))),
                                                        new CompStmt(new AssignStmt("c", new ValueExp(new IntValue(5))),
                                                                new CompStmt(new SwitchStmt(new ArithmExp('*', new VarExp("a"), new ValueExp(new IntValue(10))),
                                                                                            new ArithmExp('*', new VarExp("b"), new VarExp("c")),
                                                                                            new ValueExp(new IntValue(10)),
                                                                                            new CompStmt(new PrintStmt(new VarExp("a")), new PrintStmt(new VarExp("b"))),
                                                                                            new CompStmt(new PrintStmt(new ValueExp(new IntValue(100))), new PrintStmt(new ValueExp(new IntValue(200)))),
                                                                                            new PrintStmt(new ValueExp(new IntValue(300)))),
                                                                        new PrintStmt(new ValueExp(new IntValue(300))))))))
                        ));
        IStmt forStmt = new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
                new CompStmt(new HeapAllocStmt("a", new ValueExp(new IntValue(20))),
                        new CompStmt(new ForStmt("v",
                                new ValueExp(new IntValue(0)),
                                new ValueExp(new IntValue(3)),
                                new ArithmExp('+', new VarExp("v"), new ValueExp(new IntValue(1))),
                                new CompStmt(new ForkStmt(new CompStmt(new PrintStmt(new VarExp("v")),
                                        new AssignStmt("v", new ArithmExp('*',new VarExp("v"),new ReadHeapExp(new VarExp("a")))))),
                                        new NoStmt())
                ),new PrintStmt(new ReadHeapExp(new VarExp("a"))))));
        List<IStmt> typeCheckStmtList = Arrays.asList(ex0, ex1, ex2, ex3, ex4, exP);
        this.typecheckSetUp(typeCheckStmtList);

        PrgState prgState0 = new PrgState( ex0);
        PrgState prgState1 = new PrgState( ex1);
        PrgState prgState2 = new PrgState( ex2);
        PrgState prgState3 = new PrgState( ex3);
        PrgState prgState4 = new PrgState( ex4);
        PrgState prgState5 = new PrgState( exP);
        PrgState prgState6 = new PrgState( swStmt);
        PrgState prgState7 = new PrgState( forStmt);

        repository0 = new Repository("out0.out");
        repository1 = new Repository("out1.out");
        repository2 = new Repository("out2.out");
        repository3 = new Repository("out3.out");
        repository4 = new Repository("out4.out");
        repository5 = new Repository("outP.out");
        repository6 = new Repository("outSw.out");
        repository7 = new Repository("outFor.out");

        repository0.addToRepository(prgState0);
        repository1.addToRepository(prgState1);
        repository2.addToRepository(prgState2);
        repository3.addToRepository(prgState3);
        repository4.addToRepository(prgState4);
        repository5.addToRepository(prgState5);
        repository6.addToRepository(prgState6);
        repository7.addToRepository(prgState7);

        controller0 = new Controller(repository0);
        controller1 = new Controller(repository1);
        controller2 = new Controller(repository2);
        controller3 = new Controller(repository3);
        controller4 = new Controller(repository4);
        controller5 = new Controller(repository5);
        controller6 = new Controller(repository6);
        controller7 = new Controller(repository7);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUp();
        ObservableList<Controller> myData = FXCollections.observableArrayList();
        myData.add(controller5);
        myData.add(controller0);
        myData.add(controller1);
        myData.add(controller2);
        myData.add(controller3);
        myData.add(controller4);
        myData.add(controller6);
        myData.add(controller7);
        programsListView.setItems(myData);
        programsListView.getSelectionModel().selectFirst();
        ObservableList<Procedure> procData = FXCollections.observableArrayList();
        String procName = "sum";
        List<String> params = Arrays.asList("a","b");
        IStmt procStmt = new CompStmt(new AssignStmt("v", new ArithmExp('+', new VarExp("a"), new VarExp("b"))), new PrintStmt(new VarExp("v")));
        Procedure procedure1 = new Procedure(procName, params, procStmt);
        String procName1 = "product";
        List<String> params1 = Arrays.asList("a","b");
        IStmt procStmt1 = new CompStmt(new AssignStmt("v", new ArithmExp('*', new VarExp("a"), new VarExp("b"))), new PrintStmt(new VarExp("v")));
        Procedure procedure2 = new Procedure(procName1, params1, procStmt1);
        procData.add(procedure1);
        procData.add(procedure2);
        procedureListView.setItems(procData);
        this.procedureListView.getSelectionModel().selectFirst();
        chooseProgram.setOnAction(e -> {
            Stage programStage = new Stage();
            Parent programRoot;
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-window.fxml"));
                if(programsListView.getSelectionModel().getSelectedItem().getRepository().getPrgList().size() > 0) {
                    PrgRunController mainWindowController = new PrgRunController(programsListView.getSelectionModel().getSelectedItem());
                    fxmlLoader.setController(mainWindowController);
                    programRoot = fxmlLoader.load();
                    Scene scene = new Scene(programRoot);
                    programStage.setScene(scene);
                    programStage.show();
                }else{
                    alertUser();
                }
            } catch (IOException ioException) {
                alertUser();
            }

        });
        addProcedureButton.setOnAction(e->{
            this.programsListView.getSelectionModel().getSelectedItem().addProcedure(this.procedureListView.getSelectionModel().getSelectedItem());
            this.programsListView.refresh();
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
