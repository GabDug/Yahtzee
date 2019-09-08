package yahtzee;

import yahtzee.game.Score;

class GameEngineFX {
    private static int MAX_PLAYERS;
    public final RoundFX rou;
    public Score[] scoreboardArr;
    public int currentPlayer = 0;

    public GameEngineFX(int playerNumber) {
        MAX_PLAYERS = playerNumber;
        scoreboardArr = new Score[MAX_PLAYERS];

        // Set Scoreboard for each player
        for (int player = 0; player < MAX_PLAYERS; player++) {
            scoreboardArr[player] = new Score();
        }
        rou = new RoundFX(this.scoreboardArr);
        rou.setPlayer(0);
    }

    private void reset() {
        this.currentPlayer++;
        if (this.currentPlayer == MAX_PLAYERS) {
            this.currentPlayer = 0;
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
            for (Score scoreboard : this.scoreboardArr) {
                scoreboard.updateMaxScore(this.rou.dices);
            }
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

    public Score getCurrentScoreboard() {
        return this.scoreboardArr[this.currentPlayer];
    }
}
