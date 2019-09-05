package yahtzee;

import com.sun.istack.internal.NotNull;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import table.ScoreTable;
import yahtzee.game.Score;

public class MainFXController {
    public static int MAX_SCORE_NAME = 16;

    public TableView tableView;
    public TableColumn player1;
    public TableColumn scoreName;
    public TableColumn player2;
    public Button reRollButton;
    public GridPane rollingDice;
    public GridPane keptDice;

    @FXML
    private Text scoreLabel;

    @FXML
    private TableColumn<ScoreTable, String> scoreColumn;

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

    private GameEngineFX gfx;

    private Image[] img = new Image[6];

    public void initialize() {
        System.out.println("YAHTZEE STARTED!");
        this.gfx = new GameEngineFX();

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
        Inspired from https://stackoverflow.com/questions/12499269/javafx-tableview-detect-a-doubleclick-on-a-cell
        */

        Callback<TableColumn, TableCell> cellFactory =
                new Callback<TableColumn, TableCell>() {
                    public TableCell call(TableColumn p) {
                        TableCell cell = new TableCell<ScoreTable, String>() {
                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                // "R" is appended to text supposed to be red, and is thus deleted before printing
                                if (item != null && item.contains("R")) {
                                    setStyle("-fx-text-fill: red;");
                                } else if (item != null) {
                                    setStyle("-fx-text-fill: black;");
                                }
                                setText(empty ? null : getString().replace("R", ""));
                                setGraphic(null);
                            }

                            private String getString() {
                                return getItem() == null ? "" : getItem();
                            }
                        };

                        cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                if (event.getClickCount() > 1) {
                                    TableCell c = (TableCell) event.getSource();
                                    System.out.println("DEBUG Row Clicked" + c.getTableRow().getIndex());
                                    if (c.getTableRow().getIndex() < MAX_SCORE_NAME) {
                                        clickCell(c.getTableRow().getIndex());
                                    } else {
                                        System.out.println("DEBUG Cell is not clickable!");
                                    }
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
        player2.setSortable(false);
        scoreName.setSortable(false);

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
            updateScore(gfx.scoreboard.getMaxScore(), gfx.scoreboard.getScore());
            updateThrowLeft();
            resetDice();
            updateDices();
            reRollButton.setDisable(false);
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
        this.updateScore(this.gfx.scoreboard.getMaxScore(), this.gfx.scoreboard.getScore());

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
        if (!this.gfx.rou.dices[index].keep() && keptDice.getChildren().contains(dice1)) {
            keptDice.getChildren().remove(dice1);
            rollingDice.getChildren().add(dice1);
        }
    }

    /**
     * Print the latest scoreboard, clearing the old one
     */
    private void updateScore(int[] score, int[] possibleScore) {
        tableView.getItems().clear();
        for (int i = 0; i < 16; i++) {
            String textRealScore = possibleScore[i] == -1 ? "" : Integer.toString(possibleScore[i]);
            String testScore = score[i] == 0 ? "" : Integer.toString(score[i]);
            String toAdd;
            if (!"".equals(testScore) && "".equals(textRealScore)) {
                toAdd = testScore + "R";
            } else {
                toAdd = textRealScore;
            }

            tableView.getItems().add(new ScoreTable(Score.lower(i + 1), toAdd, textRealScore
            ));
        }
    }

    /**
     * Put an empty scoreboard
     */
    private void updateScore() {
        tableView.getItems().clear();
        for (int i = 0; i < 16; i++) {
            tableView.getItems().add(new ScoreTable(Score.lower(i + 1), "", ""
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
}