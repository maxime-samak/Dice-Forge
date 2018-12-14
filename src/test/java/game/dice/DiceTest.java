package game.dice;

import org.junit.Assert;
import org.junit.Test;

import static game.dice.Resource.GOLD;
import static game.dice.Resource.SOLAR;

public class DiceTest {

    @Test
    public void setDiceCard() {
        Dice dice1 = new Dice();
        dice1.solarDiceInit();

        DiceCard card = new DiceCard(404, SOLAR);

        dice1.setDiceCard(1, card);
        Assert.assertEquals(card, dice1.getFi(1));
    }

    @Test
    public void getFi() {
        Dice dice1 = new Dice();

        DiceCard f1 = new DiceCard(1, SOLAR);
        DiceCard f2 = new DiceCard(1, GOLD);
        DiceCard f3 = new DiceCard(1, GOLD);
        DiceCard f4 = new DiceCard(1, GOLD);
        DiceCard f5 = new DiceCard(1, GOLD);
        DiceCard f6 = new DiceCard(1, GOLD);

        dice1.setDiceCard(1, f1);
        dice1.setDiceCard(2, f2);
        dice1.setDiceCard(3, f3);
        dice1.setDiceCard(4, f4);
        dice1.setDiceCard(5, f5);
        dice1.setDiceCard(6, f6);

        Assert.assertEquals(f1,dice1.getFi(1));
        Assert.assertEquals(f2,dice1.getFi(2));
        Assert.assertEquals(f3,dice1.getFi(3));
        Assert.assertEquals(f4,dice1.getFi(4));
        Assert.assertEquals(f5,dice1.getFi(5));
        Assert.assertEquals(f6,dice1.getFi(6));
    }

    @Test
    public void solarDiceInit() {
        Dice dice1 = new Dice();
        dice1.solarDiceInit();

        Assert.assertEquals(1,dice1.getFi(1).getValue());
        Assert.assertEquals(1,dice1.getFi(2).getValue());
        Assert.assertEquals(1,dice1.getFi(3).getValue());
        Assert.assertEquals(1,dice1.getFi(4).getValue());
        Assert.assertEquals(1,dice1.getFi(5).getValue());
        Assert.assertEquals(1,dice1.getFi(6).getValue());

        Assert.assertEquals(Resource.SOLAR.resourceName(),dice1.getFi(1).getResource());
        Assert.assertEquals(Resource.GOLD.resourceName(),dice1.getFi(2).getResource());
        Assert.assertEquals(Resource.GOLD.resourceName(),dice1.getFi(3).getResource());
        Assert.assertEquals(Resource.GOLD.resourceName(),dice1.getFi(4).getResource());
        Assert.assertEquals(Resource.GOLD.resourceName(),dice1.getFi(5).getResource());
        Assert.assertEquals(Resource.GOLD.resourceName(),dice1.getFi(6).getResource());
    }

    @Test
    public void lunarDiceInit() {
        Dice dice2 = new Dice();
        dice2.lunarDiceInit();

        Assert.assertEquals(1,dice2.getFi(1).getValue());
        Assert.assertEquals(2,dice2.getFi(2).getValue());
        Assert.assertEquals(1,dice2.getFi(3).getValue());
        Assert.assertEquals(1,dice2.getFi(4).getValue());
        Assert.assertEquals(1,dice2.getFi(5).getValue());
        Assert.assertEquals(1,dice2.getFi(6).getValue());

        Assert.assertEquals(Resource.LUNAR.resourceName(),dice2.getFi(1).getResource());
        Assert.assertEquals(Resource.VICTORY.resourceName(),dice2.getFi(2).getResource());
        Assert.assertEquals(Resource.GOLD.resourceName(),dice2.getFi(3).getResource());
        Assert.assertEquals(Resource.GOLD.resourceName(),dice2.getFi(4).getResource());
        Assert.assertEquals(Resource.GOLD.resourceName(),dice2.getFi(5).getResource());
        Assert.assertEquals(Resource.GOLD.resourceName(),dice2.getFi(6).getResource());
    }

}