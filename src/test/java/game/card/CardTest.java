package game.card;

import org.junit.Assert;
import org.junit.Test;

public class CardTest {

    @Test
    public void CardTest() {

        // carte sans effet
        Card c1 = new RegularCard(AbstractCard.Name.LE_PASSEUR, AbstractCard.Type.INSTANT, 12, 0,4);
        Card c2 = new RegularCard(AbstractCard.Name.LA_MEDUSE, AbstractCard.Type.INSTANT, 14, 4,0);
        Card c3 = new RegularCard(AbstractCard.Name.L_HYDRE, AbstractCard.Type.INSTANT, 26, 5, 5);

        Assert.assertEquals(12, c1.getVictory());
        Assert.assertEquals(0, c1.getPrice()[0]);
        Assert.assertEquals(4, c1.getPrice()[1]);
        Assert.assertEquals("LE_PASSEUR", c1.getName().toString());
        Assert.assertEquals("INSTANT", c1.getType().toString());

        Assert.assertEquals(14, c2.getVictory());
        Assert.assertEquals(4, c2.getPrice()[0]);
        Assert.assertEquals(0, c2.getPrice()[1]);
        Assert.assertEquals("LA_MEDUSE", c2.getName().toString());
        Assert.assertEquals("INSTANT", c2.getType().toString());

        Assert.assertEquals(26, c3.getVictory());
        Assert.assertEquals(5, c3.getPrice()[0]);
        Assert.assertEquals(5, c3.getPrice()[1]);
        Assert.assertEquals("L_HYDRE", c3.getName().toString());
        Assert.assertEquals("INSTANT", c3.getType().toString());

    }
}


