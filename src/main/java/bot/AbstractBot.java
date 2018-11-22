package bot;

import game.BotScore;
import game.DiceRoll;
import game.dice.Dice;
import game.dice.Sanctuary;

/**
 * Classe abstraite AbstractBot implément l'interface Bot
 */
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

    public BotScore getBotScore() {
        return botscore;
    }

    public String getBotID() { return botID; }

    public Dice getDice1() {
        return dice1;
    }

    public Dice getDice2() {
        return dice2;
    }

    public void play(Sanctuary sanctuary){}

}