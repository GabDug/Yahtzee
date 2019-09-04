package yahtzee;

public class GameEngineFX {
    public Score scoreboard = new Score();
    public RoundFX rou;

    public GameEngineFX() {
        System.out.println("New round!");
        rou = new RoundFX(this.scoreboard);
    }

    public void reset() {
        this.rou.throwLeft = 3;
        for (int dice = 0; dice < 5; dice++) {
            this.rou.dices[dice].setKeeper(false);
        }
    }
}
