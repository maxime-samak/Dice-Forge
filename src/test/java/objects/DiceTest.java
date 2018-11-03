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

        Assert.assertEquals(Resource.SOLAR,d1.getFi(1).getResource().get(0));
        Assert.assertEquals(Resource.GOLD,d1.getFi(2).getResource().get(0));
        Assert.assertEquals(Resource.GOLD,d1.getFi(3).getResource().get(0));
        Assert.assertEquals(Resource.GOLD,d1.getFi(4).getResource().get(0));
        Assert.assertEquals(Resource.GOLD,d1.getFi(5).getResource().get(0));
        Assert.assertEquals(Resource.GOLD,d1.getFi(6).getResource().get(0));

        Assert.assertEquals(1,d1.getFi(1).getValue(d1.getFi(1).getResource().get(0)));
        Assert.assertEquals(1,d1.getFi(2).getValue(d1.getFi(2).getResource().get(0)));
        Assert.assertEquals(1,d1.getFi(3).getValue(d1.getFi(3).getResource().get(0)));
        Assert.assertEquals(1,d1.getFi(4).getValue(d1.getFi(4).getResource().get(0)));
        Assert.assertEquals(1,d1.getFi(5).getValue(d1.getFi(5).getResource().get(0)));
        Assert.assertEquals(1,d1.getFi(6).getValue(d1.getFi(6).getResource().get(0)));

        Assert.assertEquals(Resource.LUNAR,d2.getFi(1).getResource().get(0));
        Assert.assertEquals(Resource.VICTORY,d2.getFi(2).getResource().get(0));
        Assert.assertEquals(Resource.GOLD,d2.getFi(3).getResource().get(0));
        Assert.assertEquals(Resource.GOLD,d2.getFi(4).getResource().get(0));
        Assert.assertEquals(Resource.GOLD,d2.getFi(5).getResource().get(0));
        Assert.assertEquals(Resource.GOLD,d2.getFi(6).getResource().get(0));

        Assert.assertEquals(1,d2.getFi(1).getValue(d2.getFi(1).getResource().get(0)));
        Assert.assertEquals(2,d2.getFi(2).getValue(d2.getFi(2).getResource().get(0)));
        Assert.assertEquals(1,d2.getFi(3).getValue(d2.getFi(3).getResource().get(0)));
        Assert.assertEquals(1,d2.getFi(4).getValue(d2.getFi(4).getResource().get(0)));
        Assert.assertEquals(1,d2.getFi(5).getValue(d2.getFi(5).getResource().get(0)));
        Assert.assertEquals(1,d2.getFi(6).getValue(d2.getFi(6).getResource().get(0)));
    }
}