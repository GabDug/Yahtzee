package yahtzee;

import yahtzee.game.Score;

class GameEngine {
    private static int MAX_PLAYERS;
    public final Round rou;
    public Score[] scoreboardArr;
    public int currentPlayer = 0;

    GameEngine(int playerNumber) {
        MAX_PLAYERS = playerNumber;
        scoreboardArr = new Score[MAX_PLAYERS];

        // Set Scoreboard for each player
        for (int player = 0; player < MAX_PLAYERS; player++) {
            scoreboardArr[player] = new Score();
        }
        rou = new Round(this.scoreboardArr);
        rou.setPlayer(0);
    }

    /**
     * Reset a round: the dices, number of throw lefts,
     */
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

    /**
     * Roll the dices, update the scoreboard
     */
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
     * Save the score selected by the user, if it's allowed
     *
     * @param row of selected score
     * @return boolean Sucess
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

    /**
     * Check if the game is over, when the scoreboard is full.
     * @return boolean Is the game over ?
     */
    public boolean isGameOver() {
        for (Score scoreboard : this.scoreboardArr) {
            if (!scoreboard.isFull()) {
                return false;
            }
        }
        return true;
    }
}
