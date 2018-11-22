package game;

import game.dice.DiceCard;
import game.dice.Resource;
import org.junit.Assert;
import org.junit.Test;

public class ScoreCounterTest {

    @Test
    public void ScoreDiceTest() {

        BotScore bs = new BotScore();
        ScoreCounter sc = new ScoreCounter();

        sc.updateScore(bs, new DiceCard[]{new DiceCard(1, Resource.SOLAR), new DiceCard(2, Resource.VICTORY)});
        Assert.assertEquals(1, bs.getSolar());
        Assert.assertEquals(2, bs.getVictory());

        sc.updateScore(bs, new DiceCard[]{new DiceCard(1, Resource.SOLAR), new DiceCard(2, Resource.VICTORY)});
        Assert.assertEquals(2, bs.getSolar());
        Assert.assertEquals(4, bs.getVictory());

        sc.updateScore(bs, new DiceCard[]{new DiceCard(1, Resource.GOLD), new DiceCard(1, Resource.GOLD)});

        Assert.assertEquals(2, bs.getGold());

        sc.updateScore(bs, new DiceCard[]{new DiceCard(1, Resource.SOLAR), new DiceCard(1, Resource.GOLD)});

        Assert.assertEquals(3, bs.getSolar());
        Assert.assertEquals(3, bs.getGold());

        sc.updateScore(bs, new DiceCard[]{new DiceCard(1, Resource.SOLAR), new DiceCard(2, Resource.LUNAR)});

        Assert.assertEquals(4, bs.getSolar());
        Assert.assertEquals(1, bs.getLunar());

        sc.updateScore(bs, new DiceCard[]{new DiceCard(1, Resource.GOLD), new DiceCard(1, Resource.LUNAR)});

        Assert.assertEquals(4, bs.getGold());
        Assert.assertEquals(2, bs.getLunar());

        sc.updateScore(bs, new DiceCard[]{new DiceCard(2, Resource.VICTORY), new DiceCard(1, Resource.SOLAR)});

        Assert.assertNotEquals(6, bs.getVictory()); // d1 de la string roll doit être un dé solar
        Assert.assertNotEquals(5, bs.getSolar()); // d2 de la string roll doit être un dé lunar

    }
}
