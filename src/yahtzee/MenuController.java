package yahtzee;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;


public class MenuController {
    public Button singlePlayerButton;
    public Button multiPlayerButton;
    public Button exitButton;
    public Spinner spinner;
    public Button multiPlayerLaunchButton;

    public void initialize() {
        // Hide the spinner, used to select player number, and the launch multiplayer button
        // They will be sjown after the "Multiplayer" button is clicked.
        // setVisible is to hide the element, setManaged is to "remove" it from parent node
        // Thus, it will not take blank space
        spinner.setVisible(false);
        spinner.setManaged(false);
        multiPlayerLaunchButton.setVisible(false);
        multiPlayerLaunchButton.setManaged(false);
    }

    private void launchGame(ActionEvent event, int playerNumber) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
        // We assign the controller here, and not in the FXML file, so we can pass the number of players to the game
        GameController controller = new GameController(playerNumber);
        loader.setController(controller);

        GridPane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert pane != null;
        Scene newgame_scene = new Scene(pane);
        newgame_scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(newgame_scene);
        app_stage.show();
    }

    public void singlePlayer(ActionEvent event) {
        launchGame(event, 1);
    }

    public void multiPlayer(ActionEvent event) {
        spinner.setVisible(true);
        spinner.setManaged(true);
        multiPlayerLaunchButton.setVisible(true);
        multiPlayerLaunchButton.setManaged(true);
    }

    public void multiPlayerLaunch(ActionEvent event) {
        launchGame(event, (Integer) spinner.getValue());
    }

    public void exit(ActionEvent actionEvent) {
        Platform.exit();
    }
}
