package engine;

import objects.Dice;
import org.junit.Assert;
import org.junit.Test;

public class DiceRollTest {

    @Test
    public void diceTest(){
        Dice d1 = new Dice();
        d1.solarDiceInit();
        Dice d2 = new Dice();
        d2.lunarDiceInit();

        //Assert.assertEquals();

    }
}