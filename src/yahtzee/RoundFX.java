package yahtzee;

import yahtzee.game.Dice;
import yahtzee.game.Score;

public class RoundFX {
    public int throwLeft;
    public Dice[] dices = new Dice[5];
    private Score scoreboard;

    public RoundFX(Score scoreboard) {
        this.scoreboard = scoreboard;
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
        if (this.scoreboard.isAvailable(score + 1)) {
            this.scoreboard.selectScore(this.dices, score + 1);
            // this.scoreboard.printScore();
            return true;
        } else {
            return false;
        }

    }
}
