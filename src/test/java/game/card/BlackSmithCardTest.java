package game.card;

import bot.SimpleBot;
import game.ScoreCounter;
import game.dice.Dice;
import game.dice.DiceCard;
import game.dice.Resource;
import org.junit.Assert;
import org.junit.Test;

public class BlackSmithCardTest {
    @Test
    public void BlackSmithCardTest() {
        //Initialisation du SimpleBot
        Dice d1 = new Dice();
        d1.solarDiceInit();
        Dice d2 = new Dice();
        d2.lunarDiceInit();
        SimpleBot bot1 = new SimpleBot(d1,d2,"bot1","\033[0m");

        BlackSmithCard c1 = new BlackSmithCard(AbstractCard.Name.LE_COFFRE_DU_FORGERON, AbstractCard.Type.INSTANT, 2, 0, 1);
        c1.getEffect(bot1); //+4 slot Gold(12 de base), +2 slot Lunar(6 de base), slot +2 Solar(6 de base)
        ScoreCounter.updateScore(bot1.getBotScore(), new DiceCard[]{new DiceCard(20, Resource.GOLD), new DiceCard(20, Resource.LUNAR)});
        ScoreCounter.updateScore(bot1.getBotScore(), new DiceCard[]{new DiceCard(20, Resource.SOLAR), new DiceCard(20, Resource.LUNAR)});
        Assert.assertTrue(bot1.getBotScore().getGold() == 12+4 && bot1.getBotScore().getLunar() == 6+2 && bot1.getBotScore().getSolar() == 6+2);

        c1.getEffect(bot1); //12+4+4 6+2+2 6+2+2
        ScoreCounter.updateScore(bot1.getBotScore(), new DiceCard[]{new DiceCard(1, Resource.LUNAR), new DiceCard(2, Resource.SOLAR)});
        Assert.assertTrue(bot1.getBotScore().getGold() == 12+4 && bot1.getBotScore().getLunar() == 6+2+1 && bot1.getBotScore().getSolar() == 6+2+2);
        ScoreCounter.updateScore(bot1.getBotScore(), new DiceCard[]{new DiceCard(20, Resource.GOLD), new DiceCard(20, Resource.LUNAR)});
        Assert.assertTrue(bot1.getBotScore().getGold() == 12+4+4 && bot1.getBotScore().getLunar() == 6+2+2 && bot1.getBotScore().getSolar() == 6+2+2);


        BlackSmithCard c2 = new BlackSmithCard(AbstractCard.Name.LE_MARTEAU_DU_FORGERON, AbstractCard.Type.INSTANT, 2, 0, 1);
        c2.getEffect(bot1); //effet non implémenté, il initialise juste la variable forge à 0.

        Assert.assertEquals("Inventaire étendu", c1.getEffect(bot1));
        Assert.assertEquals(null, c2.getEffect(bot1));


    }
}
