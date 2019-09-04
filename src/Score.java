public class Score {
    private int[] tempScoreBoard = new int[16];
    private int[] scoreBoard = new int[16];


    public Score() {
        for (int i = 0; i < 16; i++) {
            tempScoreBoard[i] = 0;
        }
        for (int i = 0; i < 16; i++) {
            scoreBoard[i] = -1;
        }
    }

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

    public void maxScore(Dice[] dices) {
        // First reset score
        for (int diceValue = 0; diceValue < 16; diceValue++) {
            tempScoreBoard[diceValue] = 0;
        }

        upperScore(dices); // For score index 0 to 5

        tempScoreBoard[8] = threeOfKind(dices);
        tempScoreBoard[9] = fourOfKind(dices);
        // tempScoreBoard[10] = fullhouse(dices);
        // tempScoreBoard[11] = smallstraight(dices);
        // tempScoreBoard[12] = largestraight(dices);
        tempScoreBoard[13] = chance(dices);
        tempScoreBoard[14] = yahtzee(dices);


    }

    private void upperScore(Dice[] dices) {
        for (int diceValue = 0; diceValue < 6; diceValue++) {
            for (int value = 0; value < 5; value++) {
                if (dices[value].value() == diceValue + 1) {
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
        return scoreBoard[scoreToCheck - 1] == -1;
    }

    private void saveScore(int scorePos, int scoreValue) {
        scoreBoard[scorePos - 1] = scoreValue;
    }

    public void selectScore(Dice[] dices, int scorePos) {
        this.maxScore(dices);
        this.saveScore(scorePos, tempScoreBoard[scorePos - 1]);
    }


    private boolean fullhouse(Dice[] dices) {
        int[] counts = new int[6];
        for (int i = 0; i < dices.length; i++)
            //increase the relevant counter
            counts[dices[i].value() - 1]++;
        //now check we've got a 2 and a 3
        boolean check2 = false;
        boolean check3 = false;
        for (int i : counts) {
            check2 |= (i == 2); //found 2 of some number
            check3 |= (i == 3); //found 3 of some number
            if (i == 5) return true; //found a Yahtzee so stop and return true
        }
        return (check2 && check3);

    }

    private int threeOfKind(Dice[] dices) {
        int[] counts = new int[6];
        int score = 0;
        for (int i = 0; i < dices.length; i++) {
            counts[dices[i].value() - 1]++;
        }

        boolean check3 = false;
        for (int i : counts) {
            check3 |= (i == 3); //found 3 of some number
            if (i == 3) {
                for (int j = 0; j < 4; j++) {
                    score = score + dices[j].value();
                    return score;
                }
            }
        }
        return score;
    }

    private int fourOfKind(Dice[] dices) {
        int[] counts = new int[6];
        int score = 0;
        for (int i = 0; i < dices.length; i++) {
            counts[dices[i].value() - 1]++;
        }

        boolean check3 = false;
        for (int i : counts) {
            check3 |= (i == 4); //found 3 of some number
            if (i == 4) {
                for (int j = 0; j < 4; j++) {
                    score = score + dices[j].value();
                    return score;
                }
            }
        }
        return score;
    }

    public int smallLargeStraight(Dice[] dices) {

        int count = 0;
        for (int i = 0; i < 4; i++) {
            if (dices[i].value() == 3) {
                for (int j = 0; j < 4; j++) {
                    if (dices[j].value() == 4) {
                        for (int k = 0; k < 4; k++) {
                            if (dices[k].value() == 5) {
                                for (int m = 0; m < 4; m++) {
                                    if (dices[m].value() == 6) {
                                        for (int p = 0; p < 4; p++) {
                                            if (dices[k].value() == 2)
                                                return 40;
                                        }
                                        return 30;
                                    }
                                    if (dices[m].value() == 2) {
                                        for (int p = 0; p < 4; p++) {
                                            if (dices[p].value() == 6)
                                                return 40;
                                        }
                                        return 30;
                                    }

                                }


                                if (dices[k].value() == 2) {
                                    for (int m = 0; m < 4; m++) {
                                        if (dices[m].value() == 1) {
                                            for (int p = 0; p < 4; p++) {
                                                if (dices[p].value() == 5)
                                                    return 40;
                                            }
                                            return 30;
                                        }
                                        if (dices[m].value() == 5) {
                                            for (int p = 0; p < 4; p++) {
                                                if (dices[p].value() == 1)
                                                    return 40;
                                            }
                                            return 30;
                                        }

                                    }

                                }
                            }
                        }
                    }
                }
            }

        }
        return 0;
    }

    private int chance(Dice[] dices) {
        int score = 0;
        for (int i = 0; i < dices.length; i++) {
            score = score + dices[i].value();
        }
        return score;
    }

    private int yahtzee(Dice[] dices) {
        int[] counts = new int[6];
        for (int i = 0; i < dices.length; i++) {
            counts[dices[i].value() - 1]++;
        }

        boolean check5 = false;
        for (int i : counts) {
            check5 |= (i == 5); //found 3 of some number
            if (i == 5) {
                return 50;
            }

        }
        return 0;

    }

}













