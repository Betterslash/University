package sample;

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
import java.util.ResourceBundle;

public class PrgListController implements Initializable {

    static Repository repository0, repository1, repository2, repository3, repository4, repository5;
    static Controller controller0, controller1, controller2, controller3, controller4, controller5;

    @FXML
    private ListView<Controller> programsListView;
    @FXML
    private Button chooseProgram;

    public void setUp() {
        IStmt varDecla = new CompStmt(new VarDeclStmt("v1", new RefType(new IntType())),
                new HeapAllocStmt("v1", new ValueExp(new IntValue(2))));
        IStmt varDecl2 = new CompStmt(new VarDeclStmt("v2", new RefType(new IntType())),
                new HeapAllocStmt("v2", new ValueExp(new IntValue(3))));
        IStmt varDecl3 = new CompStmt(new VarDeclStmt("v3", new RefType(new IntType())),
                new HeapAllocStmt("v3", new ValueExp(new IntValue(4))));
        IStmt declars = new CompStmt(varDecla, new CompStmt(varDecl2, varDecl3));
        IStmt latchDecl = new CompStmt(new VarDeclStmt("cnt", new IntType()),new NewCountdownLatchStmt("cnt", new ReadHeapExp(new VarExp("v2"))));
        IStmt firstFork = new ForkStmt(new CompStmt(
                new HeapWriteStmt("v3", new ArithmExp('*', new ReadHeapExp(new VarExp("v3")), new ValueExp(new IntValue(10)))),
                new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v3"))), new CountDownStmt("cnt"))
                ));
        IStmt secondFork = new ForkStmt(new CompStmt(new CompStmt(
                new HeapWriteStmt("v2", new ArithmExp('*', new ReadHeapExp(new VarExp("v2")), new ValueExp(new IntValue(10)))),
                new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v2"))), new CountDownStmt("cnt"))
        ),
                firstFork));
        IStmt thirdFork = new ForkStmt(new CompStmt(new CompStmt(
                new HeapWriteStmt("v1", new ArithmExp('*', new ReadHeapExp(new VarExp("v1")), new ValueExp(new IntValue(10)))),
                new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v1"))), new CountDownStmt("cnt"))
        ),
                secondFork));
        IStmt ex0 = new CompStmt(declars,
                new CompStmt(latchDecl, new CompStmt(thirdFork,
                        new CompStmt(new AwaitSmt("cnt"),
                                new CompStmt(new PrintStmt(new ValueExp(new IntValue(100))),
                                        new CompStmt(new CountDownStmt("cnt"),
                                        new PrintStmt(new ValueExp(new IntValue(100)))
                        ))))));
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
        IStmt ex6 = new CompStmt(new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(20))),
                new ForStmt(new VarExp("v"),
                        new ValueExp(new IntValue(0)),
                        new ValueExp(new IntValue(3)),
                        new ArithmExp('+', new VarExp("v"), new ValueExp(new IntValue(1))),
                        new ForkStmt(new CompStmt(new PrintStmt(new VarExp("v")),
                                new AssignStmt("v", new ArithmExp('+', new VarExp("v"), new ValueExp(new IntValue(1))))))))),
                new PrintStmt(new ArithmExp('*',new VarExp("v"), new ValueExp(new IntValue(10)))));

        IStmt ex7 = new CompStmt(new VarDeclStmt("a", new IntType()),
                new CompStmt(new VarDeclStmt("b", new IntType()),
                        new CompStmt(new VarDeclStmt("c", new IntType()),
                                new CompStmt(new AssignStmt("a", new ValueExp(new IntValue(1))),
                                        new CompStmt(new AssignStmt("b", new ValueExp(new IntValue(2))),
                                                new CompStmt(new AssignStmt("c", new ValueExp(new IntValue(5))),
                                                        new CompStmt(new SwitchStmt(new ArithmExp('*', new VarExp("a"), new ValueExp(new IntValue(10))),
                                                                new CompStmt(new PrintStmt(new VarExp("a")), new PrintStmt(new VarExp("b"))),
                                                                new ArithmExp('*', new VarExp("b"), new VarExp("c")),
                                                                new CompStmt(new PrintStmt(new ValueExp(new IntValue(100))), new PrintStmt(new ValueExp(new IntValue(200)))),
                                                                new ValueExp(new IntValue(10)),
                                                                new PrintStmt(new ValueExp(new IntValue(300)))),
                                                                new PrintStmt(new ValueExp(new IntValue(300))))))))));


        check(ex0, ex1, ex2);
        check(ex3, ex4, ex5);

        PrgState prgState0 = new PrgState(ex0);
        PrgState prgState1 = new PrgState(ex1);
        PrgState prgState2 = new PrgState(ex2);
        PrgState prgState3 = new PrgState(ex3);
        PrgState prgState4 = new PrgState(ex4);
        PrgState prgState5 = new PrgState(ex7);

        repository0 = new Repository("out0.out");
        repository1 = new Repository("out1.out");
        repository2 = new Repository("out2.out");
        repository3 = new Repository("out3.out");
        repository4 = new Repository("out4.out");
        repository5 = new Repository("out5.out");

        repository0.addToRepository(prgState0);
        repository1.addToRepository(prgState1);
        repository2.addToRepository(prgState2);
        repository3.addToRepository(prgState3);
        repository4.addToRepository(prgState4);
        repository5.addToRepository(prgState5);

        controller0 = new Controller(repository0);
        controller1 = new Controller(repository1);
        controller2 = new Controller(repository2);
        controller3 = new Controller(repository3);
        controller4 = new Controller(repository4);
        controller5 = new Controller(repository5);
    }

    private void check(IStmt ex0, IStmt ex1, IStmt ex2) {
        try {
            ex0.typecheck(new MyDict<>());
        } catch (MyException e) {
            e.printStackTrace();
        }
        try {
            ex1.typecheck(new MyDict<>());
        } catch (MyException e) {
            e.printStackTrace();
        }
        try {
            ex2.typecheck(new MyDict<>());
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUp();
        ObservableList<Controller> myData = FXCollections.observableArrayList();
        myData.add(controller0);
        myData.add(controller1);
        myData.add(controller2);
        myData.add(controller3);
        myData.add(controller4);
        myData.add(controller5);
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
