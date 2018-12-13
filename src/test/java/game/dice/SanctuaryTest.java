package game.dice;

import org.junit.*;

import java.util.ArrayList;
import java.util.*;


public class SanctuaryTest {

    @Test
    public void SanctuaryTest() {

        ArrayList<String> poolDeux = new ArrayList<String>();
        poolDeux.add("1 "+Resource.LUNAR.resourceName()+" ");
        poolDeux.add("1 "+Resource.LUNAR.resourceName()+" ");
        poolDeux.add("3 "+Resource.GOLD.resourceName()+" ");
        poolDeux.add("3 "+Resource.GOLD.resourceName()+" ");

        ArrayList<String> poolTrois = new ArrayList<String>();
        poolTrois.add("1 "+Resource.SOLAR.resourceName()+" ");
        poolTrois.add("1 "+Resource.SOLAR.resourceName()+" ");
        poolTrois.add("4 "+Resource.GOLD.resourceName()+" ");
        poolTrois.add("4 "+Resource.GOLD.resourceName()+" ");

        ArrayList<String> poolSix = new ArrayList<String>();
        poolSix.add("2 "+Resource.LUNAR.resourceName()+" ");
        poolSix.add("2 "+Resource.LUNAR.resourceName()+" ");

        ArrayList<String> poolHuit = new ArrayList<String>();
        poolHuit.add("2 "+Resource.SOLAR.resourceName()+" ");
        poolHuit.add("2 "+Resource.SOLAR.resourceName()+" ");
        poolHuit.add("3 "+Resource.VICTORY.resourceName()+" ");
        poolHuit.add("3 "+Resource.VICTORY.resourceName()+" ");

        // 2 Joueurs
        Sanctuary s1 = new Sanctuary(2);
        Assert.assertEquals(poolDeux.toString(),s1.getPoolAvailables(2).toString());
        Assert.assertEquals(poolTrois.toString(),s1.getPoolAvailables(3).toString());
        Assert.assertEquals(poolSix.toString(),s1.getPoolAvailables(6).toString());
        Assert.assertEquals(poolHuit.toString(),s1.getPoolAvailables(8).toString());


    }

}
