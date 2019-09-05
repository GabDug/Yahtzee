package yahtzee;

import yahtzee.game.Score;

public class GameEngineFX {
    public Score[] scoreboardArr = new Score[2];
    public int currentPlayer = 0;

    public RoundFX rou;

    public GameEngineFX() {
        scoreboardArr[0] = new Score();
        scoreboardArr[1] = new Score();
        rou = new RoundFX(this.scoreboardArr, currentPlayer);
        rou.setPlayer(0);
    }

    public void reset() {
        if (this.currentPlayer == 0) {
            System.out.println("Change player!");
            this.currentPlayer = 1;
        }
        else if (this.currentPlayer == 1) {
            this.currentPlayer = 0;
            System.out.println("Change player2!");
        }
        this.rou.setPlayer(this.currentPlayer);
        this.rou.throwLeft = 3;
        for (int dice = 0; dice < 5; dice++) {
            this.rou.dices[dice].setKeeper(false);
        }
    }

    public void reRoll() {
        if (this.rou.throwLeft > 0) {
            this.rou.throwLeft--;
            this.rou.rollDices();
            this.scoreboardArr[0].updateMaxScore(this.rou.dices);
            this.scoreboardArr[1].updateMaxScore(this.rou.dices);
        }
    }

    /**
     * Select
     *
     * @param row of selected score
     * @return boolean
     */
    public boolean scoreSelect(int row) {
        // Can't select score before throwing dices
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

    public Score getCurrentScoreboard(){
        return this.scoreboardArr[this.currentPlayer];
    }
}
