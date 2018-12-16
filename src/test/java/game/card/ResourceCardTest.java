package game.card;

import bot.SavingBot;
import bot.SimpleBot;
import game.dice.Dice;
import game.dice.DiceCard;
import game.dice.Resource;
import org.junit.Test;

import game.*;
import org.junit.*;


public class ResourceCardTest {

    private SimpleBot bot1;
    private Dice d1;
    private Dice d2;
    private ResourceCard  card;
    private ScoreCounter score;

    @Before
    public void setup() {
        d1 = new Dice();
        d1.solarDiceInit();
        d2 = new Dice();
        d2.lunarDiceInit();
        bot1 = new SimpleBot(d2,d2,"bot1","\033[0m");
        score = new ScoreCounter();
    }

    @Test
    public void ResourceCardTest() {

        //Creation de la ressource
        ResourceCard r1 = new ResourceCard(AbstractCard.Name.LES_AILES_DE_LA_GARDIENNES, AbstractCard.Type.INSTANT, 2, 3, 4);

        //Application de l'effet de la carte au bot
        r1.getEffect(bot1);

        //Test
        //Assert.assertEquals("Gold : 1, Solar : 0, Lunar : 0, Victory : 0", bot1.getBotScore().getInfos());
        Assert.assertEquals("LES_AILES_DE_LA_GARDIENNES", r1.getName().toString());
        Assert.assertEquals("INSTANT", r1.getType().toString());
    }

    @Test
    public void getEffect() {
        //Test effet carte L_ANCIEN
        card = new ResourceCard(AbstractCard.Name.L_ANCIEN, AbstractCard.Type.RECURRENT, 0, 1, 0);
        score.updateScore(bot1, new DiceCard[]{new DiceCard(2, Resource.GOLD), new DiceCard(1, Resource.GOLD)});
        int tmp = bot1.getBotScore().getGold();
        card.getEffect(bot1); //Offre rejetée car la stratégie d'un SimpleBot est de ne pas dépenser son or.
        Assert.assertEquals(0, bot1.getBotScore().getVictory());
        Assert.assertEquals(tmp, bot1.getBotScore().getGold());

        SavingBot savingBot = new SavingBot(d1, d2, "Bot2","\033[0m");
        score.updateScore(savingBot, new DiceCard[]{new DiceCard(5, Resource.GOLD), new DiceCard(5, Resource.GOLD)});
        card.getEffect(savingBot); //Offre acceptée car la stratégie d'un SavingBot au dessus ou égal à 10 d'or, c'est de les dépenser.
        Assert.assertEquals(4, savingBot.getBotScore().getVictory());
        Assert.assertEquals(7, savingBot.getBotScore().getGold());

        //Test effet carte LES_AILES_DE_LA_GARDIENNES
        card = new ResourceCard(AbstractCard.Name.LES_AILES_DE_LA_GARDIENNES, AbstractCard.Type.RECURRENT, 4, 2, 0);
        tmp = bot1.getBotScore().getGold();
        int tmpS = bot1.getBotScore().getSolar();
        int tmpL = bot1.getBotScore().getLunar();
        card.getEffect(bot1);
        Assert.assertTrue(bot1.getBotScore().getGold() == tmp + 1 || bot1.getBotScore().getSolar() == tmpS + 1 || bot1.getBotScore().getLunar() == tmpL + 1);

        //Test effet carte LES_HERBES_FOLLES
        card = new ResourceCard(AbstractCard.Name.LES_HERBES_FOLLES, AbstractCard.Type.INSTANT, 2, 1, 0);
        tmp = bot1.getBotScore().getGold();
        tmpL = bot1.getBotScore().getLunar();
        card.getEffect(bot1);
        Assert.assertTrue(bot1.getBotScore().getGold() == tmp + 3 &&  bot1.getBotScore().getLunar() == tmpL + 3);


    }
}
