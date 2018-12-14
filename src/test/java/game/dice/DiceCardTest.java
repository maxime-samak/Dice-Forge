package game.dice;

import org.junit.Assert;
import org.junit.Test;

import static game.dice.Resource.GOLD;
import static game.dice.Resource.SOLAR;
import static org.junit.Assert.*;

public class DiceCardTest {

    @Test
    public void equals() {
        DiceCard f1 = new DiceCard(1, SOLAR);
        DiceCard f2 = new DiceCard(1, GOLD);
        DiceCard f3 = new DiceCard(1, GOLD);
        DiceCard f4 = new DiceCard(2, GOLD);
        DiceCard f5 = new DiceCard(0, GOLD);
        DiceCard f6 = new DiceCard(-1, GOLD);

        Assert.assertFalse(f1.equals(f2));
        Assert.assertFalse(f1.equals(f2));
        Assert.assertFalse(f1.equals(f4));
        Assert.assertFalse(f1.equals(f5));
        Assert.assertFalse(f1.equals(f6));

        Assert.assertTrue(f2.equals(f3));
    }
}