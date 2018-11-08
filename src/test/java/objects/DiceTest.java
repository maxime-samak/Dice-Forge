package objects;

import org.junit.Assert;
import org.junit.Test;

public class DiceTest {

    @Test
    public void DiceTest() {

        Dice d1 = new Dice();
        d1.solarDiceInit();
        Dice d2 = new Dice();
        d2.lunarDiceInit();

        DiceCard dc1 = new DiceCard(1, Resource.GOLD);
        DiceCard dc2 = new DiceCard(1, Resource.GOLD);


        Assert.assertEquals(1,d1.getFi(1).getValue());
        Assert.assertEquals(1,d1.getFi(2).getValue());
        Assert.assertEquals(1,d1.getFi(3).getValue());
        Assert.assertEquals(1,d1.getFi(4).getValue());
        Assert.assertEquals(1,d1.getFi(5).getValue());
        Assert.assertEquals(1,d1.getFi(6).getValue());

        Assert.assertEquals("SOLAR",d1.getFi(1).getResource());
        Assert.assertEquals("GOLD",d1.getFi(2).getResource());
        Assert.assertEquals("GOLD",d1.getFi(3).getResource());
        Assert.assertEquals("GOLD",d1.getFi(4).getResource());
        Assert.assertEquals("GOLD",d1.getFi(5).getResource());
        Assert.assertEquals("GOLD",d1.getFi(6).getResource());

        Assert.assertEquals(1,d2.getFi(1).getValue());
        Assert.assertEquals(2,d2.getFi(2).getValue());
        Assert.assertEquals(1,d2.getFi(3).getValue());
        Assert.assertEquals(1,d2.getFi(4).getValue());
        Assert.assertEquals(1,d2.getFi(5).getValue());
        Assert.assertEquals(1,d2.getFi(6).getValue());

        Assert.assertEquals("LUNAR",d2.getFi(1).getResource());
        Assert.assertEquals("VICTORY",d2.getFi(2).getResource());
        Assert.assertEquals("GOLD",d2.getFi(3).getResource());
        Assert.assertEquals("GOLD",d2.getFi(4).getResource());
        Assert.assertEquals("GOLD",d2.getFi(5).getResource());
        Assert.assertEquals("GOLD",d2.getFi(6).getResource());

        Assert.assertTrue(dc1.equals(dc2));

    }
}