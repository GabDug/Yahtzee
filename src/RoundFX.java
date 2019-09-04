import java.util.Scanner;

public class RoundFX {
    public int throwLeft;
    public Dice[] dices = new Dice[5];
    private Score scoreboard;

    public RoundFX(Score scoreboard) {
        this.scoreboard = scoreboard;
        throwLeft = 2;

        for (int i = 0; i < 5; i++) {
            dices[i] = new Dice(6);
        }

        // rollDices();

        printDices();
        System.out.println("Possible Scores");
        scoreboard.maxScore(this.dices);
        scoreboard.printMaxScore();
    }


    public void rollDices() {
        for (int i = 0; i < 5; i++) {
            // System.out.println(i);
            if (!this.dices[i].keep()) {
                this.dices[i].roll();
            }
        }
    }

    public void printDices() {
        System.out.println("Dices");
        int diceNbr = 0;
        for (int i = 0; i < 5; i++) {
            if (!this.dices[i].keep()) {
                diceNbr++;
                System.out.printf("%-4.4s ", this.dices[i].value());
            }
        }
        if (diceNbr != 5) {
            System.out.println("\nKeeper Dices");
            for (int i = 0; i < 5; i++) {
                if (this.dices[i].keep())
                    System.out.printf("%-4.4s ", this.dices[i].value());
            }
        }
        System.out.println();
    }

    public void toggleKeeperSolo(int keep) {
        this.dices[keep - 1].toggleKeeper();
    }

    private void reRoll() {
        if (this.throwLeft > 0) {
            this.throwLeft--;
            this.rollDices();
            this.printDices();
        } else {
            System.out.println("No throw left! Please choose a score.");
        }
    }

    public void scoreSelectCheck(int score) {
        if (this.scoreboard.isAvailable(score + 1)) {
            System.out.println("Score is available");
            this.scoreboard.selectScore(this.dices, score + 1);
            this.scoreboard.printScore();

        } else {
            System.out.print("Not available!");
        }

    }
}
