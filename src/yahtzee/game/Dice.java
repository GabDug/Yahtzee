package yahtzee.game;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class Dice implements Comparable<Dice> {
    private int sides, value;
    private Random ran;
    private boolean keeper;

    public Dice(int num) {
        sides = num; // 6 sides
        ran = new Random();
        value = roll();
        keeper = false;
    }

    public int roll() {
        value = ran.nextInt(sides) + 1; // nextInt is 0 inclusive and n exclusive so +1
        return value;
    }

    public int value() {
        return value;
    }

    public boolean keep() {
        return keeper;
    }

    public void toggleKeeper() {
        keeper = !keeper;
    }

    public void setKeeper(boolean keep) {
        this.keeper = keep;
    }

    public void setValue(int newValue) {
        this.value = newValue;
    }


    /** Allows sorting and comparing of dice, based on their face value*/
    @Override
    public int compareTo(@NotNull Dice o) {
        return Integer.compare(value, o.value);
    }

}