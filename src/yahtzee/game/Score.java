package yahtzee.game;


import static yahtzee.game.Rules.*;

public class Score {
    private final int[] tempScoreBoard = new int[16];
    private final int[] scoreBoard = new int[16];

    public Score() {
        for (int i = 0; i < 16; i++) {
            this.tempScoreBoard[i] = 0;
        }
        for (int i = 0; i < 16; i++) {
            this.scoreBoard[i] = -1;
        }
        /* DEBUG, fill score
        for (int i = 0; i < 5; i++) {
            this.scoreBoard[i] = 5;
        }
        for (int i = 9; i < 14; i++) {
            this.scoreBoard[i] = 10;
        }*/
    }


    public void updateMaxScore(Dice[] dices) {
        // First reset score
        for (int diceValue = 0; diceValue < 16; diceValue++) {
            tempScoreBoard[diceValue] = 0;
        }
        upperScore(dices); // For score index 0 to 5

        tempScoreBoard[8] = threeOfKind(dices);
        tempScoreBoard[9] = fourOfKind(dices);
        tempScoreBoard[10] = fullHouse(dices);
        tempScoreBoard[11] = smallStraight(dices);
        tempScoreBoard[12] = largeStraight(dices);
        tempScoreBoard[13] = sumDices(dices);
        tempScoreBoard[14] = yahtzee(dices);

    }

    private void upperScore(Dice[] dices) {
        for (int diceValue = 0; diceValue < 6; diceValue++) {
            for (Dice dice : dices) {
                if (dice.value() == diceValue + 1) {
                    tempScoreBoard[diceValue] += diceValue + 1;
                }
            }
        }
    }

    public void printMaxScore() {
        for (int i = 0; i < 16; i++) {
            System.out.printf("%-2.2s  %-16.16s  %-4.4s%n", i + 1, lower(i + 1), tempScoreBoard[i]);
        }
    }

    public void printScore() {
        for (int i = 0; i < 16; i++) {
            System.out.printf("%-2.2s  %-16.16s  %-4.4s%n", i + 1, lower(i + 1), scoreBoard[i]);
        }
    }

    public int[] getScore() {
        return this.scoreBoard;
    }

    public int[] getMaxScore() {
        return this.tempScoreBoard;
    }

    public boolean isAvailable(int scoreToCheck) {
        if (scoreToCheck == 7 || scoreToCheck == 8 || scoreToCheck == 16) {
            return false;
            // SUM / BONUS AND TOTAL SCORE ARE NOT AVAILABLE
        }
        return this.scoreBoard[scoreToCheck - 1] == -1;
    }

    private void saveScore(int scorePos, int scoreValue) {
        this.scoreBoard[scorePos - 1] = scoreValue;
    }

    public void selectScore(Dice[] dices, int scorePos) {
        this.updateMaxScore(dices);
        this.saveScore(scorePos, tempScoreBoard[scorePos - 1]);
        this.updateTotalScore();
    }

    /**
     * Update Total Upper, Bonus and Total Score
     */
    private void updateTotalScore() {
        scoreBoard[7] = totalUpper();
        scoreBoard[6] = bonus();
        scoreBoard[15] = totalScore();
    }


    private int totalUpper() {
        int counter = 0;

        for (int i = 0; i < 6; i++) {
            if (scoreBoard[i] == -1) {
                return -1;
            }
            counter += scoreBoard[i];
        }
        return counter;
    }

    private int totalScore() {
        int counter = 0;

        for (int i = 6; i < 15; i++) {
            if (scoreBoard[i] == -1) {
                return -1;
            }
            counter += scoreBoard[i];
        }
        return counter;
    }

    private int bonus() {
        if (scoreBoard[7] >= 63) {
            return 35;
        } else if (scoreBoard[7] == -1) {
            return -1;
        } else {
            return 0;
        }
    }

    public boolean isFull() {
        for (int i = 0; i < 16; i++) {
            if (scoreBoard[i] == -1) {
                return false;
            }
        }
        return true;
    }
}













