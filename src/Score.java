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
            System.out.printf("%-2.2s  %-9.9s  %-4.4s%n", i + 1, uper(i + 1), tempScoreBoard[i]);
        }
    }

    public void printScore() {
        for (int i = 0; i < 6; i++) {
            System.out.printf("%-2.2s  %-9.9s  %-4.4s%n", i + 1, uper(i + 1), scoreBoard[i]);
        }
    }

    public String uper(int i) {
        if (i == 1) {
            return "Ones: ";
        } else if (i == 2) {
            return "Twos: ";
        } else if (i == 3) {
            return "Threes: ";
        } else if (i == 4) {
            return "Fours: ";
        } else if (i == 5) {
            return "Fives: ";
        } else if (i == 6) {
            return "Sixes: ";
        }
        return "0";
    }
    
    public int lower(int j)
    { 
    	//3 and 4 of a Kind
    	Dice[] dices = null;
    	
    	
    	for(int i=0;i<6;i++)
    	{
    		if(dices[i].value() == dices[i+1].value() )
    		{
    	
    		}
    		
    	}
    	
    	return 0;
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


    public boolean fullhouse(Dice[] dices)
    {
        int[] counts = new int[6];
        for (int i=0; i<dices.length; i++)
            //increase the relevant counter
            counts[dices[i].value()-1]++;
//now check we've got a 2 and a 3
        boolean check2 = false;
        boolean check3 = false;
        for (int i: counts) {
            check2 |= (i==2); //found 2 of some number
            check3 |= (i==3); //found 3 of some number
            if (i==5) return true; //found a Yahtzee so stop and return true
        }
        return (check2 && check3);

    }

}










