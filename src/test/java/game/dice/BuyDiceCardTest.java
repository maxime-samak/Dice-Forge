package game.dice;

import bot.SimpleBot;
import game.ScoreCounter;
import org.junit.Assert;
import org.junit.Test;

public class BuyDiceCardTest {

    @Test
    public void BuyDiceCardTest()
    {
        Dice d1 = new Dice();
        d1.solarDiceInit();
        Dice d2 = new Dice();
        d2.lunarDiceInit();

        Sanctuary sanctuary = new Sanctuary(2);
        SimpleBot bot = new SimpleBot(d1,d2,"test");


        DiceCard card = new DiceCard(3, Resource.GOLD);
        ScoreCounter.updateScore(bot.getBotScore(),new DiceCard[]{new DiceCard(1, Resource.GOLD), new DiceCard(1, Resource.GOLD)});
        BuyDiceCard.setCard(sanctuary,2, card, d1,1, bot.getBotScore(),false);
        Assert.assertEquals(card, d1.getFi(1));
        Assert.assertEquals(0, bot.getBotScore().getGold());

        DiceCard card1 = new DiceCard(1, Resource.LUNAR);
        ScoreCounter.updateScore(bot.getBotScore(),new DiceCard[]{new DiceCard(1, Resource.GOLD), new DiceCard(1, Resource.GOLD)});
        BuyDiceCard.setCard(sanctuary,2, card1, d1,2, bot.getBotScore(),false);
        Assert.assertEquals(card1, d1.getFi(2));
        Assert.assertEquals(0, bot.getBotScore().getGold());

        DiceCard card2 = new DiceCard(4, Resource.GOLD);
        ScoreCounter.updateScore(bot.getBotScore(),new DiceCard[]{new DiceCard(2, Resource.GOLD), new DiceCard(1, Resource.GOLD)});
        BuyDiceCard.setCard(sanctuary,3, card2, d1,3, bot.getBotScore(),false);
        Assert.assertEquals(card2, d1.getFi(3));
        Assert.assertEquals(0, bot.getBotScore().getGold());

        DiceCard card3 = new DiceCard(1, Resource.SOLAR);
        ScoreCounter.updateScore(bot.getBotScore(),new DiceCard[]{new DiceCard(2, Resource.GOLD), new DiceCard(1, Resource.GOLD)});
        BuyDiceCard.setCard(sanctuary,3, card3, d1,4, bot.getBotScore(),false);
        Assert.assertEquals(card3, d1.getFi(4));
        Assert.assertEquals(0, bot.getBotScore().getGold());

        DiceCard card4 = new DiceCard(2, Resource.LUNAR);
        ScoreCounter.updateScore(bot.getBotScore(),new DiceCard[]{new DiceCard(3, Resource.GOLD), new DiceCard(3, Resource.GOLD)});
        BuyDiceCard.setCard(sanctuary,6, card4, d1,5, bot.getBotScore(),false);
        Assert.assertEquals(card4, d1.getFi(5));
        Assert.assertEquals(0, bot.getBotScore().getGold());

        DiceCard card5 = new DiceCard(3, Resource.VICTORY);
        ScoreCounter.updateScore(bot.getBotScore(),new DiceCard[]{new DiceCard(4, Resource.GOLD), new DiceCard(4, Resource.GOLD)});
        BuyDiceCard.setCard(sanctuary,8, card5, d1,6, bot.getBotScore(),false);
        Assert.assertEquals(card5, d1.getFi(6));
        Assert.assertEquals(0, bot.getBotScore().getGold());

        Assert.assertTrue(bot.getDice1().toString().equals("3 GOLD \n" +
                "1 LUNAR \n" +
                "4 GOLD \n" +
                "1 SOLAR \n" +
                "2 LUNAR \n" +
                "3 VICTORY \n"));

    }
}
