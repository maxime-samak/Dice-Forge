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

        Assert.assertEquals(Resource.SOLAR.resourceName(),d1.getFi(1).getResource());
        Assert.assertEquals(Resource.GOLD.resourceName(),d1.getFi(2).getResource());
        Assert.assertEquals(Resource.GOLD.resourceName(),d1.getFi(3).getResource());
        Assert.assertEquals(Resource.GOLD.resourceName(),d1.getFi(4).getResource());
        Assert.assertEquals(Resource.GOLD.resourceName(),d1.getFi(5).getResource());
        Assert.assertEquals(Resource.GOLD.resourceName(),d1.getFi(6).getResource());

        Assert.assertEquals(1,d1.getFi(1).getValue());
        Assert.assertEquals(1,d1.getFi(2).getValue());
        Assert.assertEquals(1,d1.getFi(3).getValue());
        Assert.assertEquals(1,d1.getFi(4).getValue());
        Assert.assertEquals(1,d1.getFi(5).getValue());
        Assert.assertEquals(1,d1.getFi(6).getValue());

        Assert.assertEquals(Resource.LUNAR.resourceName(),d2.getFi(1).getResource());
        Assert.assertEquals(Resource.VICTORY.resourceName(),d2.getFi(2).getResource());
        Assert.assertEquals(Resource.GOLD.resourceName(),d2.getFi(3).getResource());
        Assert.assertEquals(Resource.GOLD.resourceName(),d2.getFi(4).getResource());
        Assert.assertEquals(Resource.GOLD.resourceName(),d2.getFi(5).getResource());
        Assert.assertEquals(Resource.GOLD.resourceName(),d2.getFi(6).getResource());

        Assert.assertEquals(1,d2.getFi(1).getValue());
        Assert.assertEquals(2,d2.getFi(2).getValue());
        Assert.assertEquals(1,d2.getFi(3).getValue());
        Assert.assertEquals(1,d2.getFi(4).getValue());
        Assert.assertEquals(1,d2.getFi(5).getValue());
        Assert.assertEquals(1,d2.getFi(6).getValue());
    }
}