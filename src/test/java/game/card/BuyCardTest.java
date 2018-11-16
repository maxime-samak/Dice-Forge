package game.card;

import bot.SimpleBot;
import game.BotScore;
import game.ScoreCounter;
import game.dice.Dice;
import org.junit.Assert;
import org.junit.Test;

import static game.card.BuyCard.buyCard;

public class BuyCardTest {

    @Test
    public void BuyCardTest() {
        Dice d1 = new Dice();
        d1.solarDiceInit();
        Dice d2 = new Dice();
        d2.lunarDiceInit();

        Islands islands = new Islands(2);
        BotScore bs1 = new BotScore();

        Card card = Card.LE_PASSEUR;
        ScoreCounter.updateScore(bs1, "0@SOLAR%4@LUNAR");
        Assert.assertEquals(true, buyCard(islands, card, bs1));
        Assert.assertEquals(0, bs1.getSolar());
        Assert.assertEquals(12, bs1.getVictory());

        Assert.assertEquals(false, buyCard(islands, card, bs1));
    }
}
