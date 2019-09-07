package yahtzee;

import yahtzee.game.Score;

class GameEngine {
    private static final int MAX_ROUNDS = 150;
    public Score scoreboard = new Score();

    public GameEngine() {
        int round = 0;
        while (round < MAX_ROUNDS) {
            round++;
            System.out.println("New round!");
            Round rou = new Round(this.scoreboard);
        }
    }
}
