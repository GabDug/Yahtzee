package yahtzee.table;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScoreRow {

    private Object scoreName;
    private Object player1;
    private Object player2;
    private Object player3;
    private Object player4;
    private Object player5;

    /**
     * This class is used for the TableView object.
     * It is instancieted in a way where we can choose the number of player.
     *
     * @param args
     */
    public ScoreRow(Object... args) {
        Field[] fields = getClass().getDeclaredFields();
        int i = 0;
        for (Object arg : args) {
            try {
                fields[i++].set(this, arg);
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(ScoreRow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ScoreRow(Object scoreName, Object[] players) {
        Field[] fields = getClass().getDeclaredFields();
        int i = 0;
        try {
            fields[i++].set(this, scoreName);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(ScoreRow.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Object arg : players) {
            try {
                fields[i++].set(this, arg);
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(ScoreRow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * @return the one
     */
    public Object getScoreName() {
        return scoreName;
    }

    /**
     * @return the two
     */
    public Object getPlayer1() {
        return player1;
    }

    /**
     * @return the three
     */
    public Object getPlayer2() {
        return player2;
    }

    /**
     * @return the four
     */
    public Object getPlayer3() {
        return player3;
    }

    /**
     * @return the five
     */
    public Object getPlayer4() {
        return player4;
    }

    /**
     * @return the six
     */
    public Object getPlayer5() {
        return player5;
    }
}