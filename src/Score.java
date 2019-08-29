public class Score {
    int tempScoreBoard[] = new int[6];
    int scoreBoard[] = new int[6];

    public Score() {
        for (int i = 0; i < 6; i++) {
            tempScoreBoard[i] = 0;
        }
    }

    public void maxScore(Dice[] dices) {
        for (int diceValue = 0; diceValue < 6; diceValue++) {
            for (int value = 0; value < 5; value++) {
                if (dices[value].value() == diceValue + 1) {
                    tempScoreBoard[diceValue] += diceValue + 1;
                }
            }
        }
    }

    public void printScore() {
        for (int i = 0; i < 6; i++) {
            System.out.println(tempScoreBoard[i]);
        }
    }
}
