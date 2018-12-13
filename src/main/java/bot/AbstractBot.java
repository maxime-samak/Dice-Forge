package bot;

import game.BotScore;
import game.card.BuyCard;
import game.card.Card;
import game.card.Inventory;
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
    private final String color;

    /**
     * Constructeur d'AbstractBot
     * @param d1 son premier dé
     * @param d2 son second dé
     * @param botID son identification
     * @param color sa couleur d'écriture
     */
    public AbstractBot(Dice d1, Dice d2, String botID,String color) {
        this.botID = botID;
        this.dice1 = d1;
        this.dice2 = d2;
        this.botscore = new BotScore();
        this.color=color;
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

    public String getColor() { return color; }

    /**
     * Défini la méthode obligatoire play à implémenter qui permet d'instancier un Sanctuaire et une Islande
     */
    public void play(Sanctuary sanctuary, Islands islands, Inventory invetory){}

    public abstract int choose(DiceCard d);
    //public abstract void play(Sanctuary sanctuary, Islands islands);

    public boolean tradeGold() {
        return false; //choix par défaut arbitraire
    }

    public Resource getPreferredResource() {
        return Resource.SOLAR; //choix par défaut arbitraire
    }

    public Dice getPreferredDice() {
        return dice1; //choix par défaut arbitraire
    }

    public int getPreferredFace() {
        return 4; //choix par défaut arbitraire
    }



}