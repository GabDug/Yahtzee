public class GameEngine {
    Score socreboard = new Score();

    public GameEngine() {
        while (true) {
            System.out.println("New round!");
            Round rou = new Round(this.socreboard);
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
