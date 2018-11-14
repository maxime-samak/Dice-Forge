package bot;

import game.ScoreCounter;
import game.dice.Dice;
import game.dice.Sanctuary;
import org.junit.Assert;
import org.junit.Test;

public class SimpleBotTest {

    @Test
    public void SimpleBotTest(){
        Dice d1 = new Dice();
        d1.solarDiceInit();
        Dice d2 = new Dice();
        d2.lunarDiceInit();

        Sanctuary sanctuary = new Sanctuary(2);

        SimpleBot bot1 = new SimpleBot(d1,d2,"bot1");
        Assert.assertNotNull(bot1);

        //Assert.assertFalse(bot1.diceShopping(sanctuary,12));
        Assert.assertFalse(bot1.diceShopping(sanctuary,8));
        Assert.assertFalse(bot1.diceShopping(sanctuary,6));
        //Assert.assertFalse(bot1.diceShopping(sanctuary,5));
        //Assert.assertFalse(bot1.diceShopping(sanctuary,4));
        Assert.assertFalse(bot1.diceShopping(sanctuary,3));
        Assert.assertFalse(bot1.diceShopping(sanctuary,2));

        ScoreCounter.updateScore(bot1.getBotScore(), "12@GOLD%12@GOLD");

        Assert.assertTrue(bot1.diceShopping(sanctuary,8));
        Assert.assertTrue(bot1.diceShopping(sanctuary,2));
        Assert.assertFalse(bot1.diceShopping(sanctuary,2));

        ScoreCounter.updateScore(bot1.getBotScore(), "12@GOLD%12@GOLD");

        Assert.assertTrue(bot1.diceShopping(sanctuary,6));
        Assert.assertTrue(bot1.diceShopping(sanctuary,3));
        Assert.assertFalse(bot1.diceShopping(sanctuary,3));
    }

}