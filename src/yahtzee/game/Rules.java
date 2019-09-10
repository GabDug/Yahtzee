package yahtzee.game;

import java.util.Arrays;

public class Rules {
    /**
     * Check if there's a fullhouse possible
     * @param dices
     * @return possible score for the full house
     */
    static public int fullHouse(Dice[] dices) {
        int[] counts = new int[6];
        //increase the relevant counter
        for (Dice dice : dices) {
            counts[dice.value() - 1]++;
        }
        //now check we've got a 2 and a 3
        boolean check2 = false;
        boolean check3 = false;
        for (int i : counts) {
            check2 |= (i == 2); //found 2 of some number
            check3 |= (i == 3); //found 3 of some number
            if (i == 5) return 25; //found a Yahtzee so stop and return true
        }
        return (check2 && check3) ? 25 : 0;
    }

    /**
     * Sum all the dices
     * @param dices
     * @return sum
     */
    static int sumDices(Dice[] dices) {
        int score = 0;
        for (Dice dice : dices) {
            score = score + dice.value();
        }
        return score;
    }

    /**
     * Check if there's a Yahtzee
     * @param dices
     * @return possible score for the yahtzee
     */
    public static int yahtzee(Dice[] dices) {
        if (dices[0].value() == dices[1].value()
                && dices[1].value() == dices[2].value()
                && dices[2].value() == dices[3].value()
                && dices[3].value() == dices[4].value()) {
            return 50;
        } else
            return 0;
    }

    public static int smallStraight(Dice[] dices) {
        Dice[] sortDices;
        sortDices = dices.clone();
        Arrays.sort(sortDices);

        int counter = 0;

        for (int i = 0; i < sortDices.length - 1; i++) {
            if (sortDices[i + 1].value() == sortDices[i].value() + 1) {
                counter++;
            } else if (sortDices[i + 1].value() == sortDices[i].value()) {
                continue;
            } else {
                counter = 0;
            }
            if (counter == 3) {
                return 30;
            }
        }
        return 0;
    }

    public static int largeStraight(Dice[] dices) {
        Dice[] sortDices;
        sortDices = dices.clone();
        Arrays.sort(sortDices);

        int counter = 0;

        for (int i = 0; i < sortDices.length - 1; i++) {
            if (sortDices[i + 1].value() == sortDices[i].value() + 1) {
                counter++;
            } else if (sortDices[i + 1].value() == sortDices[i].value()) {
                continue;
            } else {
                counter = 0;
            }
            if (counter == 4) {
                return 40;
            }
        }
        return 0;
    }

    public static int threeOfKind(Dice[] dices) {
        Dice[] sortedDices;
        sortedDices = dices.clone();
        Arrays.sort(sortedDices);

        if ((sortedDices[0].value() == sortedDices[1].value() && sortedDices[1].value() == sortedDices[2].value()) ||
                (sortedDices[1].value() == sortedDices[2].value() && sortedDices[2].value() == sortedDices[3].value()) ||
                (sortedDices[2].value() == sortedDices[3].value() && sortedDices[3].value() == sortedDices[4].value())) {
            return sumDices(dices);
        } else {
            return 0;
        }
    }

    public static int fourOfKind(Dice[] dices) {
        Dice[] sortedDices;
        sortedDices = dices.clone();
        Arrays.sort(sortedDices);

        if ((sortedDices[0].value() == sortedDices[3].value()) || (sortedDices[1].value() == sortedDices[4].value()))
            return sumDices(dices);
        return 0;
    }

    /**
     * Gives text for each indice.
     * @param i row in the table, starting from 1
     * @return String to be printed, for each score row
     */
    public static String lower(int i) {
        switch (i) {
            case 1:
                return "Ones";
            case 2:
                return "Twos";
            case 3:
                return "Threes";
            case 4:
                return "Fours";
            case 5:
                return "Fives";
            case 6:
                return "Sixes";
            case 7:
                return "Bonus";
            case 8:
                return "Sum";
            case 9:
                return "Three of a kind";
            case 10:
                return "Four of a kind";
            case 11:
                return "Full House";
            case 12:
                return "Small straight";
            case 13:
                return "Large straight";
            case 14:
                return "Chance";
            case 15:
                return "Yahtzee";
            case 16:
                return "Total Score";
            default:
                return "0";
        }
    }
}
