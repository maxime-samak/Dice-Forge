package game.card;

import bot.SimpleBot;
import game.ScoreCounter;
import game.dice.Dice;
import game.dice.DiceCard;
import game.dice.Resource;
import org.junit.Assert;
import org.junit.Test;

public class ForgeCardTest {
    @Test
    public void ForgeCardTest(){
        //Initialisation du SimpleBot
        Dice d1 = new Dice();
        d1.solarDiceInit();
        Dice d2 = new Dice();
        d2.lunarDiceInit();
        SimpleBot bot1 = new SimpleBot(d2,d2,"bot1","\033[0m");

        ForgeCard c1 = new ForgeCard(AbstractCard.Name.LE_MIROIR_ABYSSAL, AbstractCard.Type.INSTANT, 10, 0, 0);
        Assert.assertEquals("Face spéciale QUESTION ajoutée", c1.getEffect(bot1));
        Assert.assertTrue(
                Resource.QUESTION.resourceName().equals(d2.getFi(1).getResource()) ||
                Resource.QUESTION.resourceName().equals(d2.getFi(2).getResource()) ||
                Resource.QUESTION.resourceName().equals(d2.getFi(3).getResource()) ||
                Resource.QUESTION.resourceName().equals(d2.getFi(4).getResource()) ||
                Resource.QUESTION.resourceName().equals(d2.getFi(5).getResource()) ||
                Resource.QUESTION.resourceName().equals(d2.getFi(6).getResource()) );

        ForgeCard c2 = new ForgeCard(AbstractCard.Name.LE_CASQUE_D_INVISIBILITE, AbstractCard.Type.INSTANT, 4, 0, 0);
        Assert.assertEquals("Face spéciale X3 ajoutée", c2.getEffect(bot1));
        Assert.assertEquals(Resource.X3.resourceName(), d2.getFi(4).getResource());
        Assert.assertTrue(
                Resource.X3.resourceName().equals(d2.getFi(1).getResource()) ||
                Resource.X3.resourceName().equals(d2.getFi(2).getResource()) ||
                Resource.X3.resourceName().equals(d2.getFi(3).getResource()) ||
                Resource.X3.resourceName().equals(d2.getFi(4).getResource()) ||
                Resource.X3.resourceName().equals(d2.getFi(5).getResource()) ||
                Resource.X3.resourceName().equals(d2.getFi(6).getResource()) );
    }
}
