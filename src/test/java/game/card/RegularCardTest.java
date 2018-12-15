package game.card;

import bot.AbstractBot;
import bot.SimpleBot;
import game.dice.Dice;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegularCardTest {

    private AbstractCard card;
    private AbstractBot bot;
    private Dice d1;
    private Dice d2;

    @Before
    public void setup(){
        d1 = new Dice();
        d1.solarDiceInit();
        d2 = new Dice();
        d2.lunarDiceInit();
        bot = new SimpleBot(d1, d2, "bot1", "\033[0m");
        card = new RegularCard(AbstractCard.Name.LE_PASSEUR, AbstractCard.Type.INSTANT, 12, 0,4);
    }

    @Test
    public void getEffect() {
        assertNull(card.getEffect(bot));
    }

    //Test classe abstraite AbstractCard
    @Test
    public void equals() {
        assertTrue(card.equals(card));

        AbstractCard cardTest = new RegularCard(AbstractCard.Name.LE_PASSEUR, AbstractCard.Type.RECURRENT, 120, 10,40);
        assertTrue(card.equals(cardTest)); //Le test passe si il on le même nom.

        cardTest = new RegularCard(AbstractCard.Name.LA_MEDUSE, AbstractCard.Type.INSTANT, 12, 0,4);
        assertFalse(card.equals(cardTest));

        cardTest = new DivineFavorCard(AbstractCard.Name.LE_PASSEUR, AbstractCard.Type.RECURRENT, 2, 0, 2);
        assertTrue(card.equals(cardTest));
    }

    @Test
    public void getPrice() {
        int solar_cost = card.getPrice()[0];
        int lunar_cost = card.getPrice()[1];
        assertTrue(0 == solar_cost && 4 == lunar_cost);
    }

    @Test
    public void getName() {
        assertEquals(AbstractCard.Name.LE_PASSEUR, card.getName());
        assertNotEquals("LE_PASSEUR", card.getName());
    }

    @Test
    public void getType() {
        assertEquals(AbstractCard.Type.INSTANT, card.getType());
        assertNotEquals("INSTANT", card.getType());
    }

    @Test
    public void getVictory() {
        assertEquals(12, card.getVictory());
    }

    @Test
    public void toStringTest() {
        assertEquals("LE_PASSEUR", card.toString());
    }

    @Test
    public void effectToString() {
        assertEquals("Pas d’effet. Cette carte ne donne que des points de victoire.", card.effectToString());
    }
}