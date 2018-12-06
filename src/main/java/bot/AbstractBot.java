package bot;

import game.BotScore;
import game.card.BuyCard;
import game.card.Card;
import game.card.Islands;
import game.dice.*;

import java.util.ArrayList;

/**
 * Classe abstraite AbstractBot implémente l'interface Bot
 */
public abstract class AbstractBot implements Bot {

    private final String botID;
    private final Dice dice1;
    private final Dice dice2;
    private final BotScore botscore;

    /**
     * Constructeur
     */
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

    /**
     * Défini la méthode obligatoire play à implémenter qui permet d'instancier un Sanctuaire et une Islande
     */
    public void play(Sanctuary sanctuary, Islands islands){}

    public abstract String strategyCard(Card card);

    public abstract DiceCard choose(DiceCard d);
    //public abstract void play(Sanctuary sanctuary, Islands islands);

    public boolean tradeGold() {
        return false; //choix par défaut arbitraire
    }

    public Resource getPreferedResource() {
        return Resource.SOLAR; //choix par défaut arbitraire
    }

    public Dice getPreferedDice() {
        return dice1; //choix par défaut arbitraire
    }

    public int getPreferedFace() {
        return 4; //choix par défaut arbitraire
    }



}