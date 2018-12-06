package game.card.newcard;

import bot.SimpleBot;
import game.dice.Dice;
import game.dice.Resource;
import org.junit.Test;
import game.card.newcard.ResourceCard;
import game.card.newcard.AbstractCard;
import game.*;
import org.junit.*;


public class ResourceCardTest {
    @Test
    public void ResourceCardTest() {
        //Initialisation du SimpleBot
        Dice d1 = new Dice();
        d1.solarDiceInit();
        Dice d2 = new Dice();
        d2.lunarDiceInit();
        SimpleBot bot1 = new SimpleBot(d1,d2,"bot1");

        //Ajout de ressources au SimpleBot
        ScoreCounter.addResource(bot1.getBotScore(), Resource.GOLD,3);

        //Creation de la ressource
        ResourceCard r1 = new ResourceCard(AbstractCard.Name.LES_AILES_DE_LA_GARDIENNES, AbstractCard.Type.INSTANT, 2, 3, 4);

        //Application de l'effet de la carte au bot
        r1.getEffect(bot1);

        //Test
        Assert.assertEquals("Gold : 3, Solar : 1, Lunar : 0, Victory : 0", bot1.getBotScore().getInfos());
        Assert.assertEquals("LES_AILES_DE_LA_GARDIENNES", r1.getName().toString());
        Assert.assertEquals("INSTANT", r1.getType().toString());


    }
}
