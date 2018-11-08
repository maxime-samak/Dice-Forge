package bot;

import engine.BotScore;
import engine.DiceRoll;
import objects.Dice;

public abstract class AbstractBot implements Bot {

    private final String botID;
    private final Dice dice1;
    private final Dice dice2;
    private final BotScore botscore;

    public AbstractBot(Dice d1, Dice d2, String botID) {
        this.botID = botID;
        this.dice1 = d1;
        this.dice2 = d2;
        this.botscore = new BotScore();
    }

    public BotScore getBotscore()
    {
        return botscore;
    }

    public String getBotID() { return botID; }

    public String rollDices()
    {
        return DiceRoll.roll(dice1,dice2);
    }

    public void play(){}

}