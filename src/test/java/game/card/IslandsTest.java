package game.card;

import org.junit.Assert;
import org.junit.Test;

public class IslandsTest {

    @Test
    public void IslandsTest() {

        // test Islands content 2 players
        Islands i2 = new Islands(2);
        String s1 = "[LE_MARTEAU_DU_FORGERON, LE_MARTEAU_DU_FORGERON, LE_COFFRE_DU_FORGERON, LE_COFFRE_DU_FORGERON, L_ANCIEN, L_ANCIEN, LES_HERBES_FOLLES, LES_HERBES_FOLLES]";
        String s2 = "[LES_SABOTS_D_ARGENT, LES_SABOTS_D_ARGENT, LES_AILES_DE_LA_GARDIENNES, LES_AILES_DE_LA_GARDIENNES]";
        String s4 = "[LE_PASSEUR, LE_PASSEUR, LA_MEDUSE, LA_MEDUSE]";
        String s10 = "[L_HYDRE, L_HYDRE]";

        Assert.assertEquals(s1, i2.getIslandAvailables(1).toString());
        Assert.assertEquals(s2, i2.getIslandAvailables(2).toString());
        Assert.assertEquals(s4, i2.getIslandAvailables(4).toString());
        Assert.assertEquals(s10, i2.getIslandAvailables(10).toString());

        // test Islands content 3 players
        Islands i3 = new Islands(3);
        String st1 = "[LE_MARTEAU_DU_FORGERON, LE_MARTEAU_DU_FORGERON, LE_MARTEAU_DU_FORGERON, LE_COFFRE_DU_FORGERON, LE_COFFRE_DU_FORGERON, LE_COFFRE_DU_FORGERON, L_ANCIEN, L_ANCIEN, L_ANCIEN, LES_HERBES_FOLLES, LES_HERBES_FOLLES, LES_HERBES_FOLLES]";
        String st2 = "[LES_SABOTS_D_ARGENT, LES_SABOTS_D_ARGENT, LES_SABOTS_D_ARGENT, LES_AILES_DE_LA_GARDIENNES, LES_AILES_DE_LA_GARDIENNES, LES_AILES_DE_LA_GARDIENNES]";
        String st4 = "[LE_PASSEUR, LE_PASSEUR, LE_PASSEUR, LA_MEDUSE, LA_MEDUSE, LA_MEDUSE]";
        String st10 = "[L_HYDRE, L_HYDRE, L_HYDRE]";

        Assert.assertEquals(st1, i3.getIslandAvailables(1).toString());
        Assert.assertEquals(st2, i3.getIslandAvailables(2).toString());
        Assert.assertEquals(st4, i3.getIslandAvailables(4).toString());
        Assert.assertEquals(st10, i3.getIslandAvailables(10).toString());

        // test Islands content 4 players
        Islands i4 = new Islands(4);
        String str1 = "[LE_MARTEAU_DU_FORGERON, LE_MARTEAU_DU_FORGERON, LE_MARTEAU_DU_FORGERON, LE_MARTEAU_DU_FORGERON, LE_COFFRE_DU_FORGERON, LE_COFFRE_DU_FORGERON, LE_COFFRE_DU_FORGERON, LE_COFFRE_DU_FORGERON, L_ANCIEN, L_ANCIEN, L_ANCIEN, L_ANCIEN, LES_HERBES_FOLLES, LES_HERBES_FOLLES, LES_HERBES_FOLLES, LES_HERBES_FOLLES]";
        String str2 = "[LES_SABOTS_D_ARGENT, LES_SABOTS_D_ARGENT, LES_SABOTS_D_ARGENT, LES_SABOTS_D_ARGENT, LES_AILES_DE_LA_GARDIENNES, LES_AILES_DE_LA_GARDIENNES, LES_AILES_DE_LA_GARDIENNES, LES_AILES_DE_LA_GARDIENNES]";
        String str4 = "[LE_PASSEUR, LE_PASSEUR, LE_PASSEUR, LE_PASSEUR, LA_MEDUSE, LA_MEDUSE, LA_MEDUSE, LA_MEDUSE]";
        String str10 = "[L_HYDRE, L_HYDRE, L_HYDRE, L_HYDRE]";

        Assert.assertEquals(str1, i4.getIslandAvailables(1).toString());
        Assert.assertEquals(str2, i4.getIslandAvailables(2).toString());
        Assert.assertEquals(str4, i4.getIslandAvailables(4).toString());
        Assert.assertEquals(str10, i4.getIslandAvailables(10).toString());

        // test RemoveCard 2 players BlackSmithCard
        BlackSmithCard cardtest = new BlackSmithCard(AbstractCard.Name.LE_COFFRE_DU_FORGERON, AbstractCard.Type.INSTANT, 2, 0, 1);
        i2.removeCard(cardtest);
        s1 = "[LE_MARTEAU_DU_FORGERON, LE_MARTEAU_DU_FORGERON, LE_COFFRE_DU_FORGERON, L_ANCIEN, L_ANCIEN, LES_HERBES_FOLLES, LES_HERBES_FOLLES]";
        Assert.assertEquals(s1, i2.getIslandAvailables(1).toString());

        // test RemoveCard 3 players ResourceCard
        ResourceCard cardtest2 = new ResourceCard(AbstractCard.Name.L_ANCIEN, AbstractCard.Type.RECURRENT, 0, 1, 0);
        i3.removeCard(cardtest2);
        st1 = "[LE_MARTEAU_DU_FORGERON, LE_MARTEAU_DU_FORGERON, LE_MARTEAU_DU_FORGERON, LE_COFFRE_DU_FORGERON, LE_COFFRE_DU_FORGERON, LE_COFFRE_DU_FORGERON, L_ANCIEN, L_ANCIEN, LES_HERBES_FOLLES, LES_HERBES_FOLLES, LES_HERBES_FOLLES]";
        Assert.assertEquals(st1, i3.getIslandAvailables(1).toString());

        // test RemoveCard 4 players  DivineFavorCard
        DivineFavorCard cardtest3 = new DivineFavorCard(AbstractCard.Name.LES_SABOTS_D_ARGENT, AbstractCard.Type.RECURRENT, 2, 0, 2);
        i4.removeCard(cardtest3);
        str2 = "[LES_SABOTS_D_ARGENT, LES_SABOTS_D_ARGENT, LES_SABOTS_D_ARGENT, LES_AILES_DE_LA_GARDIENNES, LES_AILES_DE_LA_GARDIENNES, LES_AILES_DE_LA_GARDIENNES, LES_AILES_DE_LA_GARDIENNES]";
        Assert.assertEquals(str2, i4.getIslandAvailables(2).toString());

        // test RemoveCard 4 players RegularCard
        RegularCard cardtest4 = new RegularCard(AbstractCard.Name.LE_PASSEUR, AbstractCard.Type.INSTANT, 12, 0,4);
        i4.removeCard(cardtest4);
        str4 = "[LE_PASSEUR, LE_PASSEUR, LE_PASSEUR, LA_MEDUSE, LA_MEDUSE, LA_MEDUSE, LA_MEDUSE]";
        Assert.assertEquals(str4, i4.getIslandAvailables(4).toString());

        }

}
