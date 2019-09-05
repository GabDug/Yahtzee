package yahtzee.table;

import javafx.beans.property.SimpleStringProperty;

public class ScoreTable {
    private final SimpleStringProperty scoreName = new SimpleStringProperty("");
    private final SimpleStringProperty player1 = new SimpleStringProperty("");
    private final SimpleStringProperty player2 = new SimpleStringProperty("");

    public ScoreTable() {
        this("", "", "");
    }

    public ScoreTable(String scoreName, String player1, String player2) {
        setScoreName(scoreName);
        setPlayer1(player1);
        setPlayer2(player2);
    }

    public String getScoreName() {
        return scoreName.get();
    }

    public void setScoreName(String fName) {
        scoreName.set(fName);
    }

    public String getPlayer1() {
        return player1.get();
    }

    public void setPlayer1(String fName) {
        player1.set(fName);
    }

    public String getPlayer2() {
        return player2.get();
    }

    public void setPlayer2(String fName) {
        player2.set(fName);
    }
}