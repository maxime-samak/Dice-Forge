package game.card;

import bot.SimpleBot;
import game.dice.Dice;
import org.junit.Assert;
import org.junit.Test;

public class DivineFavorCardTest {

    @Test
    public void DivineFavorCardTest() {
        //Initialisation du SimpleBot
        Dice d1 = new Dice();
        d1.solarDiceInit();
        Dice d2 = new Dice();
        d2.lunarDiceInit();
        SimpleBot bot1 = new SimpleBot(d1, d2, "bot1", "\033[0m");

        DivineFavorCard c1 = new DivineFavorCard(AbstractCard.Name.LES_SABOTS_D_ARGENT, AbstractCard.Type.RECURRENT, 2, 0, 2);
        c1.getEffect(bot1);
        Assert.assertTrue(bot1.getBotScore().getGold() == 1 || bot1.getBotScore().getSolar() == 1 || bot1.getBotScore().getVictory() == 2 || bot1.getBotScore().getLunar() == 1);

        DivineFavorCard c2 = new DivineFavorCard(AbstractCard.Name.L_ENIGME, AbstractCard.Type.INSTANT, 10, 6, 0);
        c2.getEffect(bot1);



    }
}
