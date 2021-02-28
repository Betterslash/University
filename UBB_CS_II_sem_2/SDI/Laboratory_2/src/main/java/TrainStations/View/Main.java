package TrainStations.View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        AnchorPane loader = FXMLLoader.load(Main.class.getResource("first_view.fxml"));
        Scene scene = new Scene(loader);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Trains");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
