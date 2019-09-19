package test;

import org.junit.Assert;
import org.junit.Test;
import yahtzee.game.Dice;
import yahtzee.game.ScoreUtils;

public class TestScore {

    @Test
    public void smallStraight() {
        // Arrange
        final Dice[] dices = new Dice[5];
        for (int i = 0; i < 5; i++) {
            dices[i] = new Dice(6);
        }
        dices[0].setValue(2);
        dices[1].setValue(3);
        dices[2].setValue(4);
        dices[3].setValue(1);

        final int expected = 30;

        // Act
        final int actual = ScoreUtils.smallStraight(dices);

        // Assert
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void threeOfAKind() {
        // Arrange
        final Dice[] dices = new Dice[5];
        for (int i = 0; i < 5; i++) {
            dices[i] = new Dice(6);
        }
        dices[0].setValue(2);
        dices[1].setValue(2);
        dices[2].setValue(2);
        dices[3].setValue(4);
        dices[4].setValue(5);

        final int expected = 15;

        // Act
        final int actual = ScoreUtils.threeOfKind(dices);

        // Assert
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void smallStraightBis() {
        // Arrange
        final Dice[] dices = new Dice[5];
        for (int i = 0; i < 5; i++) {
            dices[i] = new Dice(6);
        }
        dices[0].setValue(2);
        dices[1].setValue(1);
        dices[2].setValue(3);
        dices[3].setValue(6);
        dices[3].setValue(6);

        final int expected = 0;

        // Act
        final int actual = ScoreUtils.smallStraight(dices);

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

        final int expected = 14;

        // Act
        final int actual = ScoreUtils.fourOfKind(dices);

        // Assert
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void fourOfAKindBis() {
        // Arrange
        final Dice[] dices = new Dice[5];
        for (int i = 0; i < 5; i++) {
            dices[i] = new Dice(6);
        }
        dices[0].setValue(5);
        dices[1].setValue(5);
        dices[2].setValue(5);
        dices[3].setValue(4);
        dices[4].setValue(5);

        final int expected = 24;

        // Act
        final int actual = ScoreUtils.fourOfKind(dices);

        // Assert
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void fourOfAKindTris() {
        // Arrange
        final Dice[] dices = new Dice[5];
        for (int i = 0; i < 5; i++) {
            dices[i] = new Dice(6);
        }
        dices[0].setValue(6);
        dices[1].setValue(6);
        dices[2].setValue(6);
        dices[3].setValue(6);
        dices[4].setValue(1);

        final int expected = 25;

        // Act
        final int actual = ScoreUtils.fourOfKind(dices);

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

        final int expected = 40;

        // Act
        final int actual = ScoreUtils.largeStraight(dices);

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

        final int expected = 50;

        // Act
        final int actual = ScoreUtils.yahtzee(dices);

        // Assert
        Assert.assertEquals(expected, actual);
    }
}
