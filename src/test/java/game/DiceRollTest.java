package game;

import game.dice.Dice;
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
        String roll = roll(d1,d2);
        final String[] token = roll.split("%");


        Assert.assertTrue("Roll Control pt1",
                (token[0].equals("1@SOLAR"))||
                        (token[0].equals("1@GOLD")));
        Assert.assertTrue("Roll Control pt2",
                (token[1].equals("1@LUNAR"))||
                        (token[1].equals("1@GOLD"))||
                        (token[1].equals("2@VICTORY")));

    }
}