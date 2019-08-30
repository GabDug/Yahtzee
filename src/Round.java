import java.util.Scanner;

public class Round {
    int throwLeft;

    Score scoreboard;
    Dice[] dices = new Dice[5];

    public Round(Score scoreboard) {
        this.scoreboard=scoreboard;
        throwLeft = 2;

        for (int i = 0; i < 5; i++) {
            dices[i] = new Dice(6);
        }

        rollDices();


        while (throwLeft >= 0) {
            printDices();
            System.out.println("Possible Scores");
            scoreboard.maxScore(this.dices);
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

    void rollDices() {
        for (int i = 0; i < 5; i++) {
            // System.out.println(i);
            if (!this.dices[i].keep()) {
                this.dices[i].roll();
            }
        }
    }


    void printDices() {
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
        System.out.println("");
    }

    void askInput() {
        int userChoice;
        Scanner input = new Scanner(System.in);

        userChoice = menu();
        if (userChoice == 1) {
            int keep;

            while (true) {
                keep = keeperSelect();
                if (keep == 0) {
                    break;
                } else {
                    this.dices[keep - 1].toggleKeeper();
                }
            }
        }
        if (userChoice == 2) {
            if (this.throwLeft > 0) {
                this.throwLeft--;
                this.rollDices();
                this.printDices();
            } else {
                System.out.println("No throw left! Please choose a score.");
            }
        }
        if (userChoice == 3) {
            // Score Selection
            this.scoreSelectCheck();
        }
    }

    private void scoreSelectCheck() {
        int score;

        while (true) {
            score = scoreSelect();
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

    public static int menu() {

        int selection;
        Scanner input = new Scanner(System.in);
        System.out.println("-------------------------");
        System.out.println("1 - Keeper Selection");
        System.out.println("2 - Re-roll");
        System.out.println("3 - Score Selection");
        System.out.println("-------------------------");

        selection = input.nextInt();
        return selection;
    }

    public static int keeperSelect() {
        int selection;
        Scanner input = new Scanner(System.in);

        /***************************************************/

        System.out.println("Input the dice number to add it to keep.");
        System.out.println("Input 0 to exit this menu");


        selection = input.nextInt();
        return selection;
    }

    public static int scoreSelect() {
        int selection;
        Scanner input = new Scanner(System.in);

        System.out.println("Input the score number to add it to keep.");
        System.out.println("Input 0 to exit this menu");


        selection = input.nextInt();
        return selection;
    }
}
