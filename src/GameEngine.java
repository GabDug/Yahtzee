import java.util.Scanner;

public class GameEngine {

    Score scoreboard = new Score();
    Dice[] dices = new Dice[5];
    int throwLeft = 2;

    public GameEngine() {
        for (int i = 0; i < 5; i++) {
            dices[i] = new Dice(6);
        }

        Score score = new Score();
        rollDices();
        System.out.println("Dices");
        printDices();

        System.out.println("Possible Scores");
        score.maxScore(this.dices);
        score.printScore();

        while (throwLeft > 0) {
            askInput();

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
        for (int i = 0; i < 5; i++) {
            System.out.printf("%-4.4s ", this.dices[i].value());
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
    }

    public static int menu() {

        int selection;
        Scanner input = new Scanner(System.in);

        System.out.println("Choose from these choices");
        System.out.println("-------------------------\n");
        System.out.println("1 - Keeper Selection");
        System.out.println("2 - Re-roll");
        System.out.println("3 - Score Selection");

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

        /***************************************************/

        System.out.println("Input the score number to add it to keep.");
        System.out.println("Input 0 to exit this menu");


        selection = input.nextInt();
        return selection;
    }

    /* LOOP */
    /* ROLL DICE */


    /* OUTPUT: DICE VALUE */
    /* OUTPUT: POSSIBLE SCORE */

    /* INPUT: ACTION
    KEEPER SELECTION /
    ROLL AGAIN /
    SCORE */

    /* END LOOP */

    /* PRINT SCORE */
}
