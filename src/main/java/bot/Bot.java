package bot;

import engine.DiceRoll;
import objects.Dice;

public abstract class Bot implements InterfaceBot {

    private Dice dice1;
    private Dice dice2;

    public Bot(Dice d1,Dice d2)
    {
        this.dice1=d1;
        this.dice2=d2;
    }

    public void rollDices()
    {
        DiceRoll.roll(dice1,dice2);
    }

}
