package yahtzee;

import yahtzee.game.Dice;
import yahtzee.game.Score;

public class RoundFX {
    public int throwLeft;
    public Dice[] dices = new Dice[5];
    private Score scoreboards[];
    private int currentPlayer;

    public RoundFX(Score[] scoreboards, int currentPlayer) {
        this.scoreboards = scoreboards;
        throwLeft = 3;

        for (int i = 0; i < 5; i++) {
            dices[i] = new Dice(6);
        }
    }

    public void rollDices() {
        for (int i = 0; i < 5; i++) {
            // System.out.println(i);
            if (!this.dices[i].keep()) {
                this.dices[i].roll();
            }
        }
    }

    public void toggleKeeperSolo(int keep) {
        this.dices[keep - 1].toggleKeeper();
    }

    public boolean scoreSelectCheck(int score) {
        if (this.scoreboards[this.currentPlayer].isAvailable(score + 1)) {
            this.scoreboards[this.currentPlayer].selectScore(this.dices, score + 1);
            this.scoreboards[this.currentPlayer].printScore();
            return true;
        } else {
            return false;
        }
    }

    public void setPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
       // this.scoreboards[0] = this.scoreboards[currentPlayer];
        System.out.println("Player " + currentPlayer);
    }
}
