package yahtzee;

import yahtzee.game.Score;

public class GameEngineFX {
    public Score scoreboard = new Score();
    public RoundFX rou;

    public GameEngineFX() {
        rou = new RoundFX(this.scoreboard);
    }

    public void reset() {
        this.rou.throwLeft = 3;
        for (int dice = 0; dice < 5; dice++) {
            this.rou.dices[dice].setKeeper(false);
        }

    }

    void reRoll() {
        if (this.rou.throwLeft > 0) {
            this.rou.throwLeft--;
            this.rou.rollDices();
            this.scoreboard.updateMaxScore(this.rou.dices);
        }
    }

    /**
     * Select
     *
     * @param row of selected score
     * @return boolean
     */
    public boolean scoreSelect(int row) {
        if (this.rou.throwLeft != 3) {
            boolean result = this.rou.scoreSelectCheck(row);
            if (!result) {
                return false;
            }
            // Reset for new round
            this.reset();
            return true;
        }
        return false;
    }
}
