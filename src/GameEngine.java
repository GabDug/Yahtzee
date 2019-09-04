public class GameEngine {
    public Score scoreboard = new Score();

    public GameEngine() {
        while (true) {
            System.out.println("New round!");
            Round rou = new Round(this.scoreboard);
        }
    }
}
