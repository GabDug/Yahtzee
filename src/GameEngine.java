public class GameEngine {

    Score scoreboard = new Score();
    Dice[] dices = new Dice[5];

    public GameEngine() {
        for (int i = 0; i < 5; i++) {
            dices[i] = new Dice(6);
        }
        rollDices();
        printDices();
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
            System.out.println(this.dices[i].value());
        }
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
