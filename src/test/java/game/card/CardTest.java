package game.card;

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
        Card c1 = Card.LE_PASSEUR;//12 0 4 false
        Card c2 = Card.LA_MEDUSE;// 14 4 0 false
        Card c3 = Card.L_HYDRE;// 26 5 5 false

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
        SimpleBot b = new SimpleBot(d1, d2, "Bot1");

        //carte avec effet
        Card c4 = Card.LES_SABOTS_D_ARGENT;
        c4.doEffect(b); // le bot choisit le d1 donc 1 solar ou un gold.

        Assert.assertTrue(b.getBotScore().getGold() == 1 || b.getBotScore().getSolar() == 1);
        Assert.assertTrue(b.getBotScore().getGold() == 0 || b.getBotScore().getSolar() == 0);

        Card c5 = Card.L_ANCIEN; //il paye 3 gold et recoit 4 point de gloire.
        ScoreCounter.updateScore(b.getBotScore(), new DiceCard[]{new DiceCard(2, Resource.GOLD), new DiceCard(1, Resource.GOLD)});
        int tmp = b.getBotScore().getGold();
        c5.doEffect(b);
        Assert.assertEquals(4, b.getBotScore().getVictory());
        Assert.assertEquals(tmp - 3, b.getBotScore().getGold());

        Card c6 = Card.LES_AILES_DE_LA_GARDIENNES; // donne 1 gold et un lunar ou solar.
        tmp = b.getBotScore().getGold();
        int tmpS = b.getBotScore().getSolar();
        int tmpL = b.getBotScore().getLunar();
        c6.doEffect(b);
        Assert.assertEquals(tmp + 1, b.getBotScore().getGold());
        Assert.assertTrue(b.getBotScore().getSolar() == tmpS + 1 || b.getBotScore().getLunar() == tmpL + 1);

    }
}


