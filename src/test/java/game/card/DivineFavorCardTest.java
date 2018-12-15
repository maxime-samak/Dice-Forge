package game.card;

import bot.SimpleBot;
import game.dice.Dice;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DivineFavorCardTest {

    private SimpleBot bot1;
    private Dice d1;
    private Dice d2;
    private DivineFavorCard  card;

    @Before
    public void setup() {
        d1 = new Dice();
        d1.solarDiceInit();
        d2 = new Dice();
        d2.lunarDiceInit();
        bot1 = new SimpleBot(d2,d2,"bot1","\033[0m");
    }

    @Test
    public void getEffect() {
        card = new DivineFavorCard(AbstractCard.Name.LES_SABOTS_D_ARGENT, AbstractCard.Type.RECURRENT, 2, 0, 2);
        card.getEffect(bot1);
        Assert.assertTrue(bot1.getBotScore().getGold() == 1 || bot1.getBotScore().getSolar() == 1 || bot1.getBotScore().getVictory() == 2 || bot1.getBotScore().getLunar() == 1);

        card = new DivineFavorCard(AbstractCard.Name.L_ENIGME, AbstractCard.Type.INSTANT, 10, 6, 0);
        card.getEffect(bot1);
    }
}
