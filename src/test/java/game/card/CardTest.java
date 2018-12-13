package game.card;

import bot.SavingBot;
import bot.SimpleBot;
import game.BotScore;
import game.ScoreCounter;
import game.dice.Dice;
import game.dice.DiceCard;
import game.dice.Resource;
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
        Assert.assertEquals(14, c2.getVictory());
        Assert.assertEquals(26, c3.getVictory());

        Assert.assertEquals(0, c1.getPrice()[0]);
        Assert.assertEquals(4, c1.getPrice()[1]);

        Assert.assertEquals(5, c3.getPrice()[0]);
        Assert.assertEquals(5, c3.getPrice()[1]);

        Dice d1 = new Dice();
        d1.solarDiceInit();
        Dice d2 = new Dice();
        d2.lunarDiceInit();
        SimpleBot b = new SimpleBot(d1, d2, "Bot1","\033[0m");

        //carte avec effet
        DivineFavorCard c4 = new DivineFavorCard(AbstractCard.Name.LES_SABOTS_D_ARGENT, AbstractCard.Type.RECURRENT, 2, 0, 2);
        c4.getEffect(b);
        Assert.assertTrue(b.getBotScore().getGold() == 1 || b.getBotScore().getSolar() == 1 || b.getBotScore().getVictory() == 2 || b.getBotScore().getLunar() == 1);


    }
}


