package test;

import org.junit.Assert;
import org.junit.Test;
import yahtzee.Dice;
import yahtzee.Score;

public class TestScore {

    @Test
    public void smallStraight_1() {
        // Arrange
        final Dice[] dices = new Dice[5];
        for (int i = 0; i < 5; i++) {
            dices[i] = new Dice(6);
        }
        dices[0].setValue(2);
        dices[1].setValue(3);
        dices[2].setValue(4);
        dices[3].setValue(1);
        final Score scoreboard = new Score();

        final int expected = 30;

        // Act
        final int actual = scoreboard.smallStraight(dices);

        // Assert
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void fourOfAKind() {
        // Arrange
        final Dice[] dices = new Dice[5];
        for (int i = 0; i < 5; i++) {
            dices[i] = new Dice(6);
        }
        dices[0].setValue(2);
        dices[1].setValue(2);
        dices[2].setValue(6);
        dices[3].setValue(2);
        dices[4].setValue(2);
        final Score scoreboard = new Score();

        final int expected = 14;

        // Act
        final int actual = scoreboard.fourOfKind(dices);

        // Assert
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void largeStraight() {
        // Arrange
        final Dice[] dices = new Dice[5];
        for (int i = 0; i < 5; i++) {
            dices[i] = new Dice(6);
        }
        dices[0].setValue(2);
        dices[1].setValue(5);
        dices[2].setValue(4);
        dices[3].setValue(3);
        dices[4].setValue(6);
        final Score scoreboard = new Score();

        final int expected = 40;

        // Act
        final int actual = scoreboard.largeStraight(dices);

        // Assert
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void yahtzee() {
        // Arrange
        final Dice[] dices = new Dice[5];
        for (int i = 0; i < 5; i++) {
            dices[i] = new Dice(6);
        }

        dices[0].setValue(1);
        dices[1].setValue(1);
        dices[2].setValue(1);
        dices[3].setValue(1);
        dices[4].setValue(1);
        final Score scoreboard = new Score();

        final int expected = 50;

        // Act
        final int actual = scoreboard.yahtzee(dices);

        // Assert
        Assert.assertEquals(expected, actual);
    }
}
