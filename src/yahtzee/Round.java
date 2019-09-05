package yahtzee;

import yahtzee.game.Dice;
import yahtzee.game.Score;

import java.util.Scanner;

public class Round {
    private int throwLeft;
    private Score scoreboard;
    private Dice[] dices = new Dice[5];

    public Round(Score scoreboard) {
        this.scoreboard = scoreboard;
        throwLeft = 2;

        for (int i = 0; i < 5; i++) {
            dices[i] = new Dice(6);
        }

        rollDices();

        while (throwLeft >= 0) {
            printDices();
            System.out.println("Possible Scores");
            scoreboard.updateMaxScore(this.dices);
            scoreboard.printMaxScore();
            if (throwLeft == 0) {
                this.scoreSelectCheck();
                break;
            } else {
                askInput();
            }
            System.out.println("Throw left:" + throwLeft);
        }
    }


    private void rollDices() {
        for (int i = 0; i < 5; i++) {
            // System.out.println(i);
            if (!this.dices[i].keep()) {
                this.dices[i].roll();
            }
        }
    }

    private void printDices() {
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

    private void askInput() {
        int userChoice;
        Scanner input = new Scanner(System.in);
        userChoice = Console.menu();
        if (userChoice == 1) {
            this.toggleKeeper();
        }
        if (userChoice == 2) {
            this.reRoll();
        }
        if (userChoice == 3) {
            this.scoreSelectCheck();
        }
    }

    private void toggleKeeper() {
        int keep;

        while (true) {
            keep = Console.keeperSelect();
            if (keep == 0) {
                break;
            } else {
                this.dices[keep - 1].toggleKeeper();
            }
        }
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

    private void scoreSelectCheck() {
        int score;

        while (true) {
            score = Console.scoreSelect();
            if (score == 0)
                break;
            if (this.scoreboard.isAvailable(score)) {
                System.out.println("Score is available");
                this.scoreboard.selectScore(this.dices, score);
                this.scoreboard.printScore();
                break;
            } else {
                System.out.print("Not available!");
            }
        }
    }
}
