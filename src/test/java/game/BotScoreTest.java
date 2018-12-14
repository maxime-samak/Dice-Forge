package game;

import org.junit.Assert;
import org.junit.Test;

public class BotScoreTest {
    BotScore bs = new BotScore();

    @Test
    public void addGold() {

        bs = new BotScore();

        Assert.assertEquals(0,bs.getGold());
        bs.addGold(0);
        Assert.assertEquals(0,bs.getGold());
        bs.addGold(12);
        Assert.assertEquals(12,bs.getGold());
        bs.addGold(2000);
        Assert.assertEquals(12,bs.getGold());
        bs.addGold(-5000);
        Assert.assertEquals(12,bs.getGold());
    }

    @Test
    public void addSolar() {

        bs = new BotScore();

        Assert.assertEquals(0,bs.getSolar());
        bs.addSolar(0);
        Assert.assertEquals(0,bs.getSolar());
        bs.addSolar(6);
        Assert.assertEquals(6,bs.getSolar());
        bs.addSolar(2000);
        Assert.assertEquals(6,bs.getSolar());
        bs.addSolar(-5000);
        Assert.assertEquals(6,bs.getSolar());
    }

    @Test
    public void addLunar() {

        bs = new BotScore();

        Assert.assertEquals(0,bs.getLunar());
        bs.addLunar(0);
        Assert.assertEquals(0,bs.getLunar());
        bs.addLunar(6);
        Assert.assertEquals(6,bs.getLunar());
        bs.addLunar(2000);
        Assert.assertEquals(6,bs.getLunar());
        bs.addLunar(-5000);
        Assert.assertEquals(6,bs.getLunar());
    }

    @Test
    public void addVictory() {

        bs = new BotScore();

        Assert.assertEquals(0,bs.getVictory());
        bs.addVictory(0);
        Assert.assertEquals(0,bs.getVictory());
        bs.addVictory(-5000);
        Assert.assertEquals(5000,bs.getVictory());
    }

    @Test
    public void removeGold() {

        bs = new BotScore();

        bs.addGold(1);
        Assert.assertEquals(1,bs.getGold());
        bs.removeGold(0);
        Assert.assertEquals(1,bs.getGold());
        bs.removeGold(1000);
        Assert.assertEquals(0,bs.getGold());
        bs.addGold(6);
        Assert.assertEquals(6,bs.getGold());
        bs.removeGold(3);
        Assert.assertEquals(3,bs.getGold());
        bs.removeGold(-5000);
        Assert.assertEquals(0,bs.getGold());
    }

    @Test
    public void removeSolar() {

        bs = new BotScore();
        bs.addSolar(1);
        Assert.assertEquals(1,bs.getSolar());
        bs.removeSolar(0);
        Assert.assertEquals(1,bs.getSolar());
        bs.removeSolar(1000);
        Assert.assertEquals(0,bs.getSolar());
        bs.addSolar(6);
        Assert.assertEquals(6,bs.getSolar());
        bs.removeSolar(3);
        Assert.assertEquals(3,bs.getSolar());
        bs.removeSolar(-5000);
        Assert.assertEquals(0,bs.getSolar());
    }

    @Test
    public void removeLunar() {

        bs = new BotScore();

        bs.addLunar(1);
        Assert.assertEquals(1,bs.getLunar());
        bs.removeLunar(0);
        Assert.assertEquals(1,bs.getLunar());
        bs.removeLunar(1000);
        Assert.assertEquals(0,bs.getLunar());
        bs.addLunar(6);
        Assert.assertEquals(6,bs.getLunar());
        bs.removeLunar(3);
        Assert.assertEquals(3,bs.getLunar());
        bs.removeLunar(-5000);
        Assert.assertEquals(0,bs.getLunar());
    }
}