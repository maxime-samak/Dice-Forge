package bot;

import engine.BotScore;
import engine.DiceRoll;
import objects.Dice;
import objects.DiceCard;

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

    public Dice getDice1() {
        return dice1;
    }

    public Dice getDice2() {
        return dice2;
    }

    public String rollDices()
    {
        return DiceRoll.roll(dice1,dice2);
    }

    public DiceCard getFace(int dice,int face)
    {
        if (dice==1)
            return dice1.getFi(face);
        return dice2.getFi(face);
    }

    public void play(){}

}