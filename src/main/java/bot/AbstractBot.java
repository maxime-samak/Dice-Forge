package bot;

import engine.DiceRoll;
import objects.Dice;

public abstract class AbstractBot implements Bot {

    private Dice dice1;
    private Dice dice2;
    private BotScore botscore;

    public AbstractBot(Dice d1, Dice d2) {
        this.dice1=d1;
        this.dice2=d2;
        this.botscore = new BotScore();
    }

    public BotScore getBotscore()
    {
        return botscore;
    }

    public String rollDices()
    {
        return DiceRoll.roll(dice1,dice2);
    }

}