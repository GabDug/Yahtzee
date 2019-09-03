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
        // FXMLLoader loader = new FXMLLoader();
        //  loader.setLocation(getClass().getResource("MainFX.fxml"));
        // loader.setControllerFactory(t -> new GameEngineFX());

        Parent root = FXMLLoader.load(getClass().getResource("MainFX.fxml"));

        Scene scene = new Scene(root, 700, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Yahtzee");
        primaryStage.show();

        // Scene scene = primaryStage.getScene();
        // Image img = (Image) scene.lookup("#myBtnID");
    }
}