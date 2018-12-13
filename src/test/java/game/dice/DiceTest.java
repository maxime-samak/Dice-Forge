package game.dice;

import org.junit.Assert;
import org.junit.Test;

public class DiceTest {

    @Test
    public void DiceTest() {

        Dice dice1 = new Dice();
        dice1.solarDiceInit();
        Dice dice2 = new Dice();
        dice2.lunarDiceInit();


        Assert.assertEquals(1,dice1.getFi(1).getValue());
        Assert.assertEquals(1,dice1.getFi(2).getValue());
        Assert.assertEquals(1,dice1.getFi(3).getValue());
        Assert.assertEquals(1,dice1.getFi(4).getValue());
        Assert.assertEquals(1,dice1.getFi(5).getValue());
        Assert.assertEquals(1,dice1.getFi(6).getValue());

        Assert.assertEquals(Resource.SOLAR.resourceName(),dice1.getFi(1).getResource());
        Assert.assertEquals(Resource.GOLD.resourceName(),dice1.getFi(2).getResource());
        Assert.assertEquals(Resource.GOLD.resourceName(),dice1.getFi(3).getResource());
        Assert.assertEquals(Resource.GOLD.resourceName(),dice1.getFi(4).getResource());
        Assert.assertEquals(Resource.GOLD.resourceName(),dice1.getFi(5).getResource());
        Assert.assertEquals(Resource.GOLD.resourceName(),dice1.getFi(6).getResource());

        Assert.assertEquals(1,dice2.getFi(1).getValue());
        Assert.assertEquals(2,dice2.getFi(2).getValue());
        Assert.assertEquals(1,dice2.getFi(3).getValue());
        Assert.assertEquals(1,dice2.getFi(4).getValue());
        Assert.assertEquals(1,dice2.getFi(5).getValue());
        Assert.assertEquals(1,dice2.getFi(6).getValue());

        Assert.assertEquals(Resource.LUNAR.resourceName(),dice2.getFi(1).getResource());
        Assert.assertEquals(Resource.VICTORY.resourceName(),dice2.getFi(2).getResource());
        Assert.assertEquals(Resource.GOLD.resourceName(),dice2.getFi(3).getResource());
        Assert.assertEquals(Resource.GOLD.resourceName(),dice2.getFi(4).getResource());
        Assert.assertEquals(Resource.GOLD.resourceName(),dice2.getFi(5).getResource());
        Assert.assertEquals(Resource.GOLD.resourceName(),dice2.getFi(6).getResource());


    }
}