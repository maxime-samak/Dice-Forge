package game;

import bot.AbstractBot;
import bot.SimpleBot;
import game.dice.Dice;
import game.dice.DiceCard;
import game.dice.Resource;
import org.junit.Assert;
import org.junit.Test;

public class ScoreCounterTest {

    @Test
    public void ScoreDiceTest() {
        Dice d1 = new Dice();
        d1.solarDiceInit();
        Dice d2 = new Dice();
        d2.lunarDiceInit();
        SimpleBot bot = new SimpleBot(d1,d2,"test","\033[0m");

        BotScore bs = new BotScore();
        ScoreCounter sc = new ScoreCounter();

        sc.updateScore(bot, new DiceCard[]{new DiceCard(1, Resource.SOLAR), new DiceCard(2, Resource.VICTORY)});
        Assert.assertEquals(1, bot.getBotScore().getSolar());
        Assert.assertEquals(2, bot.getBotScore().getVictory());

        sc.updateScore(bot, new DiceCard[]{new DiceCard(1, Resource.SOLAR), new DiceCard(2, Resource.VICTORY)});
        Assert.assertEquals(2, bot.getBotScore().getSolar());
        Assert.assertEquals(4, bot.getBotScore().getVictory());

        sc.updateScore(bot, new DiceCard[]{new DiceCard(1, Resource.GOLD), new DiceCard(1, Resource.GOLD)});

        Assert.assertEquals(2, bot.getBotScore().getGold());

        sc.updateScore(bot, new DiceCard[]{new DiceCard(1, Resource.SOLAR), new DiceCard(1, Resource.GOLD)});

        Assert.assertEquals(3, bot.getBotScore().getSolar());
        Assert.assertEquals(3, bot.getBotScore().getGold());

        sc.updateScore(bot, new DiceCard[]{new DiceCard(1, Resource.SOLAR), new DiceCard(1, Resource.LUNAR)});

        Assert.assertEquals(4, bot.getBotScore().getSolar());
        Assert.assertEquals(1, bot.getBotScore().getLunar());

        sc.updateScore(bot, new DiceCard[]{new DiceCard(1, Resource.GOLD), new DiceCard(1, Resource.LUNAR)});

        Assert.assertEquals(4, bot.getBotScore().getGold());
        Assert.assertEquals(2, bot.getBotScore().getLunar());

        sc.updateScore(bot, new DiceCard[]{new DiceCard(2, Resource.VICTORY), new DiceCard(1, Resource.SOLAR)});

        Assert.assertNotEquals(5, bot.getBotScore().getVictory());
        Assert.assertNotEquals(4, bot.getBotScore().getSolar());
    }
}
