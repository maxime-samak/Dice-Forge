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
        d1.solarDiceInit();//(1 SOLAR, 1 GOLD, 1 GOLD, 1 GOLD, 1 GOLD, 1 GOLD)
        d2 = new Dice();
        d2.lunarDiceInit();//(1 LUNAR, 2 VICTORY, 1 GOLD, 1 GOLD, 1 GOLD, 1 GOLD)
        bot1 = new SimpleBot(d2,d2,"bot1","\033[0m");
    }

    @Test
    public void getEffect() {
        //Test LES_SABOTS_D_ARGENT
        card = new DivineFavorCard(AbstractCard.Name.LES_SABOTS_D_ARGENT, AbstractCard.Type.RECURRENT, 2, 0, 2);
        card.getEffect(bot1);
        Assert.assertTrue(bot1.getBotScore().getGold() == 1 || bot1.getBotScore().getSolar() == 1 || bot1.getBotScore().getVictory() == 2 || bot1.getBotScore().getLunar() == 1);

        //Test L_ENIGME
        setup();
        card = new DivineFavorCard(AbstractCard.Name.L_ENIGME, AbstractCard.Type.INSTANT, 10, 6, 0);
        card.getEffect(bot1);// on lance 4 fois un dé
        Assert.assertTrue(bot1.getBotScore().getGold() <= 4 &&
                bot1.getBotScore().getSolar() <= 4 &&
                bot1.getBotScore().getVictory() <= 8 &&
                bot1.getBotScore().getLunar() <= 4 &&
                bot1.getBotScore().getSolar() + bot1.getBotScore().getVictory() + bot1.getBotScore().getLunar() + bot1.getBotScore().getGold() >= 4 &&
                bot1.getBotScore().getSolar() + bot1.getBotScore().getVictory() + bot1.getBotScore().getLunar() + bot1.getBotScore().getGold() <= 8 &&
                //On teste si on utilise bien le même dé.
                ((bot1.getBotScore().getSolar() != 0 && bot1.getBotScore().getVictory() + bot1.getBotScore().getLunar() == 0) //On utilise d1(solarDice)
                || (bot1.getBotScore().getVictory() + bot1.getBotScore().getLunar() != 0 && bot1.getBotScore().getSolar() == 0)//On utilise d2(lunarDice)
                || (bot1.getBotScore().getGold() == 4)));

        //Test LA_PINCE
        setup();
        card = new DivineFavorCard(AbstractCard.Name.LA_PINCE, AbstractCard.Type.INSTANT, 10, 6, 0);
        card.getEffect(bot1);// on lance 2 fois les deux dé
        Assert.assertTrue(bot1.getBotScore().getGold() <= 4 &&
                bot1.getBotScore().getSolar() <= 4 &&
                bot1.getBotScore().getVictory() <= 8 &&
                bot1.getBotScore().getLunar() <= 4 &&
                bot1.getBotScore().getSolar() + bot1.getBotScore().getVictory() + bot1.getBotScore().getLunar() + bot1.getBotScore().getGold() >= 4 &&
                bot1.getBotScore().getSolar() + bot1.getBotScore().getVictory() + bot1.getBotScore().getLunar() + bot1.getBotScore().getGold() <= 8);

    }
}
