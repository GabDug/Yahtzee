package yahtzee;

import com.sun.istack.internal.NotNull;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import yahtzee.game.Rules;
import yahtzee.game.Score;
import yahtzee.table.ScoreRow;

import java.io.IOException;
import java.util.Optional;

public class GameController {
    private static final int MAX_SCORE_NAME = 16;
    private static int MAX_PLAYERS = 2;
    private final Image[] img = new Image[6];
    public TableView tableView;
    public TableColumn player1;
    public TableColumn scoreName;
    public Button reRollButton;
    public GridPane rollingDice;
    public GridPane keptDice;
    public Label title;
    @FXML
    private Text scoreLabel;
    @FXML
    private ImageView dice1;
    @FXML
    private ImageView dice2;
    @FXML
    private ImageView dice3;
    @FXML
    private ImageView dice4;
    @FXML
    private ImageView dice5;
    private GameEngine gfx;

    public GameController(int playerNumber) {
        MAX_PLAYERS = playerNumber;
    }

    public void initialize() {
        System.out.println("YAHTZEE STARTED!");
        this.gfx = new GameEngine(MAX_PLAYERS);

        img[0] = new Image("resources/dieWhite1.png");
        img[1] = new Image("resources/dieWhite2.png");
        img[2] = new Image("resources/dieWhite3.png");
        img[3] = new Image("resources/dieWhite4.png");
        img[4] = new Image("resources/dieWhite5.png");
        img[5] = new Image("resources/dieWhite6.png");

        // updateDices(); // Put random dices from the beginning
        updateThrowLeft();
        // Add a scoreboard with only labels, not actual score!
        updateScore();

        /*
        Taken and modified from https://stackoverflow.com/questions/12499269/javafx-tableview-detect-a-doubleclick-on-a-cell
        The cell factory allows us to handle click (and double click) events on a single cell, as well as setting
        formatting rules for each cell (e.g. color)
        */
        Callback<TableColumn, TableCell> cellFactory =
                new Callback<TableColumn, TableCell>() {
                    public TableCell call(TableColumn p) {
                        TableCell<ScoreRow, String> cell = new TableCell<ScoreRow, String>() {
                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                // "R" is appended to text supposed to be red, and is thus deleted before printing
                                if (item != null && item.contains("R")) {
                                    setStyle("-fx-text-fill: red;");
                                } else if (item != null) {
                                    setStyle("-fx-text-fill: black;");
                                }
                                setText("Y");
                                setText(empty ? null : getString().replace("R", ""));
                                setGraphic(null);
                            }

                            private String getString() {
                                return getItem() == null ? "" : getItem();
                            }
                        };

                        // Add the click event
                        cell.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                            if (event.getClickCount() > 1) {
                                TableCell c = (TableCell) event.getSource();
                                System.out.println("DEBUG Row Clicked " + c.getTableRow().getIndex());
                                if (c.getTableRow().getIndex() < MAX_SCORE_NAME) {
                                    clickCell(c.getTableRow().getIndex());
                                } else {
                                    // If too much row are printed, they can't be clicked.
                                    System.out.println("DEBUG Cell is not clickable!");
                                }
                            }
                        });
                        return cell;
                    }
                };

        // Set columns to take full width of table
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        // Set the CellFactory to allow format and events (click) on cells
        player1.setCellFactory(cellFactory);
        // Disable sorting not to mess with our indexes!
        player1.setSortable(false);
        scoreName.setSortable(false);

        for (int player = 1; player < MAX_PLAYERS; player++) {
            TableColumn playerN = new TableColumn<>("P" + (player + 1));
            playerN.setCellValueFactory(new PropertyValueFactory<>("player" + (player + 1)));
            playerN.setCellFactory(cellFactory);
            playerN.setSortable(false);
            tableView.getColumns().add(playerN);
        }
        // Put dices back in line (not in rolling area)
        this.resetDice();

        // Setup dices areas (kept and rolling)
        keptDice.setMinHeight(100);
        keptDice.setMinWidth(300);
        rollingDice.setMinHeight(100);
        rollingDice.setMinWidth(300);
    }

    /**
     * Update dices sprites from Dices game object
     */
    private void updateDices() {
        dice1.setImage(this.img[this.gfx.rou.dices[0].value() - 1]);
        dice2.setImage(this.img[this.gfx.rou.dices[1].value() - 1]);
        dice3.setImage(this.img[this.gfx.rou.dices[2].value() - 1]);
        dice4.setImage(this.img[this.gfx.rou.dices[3].value() - 1]);
        dice5.setImage(this.img[this.gfx.rou.dices[4].value() - 1]);
    }

    private void updateThrowLeft() {
        scoreLabel.setText("Throw left: " + this.gfx.rou.throwLeft);
    }

    /**
     * Handles click on a cell. Reset GUI for new round.
     */
    private void clickCell(int row) {
        if (gfx.scoreSelect(row)) {
            // Reset GUI for new round
            updateScoreRealOnly(gfx.scoreboardArr);
            updateThrowLeft();
            resetDice();
            updateDices();
            reRollButton.setDisable(false);
            if (gfx.isGameOver()) {
                this.gameOverDialog();
            }

        } else {
            System.out.println("Can't click here!");
        }
    }

    /**
     * Launches updates to the dices, scoreboard, texts, buttons and game logic.
     */
    @FXML
    protected void rollEvent(ActionEvent event) {
        // Remove a throw and update text
        this.gfx.reRoll();

        this.moveToRolling();
        this.updateThrowLeft();
        this.updateDices();
        //this.updateScore(this.gfx.getCurrentScoreboard().getMaxScore(), this.gfx.getCurrentScoreboard().getScore());
        this.updateScore(this.gfx.scoreboardArr, this.gfx.currentPlayer);
        // Disable button when you can't throw anymore!
        if (this.gfx.rou.throwLeft == 0) {
            this.reRollButton.setDisable(true);
        }
    }

    /**
     * Roll all dices that are not kept
     * Put them in the rolling area
     */
    private void moveToRolling() {
        moveDiceToRolling(dice1, 0);
        moveDiceToRolling(dice2, 1);
        moveDiceToRolling(dice3, 2);
        moveDiceToRolling(dice4, 3);
        moveDiceToRolling(dice5, 4);
    }

    private void moveDiceToRolling(ImageView dice1, int index) {
        if (this.gfx.rou.dices[index].keep() && keptDice.getChildren().contains(dice1)) {
            keptDice.getChildren().remove(dice1);
            rollingDice.getChildren().add(dice1);
        }
    }

    /**
     * Print the latest scoreboard, clearing the old one
     */
    private void updateScore(Score[] scoreboards, int currentPlayer) {
        tableView.getItems().clear();
        //tableView.getItems().
        // TODO Update for more than 2 players
        int[][] scores = new int[MAX_PLAYERS][];
        for (int player = 0; player < MAX_PLAYERS; player++) {
            scores[player] = scoreboards[player].getScore();
        }
        int[] possibleScore = scoreboards[0].getMaxScore();

        for (int i = 0; i < MAX_SCORE_NAME; i++) {
            String[] scoreText = new String[MAX_PLAYERS];
            for (int player = 0; player < MAX_PLAYERS; player++) {
                String textPossibleScore = possibleScore[i] == 0 ? "" : Integer.toString(possibleScore[i]);
                String textRealScore = scores[player][i] == -1 ? "" : Integer.toString(scores[player][i]);
                if (currentPlayer == player && "".equals(textRealScore) && !"".equals(textPossibleScore)) {
                    scoreText[player] = textPossibleScore + "R";
                } else {
                    scoreText[player] = textRealScore;
                }
            }

            tableView.getItems().add(new ScoreRow(Rules.lower(i + 1), scoreText));
        }
    }

    /**
     * Provides a scoreboard without clickable options
     */
    private void updateScoreRealOnly(Score[] scoreboards) {
        tableView.getItems().clear();
        // TODO Update for more than 2 players
        int[][] scores = new int[MAX_PLAYERS][];
        for (int player = 0; player < MAX_PLAYERS; player++) {
            scores[player] = scoreboards[player].getScore();
        }

        for (int i = 0; i < MAX_SCORE_NAME; i++) {
            String[] scoreText = new String[MAX_PLAYERS];
            for (int player = 0; player < MAX_PLAYERS; player++) {
                String textRealScore = scores[player][i] == -1 ? "" : Integer.toString(scores[player][i]);
                scoreText[player] = textRealScore;
            }
            tableView.getItems().add(new ScoreRow(Rules.lower(i + 1), scoreText));
        }
    }

    /**
     * Put an empty scoreboard, with only the "Score Name" column
     */
    private void updateScore() {
        tableView.getItems().clear();
        for (int i = 0; i < MAX_SCORE_NAME; i++) {
            tableView.getItems().add(new ScoreRow(Rules.lower(i + 1), "", ""
            ));
        }
    }

    public void diceClick(@NotNull MouseEvent mouseEvent) {
        System.out.println("Dice clicked");
        if (this.gfx.rou.throwLeft == 3 || this.gfx.rou.throwLeft == 0) {
            System.out.println("Can't toggle a dice now!");
            return;
        }

        int id = 0;
        ImageView diceImg;
        switch (mouseEvent.getPickResult().getIntersectedNode().getId()) {
            case "dice1":
                diceImg = dice1;
                id = 1;
                break;
            case "dice2":
                id = 2;
                diceImg = dice2;
                break;
            case "dice3":
                id = 3;
                diceImg = dice3;
                break;
            case "dice4":
                id = 4;
                diceImg = dice4;
                break;
            case "dice5":
                diceImg = dice5;
                id = 5;
                break;
            default:
                diceImg = dice1;
                break;
        }

        // Change area for dice if needed
        if (rollingDice.getChildren().contains(diceImg)) {
            rollingDice.getChildren().remove(diceImg);
            keptDice.getChildren().add(diceImg);
        } else {
            keptDice.getChildren().remove(diceImg);
            rollingDice.getChildren().add(diceImg);
        }
        this.gfx.rou.toggleKeeperSolo(id);
        System.out.println("Toggled");
    }

    /**
     * Put all dices back into line in kept area
     */
    public void resetDice() {
        if (rollingDice.getChildren().contains(dice1)) {
            rollingDice.getChildren().remove(dice1);
            keptDice.getChildren().add(dice1);
        }
        if (rollingDice.getChildren().contains(dice2)) {
            rollingDice.getChildren().remove(dice2);
            keptDice.getChildren().add(dice2);
        }
        if (rollingDice.getChildren().contains(dice3)) {
            rollingDice.getChildren().remove(dice3);
            keptDice.getChildren().add(dice3);
        }
        if (rollingDice.getChildren().contains(dice4)) {
            rollingDice.getChildren().remove(dice4);
            keptDice.getChildren().add(dice4);
        }
        if (rollingDice.getChildren().contains(dice5)) {
            rollingDice.getChildren().remove(dice5);
            keptDice.getChildren().add(dice5);
        }
    }

    private void gameOverDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText("Player X won !");
        alert.setContentText("Thank you for playing our Yahtzee.");

        ButtonType buttonTypeOne = new ButtonType("Play again");
        ButtonType buttonTypeCancel = new ButtonType("Exit", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonTypeOne) {
            // Launch new game
            this.gfx = new GameEngine(MAX_PLAYERS);
            this.updateThrowLeft();
            // Add a scoreboard with only labels, not actual score!
            this.updateScore();
            this.resetDice();

        } else {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
                Stage app_stage = (Stage) (title.getScene().getWindow());
                Scene newgame_scene = new Scene(root);
                app_stage.setScene(newgame_scene);
                app_stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}