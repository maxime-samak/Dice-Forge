package game.dice;

import org.junit.*;

import java.util.ArrayList;


public class SanctuaryTest {

    @Test
    public void SanctuaryTest() {
        // 2 Joueurs
        ArrayList<String> poolDeux=new ArrayList<String>();
        poolDeux.add("1 LUNAR");
        poolDeux.add("1 LUNAR");
        poolDeux.add("3 GOLD");
        poolDeux.add("3 GOLD");

        ArrayList<String> poolTrois=new ArrayList<String>();
        poolTrois.add("1 SOLAR");
        poolTrois.add("1 SOLAR");
        poolTrois.add("4 GOLD");
        poolTrois.add("4 GOLD");

        ArrayList<String> poolSix=new ArrayList<String>();
        poolSix.add("2 LUNAR");
        poolSix.add("2 LUNAR");

        ArrayList<String> poolHuit=new ArrayList<String>();
        poolHuit.add("2 SOLAR");
        poolHuit.add("2 SOLAR");
        poolHuit.add("3 VICTORY");
        poolHuit.add("3 VICTORY");

        Sanctuary s1 = new Sanctuary(2);
        Assert.assertEquals(poolDeux.toString(),s1.getPoolAvailables(2).toString());
        Assert.assertEquals(poolTrois.toString(),s1.getPoolAvailables(3).toString());
        Assert.assertEquals(poolSix.toString(),s1.getPoolAvailables(6).toString());
        Assert.assertEquals(poolHuit.toString(),s1.getPoolAvailables(8).toString());




    }

}
