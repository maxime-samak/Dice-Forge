package game.card;

import bot.SimpleBot;
import game.dice.Dice;
import game.dice.Resource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ForgeCardTest {

    private SimpleBot bot1;
    private Dice d1;
    private Dice d2;
    private ForgeCard card;

    @Before
    public void setup() {
        d1 = new Dice();
        d1.solarDiceInit();
        d2 = new Dice();
        d2.lunarDiceInit();
        bot1 = new SimpleBot(d2,d2,"bot1","\033[0m");
    }

    @Test
    public void getEffect() {
        card = new ForgeCard(AbstractCard.Name.LE_MIROIR_ABYSSAL, AbstractCard.Type.INSTANT, 10, 0, 0);
        Assert.assertEquals("Face spéciale QUESTION ajoutée", card.getEffect(bot1));
        Assert.assertTrue(
                Resource.QUESTION.resourceName().equals(d2.getFi(1).getResource()) ||
                        Resource.QUESTION.resourceName().equals(d2.getFi(2).getResource()) ||
                        Resource.QUESTION.resourceName().equals(d2.getFi(3).getResource()) ||
                        Resource.QUESTION.resourceName().equals(d2.getFi(4).getResource()) ||
                        Resource.QUESTION.resourceName().equals(d2.getFi(5).getResource()) ||
                        Resource.QUESTION.resourceName().equals(d2.getFi(6).getResource()) );

        card = new ForgeCard(AbstractCard.Name.LE_CASQUE_D_INVISIBILITE, AbstractCard.Type.INSTANT, 4, 0, 0);
        Assert.assertEquals("Face spéciale X3 ajoutée", card.getEffect(bot1));
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
