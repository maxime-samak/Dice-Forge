package game.dice;

import bot.SimpleBot;
import game.ScoreCounter;
import org.junit.Assert;
import org.junit.Test;

public class BuyDiceNeutralCardTest {

    @Test
    public void BuyDiceCardTest()
    {
        Dice d=new Dice();
        d.solarDiceInit();
        Dice d2=new Dice();
        d2.lunarDiceInit();
        Sanctuary s=new Sanctuary(2);
        SimpleBot b = new SimpleBot(d,d2,"test");
        DiceCard dc=s.getPoolAvailables(8).get(7);
        ScoreCounter.updateScore(b.getBotScore(),"4@GOLD%4@GOLD");

        BuyDiceCard.setCard(s,8,dc,d,3,b.getBotScore());
        Assert.assertEquals(dc,d.getFi(3));
        Assert.assertEquals(0,b.getBotScore().getGold());

    }
}