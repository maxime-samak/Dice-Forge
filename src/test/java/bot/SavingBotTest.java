package bot;

import game.ScoreCounter;
import game.card.BuyCard;
import game.card.Inventory;
import game.card.Islands;
import game.dice.*;
import org.junit.Assert;
import org.junit.Test;

public class SavingBotTest {

    @Test
    public void SavingBotTest() {

        BuyDiceCard.resetBotLog();
        BuyCard.resetBotLog();

        Dice d1 = new Dice();
        d1.solarDiceInit();
        Dice d2 = new Dice();
        d2.lunarDiceInit();

        SavingBot bot1 = new SavingBot(d1, d2, "bot1", "\033[0m");

        Assert.assertNotNull(bot1);
        Assert.assertEquals(d1, bot1.getDice1());
        Assert.assertEquals(d2, bot1.getDice2());
        Assert.assertEquals("bot1", bot1.getBotID());

        Sanctuary sanctuary = new Sanctuary(2);

        //Budget d'achat = 0, donc il ne peut rien acheter.
        Assert.assertFalse(bot1.diceShopping(sanctuary, 12));
        Assert.assertFalse(bot1.diceShopping(sanctuary, 8));
        Assert.assertFalse(bot1.diceShopping(sanctuary, 6));
        Assert.assertFalse(bot1.diceShopping(sanctuary, 5));
        Assert.assertFalse(bot1.diceShopping(sanctuary, 4));
        Assert.assertFalse(bot1.diceShopping(sanctuary, 3));
        Assert.assertFalse(bot1.diceShopping(sanctuary, 2));

        Islands islands = new Islands(2);
        Inventory inventory = new Inventory(new AbstractBot[]{bot1});

        //Test méthode play == buyInOrder
        //Cas 2 : faces achetées > 0 ou carte achetées == 0
            //Cas 2.1 : Gold > Autres ressources donc achète une face.
        //Budget d'achat = 12 Gold.
        ScoreCounter.updateScore(bot1.getBotScore(), new DiceCard[]{new DiceCard(6, Resource.GOLD), new DiceCard(6, Resource.GOLD)});
        bot1.play(sanctuary, islands, inventory); //achète une face et essaye d'acheer une carte sans succés.
        Assert.assertEquals(1, BuyDiceCard.getBought().size());
        Assert.assertEquals(0, BuyCard.getBought().size());
            //Cas 2.2 : Gold <= Autres ressources
        BuyDiceCard.resetBotLog();
        ScoreCounter.updateScore(bot1.getBotScore(), new DiceCard[]{new DiceCard(6, Resource.LUNAR), new DiceCard(6, Resource.LUNAR)});
        ScoreCounter.updateScore(bot1.getBotScore(), new DiceCard[]{new DiceCard(6, Resource.SOLAR), new DiceCard(6, Resource.SOLAR)});
        bot1.play(sanctuary, islands, inventory); //achète une carte et rejoue
        Assert.assertNotEquals(0, BuyCard.getBought().size());

        //Cas 1 : faces achetées == 0 et carte achetées > 0
            //Cas 1.1 : Gold > Autres ressources
        BuyDiceCard.resetBotLog();
        int tmp = BuyCard.getBought().size();
        ScoreCounter.updateScore(bot1.getBotScore(), new DiceCard[]{new DiceCard(6, Resource.GOLD), new DiceCard(6, Resource.GOLD)});
        bot1.play(sanctuary, islands, inventory); //achète une face
        Assert.assertEquals(1, BuyDiceCard.getBought().size());
        Assert.assertEquals(tmp, BuyCard.getBought().size());//garde l'ancienne valeur.

        //Cas 1.2 : Gold <= Autres ressources
    }
}

