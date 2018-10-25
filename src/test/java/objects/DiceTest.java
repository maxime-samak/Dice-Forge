package objects;

import org.junit.Assert;
import org.junit.Test;

public class DiceTest {

    @Test
    public void DiceTest() {

        Dice d1=new Dice();
        d1.solarDiceInit();
        Dice d2=new Dice();
        d2.lunarDiceInit();

        Assert.assertEquals(d1.getFi(1).getResource(),Resource.SOLAR.resourceName());
        Assert.assertEquals(d1.getFi(2).getResource(),Resource.GOLD.resourceName());
        Assert.assertEquals(d1.getFi(3).getResource(),Resource.GOLD.resourceName());
        Assert.assertEquals(d1.getFi(4).getResource(),Resource.GOLD.resourceName());
        Assert.assertEquals(d1.getFi(5).getResource(),Resource.GOLD.resourceName());
        Assert.assertEquals(d1.getFi(6).getResource(),Resource.GOLD.resourceName());

        Assert.assertEquals(d1.getFi(1).getValue(),1);
        Assert.assertEquals(d1.getFi(2).getValue(),1);
        Assert.assertEquals(d1.getFi(3).getValue(),1);
        Assert.assertEquals(d1.getFi(4).getValue(),1);
        Assert.assertEquals(d1.getFi(5).getValue(),1);
        Assert.assertEquals(d1.getFi(6).getValue(),1);

        Assert.assertEquals(d2.getFi(1).getResource(),Resource.LUNAR.resourceName());
        Assert.assertEquals(d2.getFi(2).getResource(),Resource.VICTORY.resourceName());
        Assert.assertEquals(d2.getFi(3).getResource(),Resource.GOLD.resourceName());
        Assert.assertEquals(d2.getFi(4).getResource(),Resource.GOLD.resourceName());
        Assert.assertEquals(d2.getFi(5).getResource(),Resource.GOLD.resourceName());
        Assert.assertEquals(d2.getFi(6).getResource(),Resource.GOLD.resourceName());

        Assert.assertEquals(d2.getFi(1).getValue(),1);
        Assert.assertEquals(d2.getFi(2).getValue(),2);
        Assert.assertEquals(d2.getFi(3).getValue(),1);
        Assert.assertEquals(d2.getFi(4).getValue(),1);
        Assert.assertEquals(d2.getFi(5).getValue(),1);
        Assert.assertEquals(d2.getFi(6).getValue(),1);
    }
}