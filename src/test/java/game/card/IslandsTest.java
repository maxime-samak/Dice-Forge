package game.card;

import org.junit.*;
import java.util.ArrayList;
import java.util.*;

public class IslandsTest {

    @Test
    public void IslandsTest() {

        Islands i1 = new Islands(2);

        String s1 = "[LE_MARTEAU_DU_FORGERON, LE_MARTEAU_DU_FORGERON, LE_COFFRE_DU_FORGERON, LE_COFFRE_DU_FORGERON, L_ANCIEN, L_ANCIEN, LES_HERBES_FOLLES, LES_HERBES_FOLLES]";
        String s2 = "[LES_SABOTS_D_ARGENT, LES_SABOTS_D_ARGENT, LES_AILES_DE_LA_GARDIENNES, LES_AILES_DE_LA_GARDIENNES]";
        String s4 = "[LE_PASSEUR, LE_PASSEUR, LA_MEDUSE, LA_MEDUSE]";
        String s10 = "[L_HYDRE, L_HYDRE]";

        Assert.assertEquals(s1,i1.getIslandAvailables(1).toString());
        Assert.assertEquals(s2,i1.getIslandAvailables(2).toString());
        Assert.assertEquals(s4,i1.getIslandAvailables(4).toString());
        Assert.assertEquals(s10,i1.getIslandAvailables(10).toString());

    }

}
