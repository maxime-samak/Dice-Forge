package bot;

import game.ScoreCounter;
import game.card.BuyCard;
import game.dice.*;
import org.junit.Assert;
import org.junit.Test;

public class SimpleBotTest {

    @Test
    public void SimpleBotTest(){

        BuyDiceCard.resetBotLog();
        BuyCard.resetBotLog();

        Dice d1 = new Dice();
        d1.solarDiceInit();
        Dice d2 = new Dice();
        d2.lunarDiceInit();
        ScoreCounter score = new ScoreCounter();
        Sanctuary sanctuary = new Sanctuary(2);

        SimpleBot bot1 = new SimpleBot(d1,d2,"bot1","\033[0m");
        Assert.assertNotNull(bot1);

        //Budget d'achat = 0.
        Assert.assertFalse(bot1.diceShopping(sanctuary,12));
        Assert.assertFalse(bot1.diceShopping(sanctuary,8));
        Assert.assertFalse(bot1.diceShopping(sanctuary,6));
        Assert.assertFalse(bot1.diceShopping(sanctuary,5));
        Assert.assertFalse(bot1.diceShopping(sanctuary,4));
        Assert.assertFalse(bot1.diceShopping(sanctuary,3));
        Assert.assertFalse(bot1.diceShopping(sanctuary,2));


        score.updateScore(bot1, new DiceCard[]{new DiceCard(6, Resource.GOLD), new DiceCard(6, Resource.GOLD)}); //Budget d'achat = 12.

        Assert.assertTrue(bot1.diceShopping(sanctuary,8));
        Assert.assertTrue(bot1.diceShopping(sanctuary,2));
        Assert.assertTrue(bot1.diceShopping(sanctuary,2));

        score.updateScore(bot1, new DiceCard[]{new DiceCard(6, Resource.GOLD), new DiceCard(6, Resource.GOLD)}); //Budget d'achat = 12.

        Assert.assertTrue(bot1.diceShopping(sanctuary,6));
        Assert.assertTrue(bot1.diceShopping(sanctuary,3));
        Assert.assertTrue(bot1.diceShopping(sanctuary,3));


        BuyDiceCard.resetBotLog();
        sanctuary = new Sanctuary(2);
        score.updateScore(bot1, new DiceCard[]{new DiceCard(6, Resource.GOLD), new DiceCard(6, Resource.GOLD)}); //Budget d'achat = 12.

        //pool 3 = {A,A,B,B}
        Assert.assertTrue(bot1.diceShopping(sanctuary,3)); //Achat carte A
        Assert.assertTrue(bot1.diceShopping(sanctuary,3)); //Achat carte B
        Assert.assertFalse(bot1.diceShopping(sanctuary,3)); //Si achat -> doublon


        BuyDiceCard.resetBotLog();
        sanctuary = new Sanctuary(2);
        score.updateScore(bot1, new DiceCard[]{new DiceCard(6, Resource.GOLD), new DiceCard(6, Resource.GOLD)}); //Budget d'achat = 12.

        //pool 3 = {A,A,B,B}
        Assert.assertTrue(bot1.diceShopping(sanctuary,3)); //Achat carte A
        Assert.assertTrue(bot1.diceShopping(sanctuary,3)); //Achat carte B
        BuyDiceCard.resetBotLog(); //Reset de l'historique d'achat.
        Assert.assertTrue(bot1.diceShopping(sanctuary,3)); //Achat carte A ou B -> devrait être valide

    }
}