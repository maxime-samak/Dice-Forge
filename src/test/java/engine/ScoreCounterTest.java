package engine;

import org.junit.Assert;
import org.junit.Test;

public class ScoreCounterTest {

    @Test
    public void ScoreDiceTest() {

        BotScore bs = new BotScore();
        ScoreCounter sc = new ScoreCounter();

        sc.updateScore(bs, "1@SOLAR%2@VICTORY");
        Assert.assertEquals(1, bs.getSolar());
        Assert.assertEquals(2, bs.getVictory());

        sc.updateScore(bs, "1@SOLAR%2@VICTORY");
        Assert.assertEquals(2, bs.getSolar());
        Assert.assertEquals(4, bs.getVictory());

        sc.updateScore(bs, "1@GOLD%1@GOLD");
        Assert.assertEquals(2, bs.getGold());

        sc.updateScore(bs, "1@SOLAR%1@GOLD");
        Assert.assertEquals(3, bs.getSolar());
        Assert.assertEquals(3, bs.getGold());

        sc.updateScore(bs, "1@SOLAR%1@LUNAR");
        Assert.assertEquals(4, bs.getSolar());
        Assert.assertEquals(1, bs.getLunar());

        sc.updateScore(bs, "1@GOLD%1@LUNAR");
        Assert.assertEquals(4, bs.getGold());
        Assert.assertEquals(2, bs.getLunar());

        sc.updateScore(bs, "2@VICTORY%1@SOLAR");
        Assert.assertNotEquals(6, bs.getVictory()); // d1 de la string roll doit être un dé solar
        Assert.assertNotEquals(5, bs.getSolar()); // d2 de la string roll doit être un dé lunar

    }
}
