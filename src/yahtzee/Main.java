package yahtzee;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setMinHeight(700);
        primaryStage.setMinWidth(650);
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));

        Scene scene = new Scene(root, 700, 600);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Yahtzee");
        primaryStage.getIcons().add(
                new Image("images/LogoIcon.png"));
        primaryStage.show();
    }
}
