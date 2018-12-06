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
        SimpleBot b = new SimpleBot(d1, d2, "Bot1");

        //carte avec effet
        DivineFavorCard c4 = new DivineFavorCard(AbstractCard.Name.LES_SABOTS_D_ARGENT, AbstractCard.Type.RECURRENT, 2, 0, 2);
        c4.getEffect(b); // le bot choisit le d1 donc 1 solar ou un gold.
        Assert.assertTrue(b.getBotScore().getGold() == 1 || b.getBotScore().getSolar() == 1);
        Assert.assertTrue(b.getBotScore().getGold() == 0 || b.getBotScore().getSolar() == 0);

        /*ResourceCard c5 = new ResourceCard(AbstractCard.Name.L_ANCIEN, AbstractCard.Type.RECURRENT, 0, 1, 0);

        ScoreCounter.updateScore(b.getBotScore(), new DiceCard[]{new DiceCard(2, Resource.GOLD), new DiceCard(1, Resource.GOLD)});
        System.out.println(b.getBotScore().getInfos());
        int tmp = b.getBotScore().getGold();
        c5.getEffect(b);
        Assert.assertEquals(4, b.getBotScore().getVictory());
        Assert.assertEquals(tmp - 3, b.getBotScore().getGold());*/

        ResourceCard c6 = new ResourceCard(AbstractCard.Name.LES_AILES_DE_LA_GARDIENNES, AbstractCard.Type.RECURRENT, 4, 2, 0);

        int tmp = b.getBotScore().getGold();
        int tmpS = b.getBotScore().getSolar();
        int tmpL = b.getBotScore().getLunar();
        c6.getEffect(b);

        Assert.assertTrue(b.getBotScore().getGold() == tmp + 1 || b.getBotScore().getSolar() == tmpS + 1 || b.getBotScore().getLunar() == tmpL + 1);

    }
}


