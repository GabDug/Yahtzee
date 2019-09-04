import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainFX.fxml"));


        Scene scene = new Scene(root, 700, 500);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());



        primaryStage.setScene(scene);
        primaryStage.setTitle("Yahtzee");
        primaryStage.show();
    }
}