package game;

import game.dice.Dice;
import game.dice.DiceCard;
import game.dice.Resource;
import org.junit.Assert;
import org.junit.Test;
import static game.DiceRoll.roll;

public class DiceRollTest {

    @Test
    public void diceTest(){
        Dice d1 = new Dice();
        d1.solarDiceInit();
        Dice d2 = new Dice();
        d2.lunarDiceInit();
        DiceCard[] roll = roll(d1,d2);

        Assert.assertTrue((roll[0].equals(new DiceCard(1, Resource.SOLAR)))||
                        (roll[0].equals(new DiceCard(1, Resource.GOLD))));
        Assert.assertTrue((roll[1].equals(new DiceCard(1, Resource.LUNAR)))||
                        (roll[1].equals(new DiceCard(1, Resource.GOLD)))||
                        (roll[1].equals(new DiceCard(2, Resource.VICTORY))));

    }
}