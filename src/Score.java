public class Score {
    int tempScoreBoard[] = new int[6];
    int scoreBoard[] = new int[6];

    public Score() {
        for (int i = 0; i < 6; i++) {
            tempScoreBoard[i] = 0;
        }
    }

    public void maxScore(Dice[] dices) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                if (dices[j].value() == i + 1) {
                    tempScoreBoard[i] += i + 1;
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
