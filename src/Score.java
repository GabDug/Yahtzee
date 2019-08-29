public class Score {
    private int[] tempScoreBoard = new int[6];
    private int[] scoreBoard = new int[6];


    public Score() {
        for (int i = 0; i < 6; i++) {
            tempScoreBoard[i] = 0;
        }
        for (int i = 0; i < 6; i++) {
            scoreBoard[i] = -1;
        }
    }

    public void maxScore(Dice[] dices) {
        // First reset score
        for (int diceValue = 0; diceValue < 6; diceValue++) {

            tempScoreBoard[diceValue] = 0;

        }


        for (int diceValue = 0; diceValue < 6; diceValue++) {
            for (int value = 0; value < 5; value++) {
                if (dices[value].value() == diceValue + 1) {
                    tempScoreBoard[diceValue] += diceValue + 1;
                }
            }
        }
    }

    public void printMaxScore() {
        for (int i = 0; i < 6; i++) {
            System.out.printf("%-4.4s  %-4.4s%n", i + 1, tempScoreBoard[i]);
        }
    }

    public void printScore() {
        for (int i = 0; i < 6; i++) {
            System.out.printf("%-4.4s  %-4.4s%n", i + 1, scoreBoard[i]);
        }
    }

    public boolean isAvailable(int scoreToCheck) {
        if (scoreBoard[scoreToCheck - 1] == -1) {
            return true;
        } else {
            return false;
        }
    }

    private void saveScore(int scorePos, int scoreValue) {
        scoreBoard[scorePos - 1] = scoreValue;
    }

    public void selectScore(Dice[] dices, int scorePos) {
        this.maxScore(dices);
        this.saveScore(scorePos, tempScoreBoard[scorePos-1]);
    }
}
