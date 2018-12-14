package game.dice;

import org.junit.*;

import java.util.ArrayList;
import java.util.*;

import static game.dice.Resource.GOLD;
import static game.dice.Resource.LUNAR;


public class SanctuaryTest {
    Sanctuary sanctuary = new Sanctuary(2);

    @Test
    public void initPool() {
        sanctuary.initPool(2);

        Assert.assertEquals(7, sanctuary.getPools().keySet().size());

        DiceCard f1 = new DiceCard(1, LUNAR);
        DiceCard f2 = new DiceCard(3, GOLD);

        for (DiceCard c : sanctuary.getPools().get(2)) {
            Assert.assertTrue(c.equals(f1) || c.equals(f2));
        }

        DiceCard f3 = new DiceCard(1, Resource.SOLAR);
        DiceCard f4 = new DiceCard(4, GOLD);

        for (DiceCard c : sanctuary.getPools().get(3)) {
            Assert.assertTrue(c.equals(f3) || c.equals(f4));
        }
        DiceCard f0 = new DiceCard(6, GOLD);
        DiceCard f5 = new DiceCard(new int[]{0, 2, 1} , new Resource[]{Resource.PLUS, GOLD, LUNAR});
        DiceCard f6 = new DiceCard(new int[]{0, 1, 1}, new Resource[]{Resource.PLUS, Resource.VICTORY, Resource.SOLAR});
        DiceCard f7 = new DiceCard(new int[]{0, 1, 1, 1}, new Resource[]{Resource.CHOICE, GOLD, Resource.SOLAR, LUNAR});

        for (DiceCard c : sanctuary.getPools().get(4)) {
            Assert.assertTrue(c.equals(f5) || c.equals(f6) || c.equals(f7) || c.equals(f0));
        }

        DiceCard f8 = new DiceCard(new int[]{0, 3, 2}, new Resource[]{Resource.CHOICE, Resource.VICTORY, GOLD});

        for (DiceCard c : sanctuary.getPools().get(5)) {
            Assert.assertTrue(c.equals(f8));
        }

        DiceCard f9 = new DiceCard(2, LUNAR);

        for (DiceCard c : sanctuary.getPools().get(6)) {
            Assert.assertTrue(c.equals(f9));
        }

        DiceCard f10 = new DiceCard(2, Resource.SOLAR);
        DiceCard f11 = new DiceCard(3, Resource.VICTORY);

        for (DiceCard c : sanctuary.getPools().get(8)) {
            Assert.assertTrue(c.equals(f10) || c.equals(f11));
        }

        DiceCard f12 = new DiceCard(4, Resource.VICTORY);
        DiceCard f13 = new DiceCard(new int[]{0, 2 ,2}, new  Resource[]{Resource.PLUS, Resource.VICTORY, LUNAR});
        DiceCard f14 = new DiceCard(new int[]{0, 2, 2, 2}, new Resource[]{Resource.CHOICE, GOLD, Resource.SOLAR, LUNAR});
        DiceCard f15 = new DiceCard(new int[]{0, 1, 1, 1, 1}, new Resource[]{Resource.PLUS, Resource.VICTORY, GOLD, Resource.SOLAR, LUNAR});

        for (DiceCard c : sanctuary.getPools().get(12)) {
            Assert.assertTrue(c.equals(f12) || c.equals(f13) || c.equals(f14) || c.equals(f15));
        }
    }

    @Test
    public void getPoolAvailables() {
        sanctuary.initPool(2);

        Assert.assertEquals("[1 [0;34mLUNAR[0m , 1 [0;34mLUNAR[0m , 3 [0;33mGOLD[0m , 3 [0;33mGOLD[0m ]",(sanctuary.getPoolAvailables(2).toString()));
        Assert.assertEquals("[1 \u001B[0;31mSOLAR\u001B[0m , 1 \u001B[0;31mSOLAR\u001B[0m , 4 \u001B[0;33mGOLD\u001B[0m , 4 \u001B[0;33mGOLD\u001B[0m ]",(sanctuary.getPoolAvailables(3).toString()));
        Assert.assertEquals("[CHOICE 3 \u001B[0;32mVICTORY\u001B[0m 2 \u001B[0;33mGOLD\u001B[0m , CHOICE 3 \u001B[0;32mVICTORY\u001B[0m 2 \u001B[0;33mGOLD\u001B[0m ]",(sanctuary.getPoolAvailables(5).toString()));
        Assert.assertEquals("[2 \u001B[0;34mLUNAR\u001B[0m , 2 \u001B[0;34mLUNAR\u001B[0m ]",(sanctuary.getPoolAvailables(6).toString()));
        Assert.assertEquals("[2 \u001B[0;31mSOLAR\u001B[0m , 2 \u001B[0;31mSOLAR\u001B[0m , 3 \u001B[0;32mVICTORY\u001B[0m , 3 \u001B[0;32mVICTORY\u001B[0m ]",(sanctuary.getPoolAvailables(8).toString()));
    }

    @Test
    public void removeCard() {
        sanctuary.initPool(1);

        DiceCard f1 = new DiceCard(1, LUNAR);
        DiceCard f2 = new DiceCard(3, GOLD);

        Assert.assertTrue(sanctuary.removeCard(2, f1));
        Assert.assertTrue(sanctuary.removeCard(2, f2));
        Assert.assertFalse(sanctuary.removeCard(2, f1));
        Assert.assertFalse(sanctuary.removeCard(2, f2));

        DiceCard f3 = new DiceCard(1, Resource.SOLAR);
        DiceCard f4 = new DiceCard(4, GOLD);

        Assert.assertTrue(sanctuary.removeCard(3, f3));
        Assert.assertTrue(sanctuary.removeCard(3, f4));
        Assert.assertFalse(sanctuary.removeCard(3, f3));
        Assert.assertFalse(sanctuary.removeCard(3, f4));

        DiceCard f8 = new DiceCard(new int[]{0, 3, 2}, new Resource[]{Resource.CHOICE, Resource.VICTORY, GOLD});

        Assert.assertTrue(sanctuary.removeCard(5, f8));
        Assert.assertFalse(sanctuary.removeCard(5, f8));

    }

}
