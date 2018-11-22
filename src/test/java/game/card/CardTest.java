package game.card;

import bot.SimpleBot;
import game.BotScore;
import game.ScoreCounter;
import game.dice.Dice;
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

        Card c4 = Card.LES_SABOTS_D_ARGENT;
        System.out.println(c4);

        BotScore bs = new BotScore();
        ScoreCounter sc = new ScoreCounter();
        Dice d1 = new Dice();
        d1.solarDiceInit();
        Dice d2 = new Dice();
        d2.lunarDiceInit();
        SimpleBot b = new SimpleBot(d1, d2, "Bot1");
        //c4.doEffect(d1, bs, sc);

        Card c5 = Card.L_ANCIEN;
        sc.updateScore(bs, "3@GOLD%0@VICTORY");
        //c5.doEffect(d1, bs, sc); //bug à réparer

        Card c6 = Card.LES_AILES_DE_LA_GARDIENNES;
        c6.doEffect(d1, bs, sc, b);



    }
}


