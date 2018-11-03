package bot;

import engine.DiceRoll;
import objects.Dice;
import objects.DiceCard;
import objects.Sanctuaire;

public abstract class AbstractBot implements Bot {
    private Sanctuaire sanctuaire;
    private String name;
    private Dice dice1;
    private Dice dice2;
    private BotScore botscore;

    public AbstractBot(Dice d1, Dice d2,String name) {
        this.sanctuaire = new Sanctuaire();
        this.dice1=d1;
        this.dice2=d2;
        this.botscore = new BotScore();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public BotScore getBotscore()
    {
        return botscore;
    }

    public String rollDices()
    {
        return DiceRoll.roll(dice1,dice2);
    }

    public void play() {



    }




}