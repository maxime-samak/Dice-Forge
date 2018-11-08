package engine;

import org.junit.Assert;
import org.junit.Test;

public class BotScoreTest {

    @Test
    public void BotScoreTest() {

        // Test BotScore addGold
        BotScore bs = new BotScore();
        bs.addGold(12);
        Assert.assertEquals(12,bs.getGold());
        bs.addGold(2000);
        Assert.assertEquals(12,bs.getGold());
        bs.addGold(-5000);
        Assert.assertEquals(12,bs.getGold());

        // Test BotScore addSolar
        bs.addSolar(6);
        Assert.assertEquals(6,bs.getSolar());
        bs.addSolar(2000);
        Assert.assertEquals(6,bs.getSolar());
        bs.addSolar(-5000);
        Assert.assertEquals(6,bs.getSolar());

        // Test BotScore addLunar
        bs.addLunar(6);
        Assert.assertEquals(6,bs.getLunar());
        bs.addLunar(2000);
        Assert.assertEquals(6,bs.getLunar());
        bs.addLunar(-5000);
        Assert.assertEquals(6,bs.getLunar());

        // Test BotScore addVictory
        bs.addVictory(-5000);
        Assert.assertEquals(5000,bs.getVictory());


    }








}
