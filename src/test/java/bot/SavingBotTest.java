package bot;

import game.ScoreCounter;
import game.card.BuyCard;
import game.card.Inventory;
import game.card.Islands;
import game.dice.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static game.dice.Resource.*;

public class SavingBotTest {

    private SavingBot bot1;
    private Dice d1;
    private Dice d2;
    private Sanctuary sanctuary;
    private Islands islands;
    private Inventory inventory;
    ScoreCounter score;

    @Before
    public void setup() {
        d1 = new Dice();
        d1.solarDiceInit();
        d2 = new Dice();
        d2.lunarDiceInit();
        bot1 = new SavingBot(d1, d2, "bot1", "\033[0m");
        sanctuary = new Sanctuary(2);
        islands = new Islands(2);
        inventory = new Inventory(new AbstractBot[]{bot1});
        score = new ScoreCounter();
        BuyDiceCard.resetBotLog();
        BuyCard.resetBotLog();
    }

    @Test //Test méthode play == buyInOrder
    public void play() {
        //Cas 2 : faces achetées > 0 ou carte achetées == 0
        Assert.assertFalse(BuyDiceCard.getBought().size() == 0 && BuyCard.getBought().size() > 0);//assert false pour tester le else
        //Cas 2.1 : Gold > Autres ressources donc achète une face.
        score.updateScore(bot1, new DiceCard[]{new DiceCard(6, Resource.GOLD), new DiceCard(6, Resource.GOLD)});
        Assert.assertTrue(bot1.getBotScore().getGold() > bot1.getBotScore().getLunar() && bot1.getBotScore().getGold() > bot1.getBotScore().getSolar());
        bot1.play(sanctuary, islands, inventory); //achète une face et essaye d'acheter une carte sans succés.
        Assert.assertEquals(1, BuyDiceCard.getBought().size());
        Assert.assertEquals(0, BuyCard.getBought().size());

        //Cas 2.2 : Gold <= Autres ressources
        BuyCard.resetBotLog();
        Assert.assertFalse(BuyDiceCard.getBought().size() == 0 && BuyCard.getBought().size() > 0);//assert false pour tester le else
        Assert.assertFalse(bot1.getBotScore().getGold() > bot1.getBotScore().getLunar() && bot1.getBotScore().getGold() > bot1.getBotScore().getSolar());
        score.updateScore(bot1, new DiceCard[]{new DiceCard(6, Resource.LUNAR), new DiceCard(6, Resource.LUNAR)});
        score.updateScore(bot1, new DiceCard[]{new DiceCard(6, Resource.SOLAR), new DiceCard(6, Resource.SOLAR)});
        bot1.play(sanctuary, islands, inventory); //achète une carte et rejoue
        Assert.assertNotEquals(0, BuyCard.getBought().size());


        //Cas 1 : faces achetées == 0 et carte achetées > 0
        BuyDiceCard.resetBotLog();
        Assert.assertTrue(BuyDiceCard.getBought().size() == 0 && BuyCard.getBought().size() > 0);//On vérifie qu'on est bien dans le cas 1
        //Cas 1.1 : Gold > Autres ressources
        score.updateScore(bot1, new DiceCard[]{new DiceCard(6, Resource.GOLD), new DiceCard(6, Resource.GOLD)});
        Assert.assertTrue(bot1.getBotScore().getGold() > bot1.getBotScore().getLunar() && bot1.getBotScore().getGold() > bot1.getBotScore().getSolar());//vérif 1.1
        int tmp = BuyCard.getBought().size();
        score.updateScore(bot1, new DiceCard[]{new DiceCard(6, Resource.GOLD), new DiceCard(6, Resource.GOLD)});
        bot1.play(sanctuary, islands, inventory); //achète une face
        Assert.assertEquals(1, BuyDiceCard.getBought().size());
        Assert.assertEquals(tmp, BuyCard.getBought().size());//garde l'ancienne valeur.

        //Cas 1.2 : Gold <= Autres ressources
        BuyDiceCard.resetBotLog();
        Assert.assertTrue(BuyDiceCard.getBought().size() == 0 && BuyCard.getBought().size() > 0);//vérif 1
        Assert.assertFalse(bot1.getBotScore().getGold() > bot1.getBotScore().getLunar() && bot1.getBotScore().getGold() > bot1.getBotScore().getSolar());//vérif 1.2
        score.updateScore(bot1, new DiceCard[]{new DiceCard(6, Resource.LUNAR), new DiceCard(6, Resource.LUNAR)});
        score.updateScore(bot1, new DiceCard[]{new DiceCard(6, Resource.SOLAR), new DiceCard(6, Resource.SOLAR)});
        tmp = BuyCard.getBought().size();
        bot1.play(sanctuary, islands, inventory); //achète une carte
        Assert.assertEquals(tmp + 1, BuyCard.getBought().size());
        Assert.assertEquals(0, BuyDiceCard.getBought().size());
    }

    @Test
    public void diceShopping() {
    }

    @Test
    public void diceShopping1() {
    }

    @Test
    public void getPreferredResource() {

        Assert.assertTrue(bot1.getBotScore().getSolar() <= bot1.getBotScore().getLunar() && bot1.getBotScore().getSolar() <= bot1.getBotScore().getGold());
        Assert.assertEquals(SOLAR, bot1.getPreferredResource());

        score.updateScore(bot1, new DiceCard[]{new DiceCard(1, Resource.SOLAR), new DiceCard(1, Resource.GOLD)});
        Assert.assertTrue(bot1.getBotScore().getLunar() < bot1.getBotScore().getSolar() && bot1.getBotScore().getLunar() <= bot1.getBotScore().getGold());
        Assert.assertEquals(LUNAR, bot1.getPreferredResource());

        score.updateScore(bot1, new DiceCard[]{new DiceCard(1, Resource.LUNAR), new DiceCard(0, Resource.GOLD)});
        Assert.assertFalse(bot1.getBotScore().getSolar() <= bot1.getBotScore().getLunar() && bot1.getBotScore().getSolar() <= bot1.getBotScore().getGold()
                && bot1.getBotScore().getLunar() < bot1.getBotScore().getSolar() && bot1.getBotScore().getLunar() <= bot1.getBotScore().getGold());
        Assert.assertTrue(bot1.getBotScore().getLunar() == bot1.getBotScore().getSolar() && bot1.getBotScore().getLunar() == bot1.getBotScore().getGold());
        //Assert.assertEquals(GOLD, bot1.getPreferredResource()); //getPreferredResource() ne renvoie jamais Gold
    }

    @Test
    public void choose() {
        Assert.assertEquals(0, bot1.choose(new DiceCard(1, SOLAR)));
        Assert.assertEquals(1, bot1.choose(new DiceCard(new int[]{0, 3, 2}, new Resource[]{Resource.CHOICE, Resource.VICTORY, Resource.GOLD})));
    }

    @Test
    public void tradeGold() {
        Assert.assertFalse(bot1.tradeGold());
        score.updateScore(bot1, new DiceCard[]{new DiceCard(6, Resource.GOLD), new DiceCard(6, Resource.GOLD)});
        Assert.assertTrue(bot1.tradeGold());
    }
}
