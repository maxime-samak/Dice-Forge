package game;

import bot.AbstractBot;
import game.dice.DiceCard;
import game.dice.Resource;

import java.util.ArrayList;

/**
 * Classe ScoreCounter : étant donné un lancé de dés ou un gain de ressources, cela actualise le botscore
 */
public class ScoreCounter {

    private static ArrayList<DiceCard> rolls;
    private static int nbPlayer;

    public ScoreCounter(){}

    public ScoreCounter(int nbPlayer, ArrayList<DiceCard> rolls){
        this.nbPlayer = nbPlayer;
        this.rolls = rolls;
    }
    /**
     * Etant donné la "fiche" de score d'un bot et un lancé de dé cette méthode actualise le score d'un bot.
     * @param bot Un bot.
     * @param roll Un lancé de dé (deux dés).
     */
    public void updateScore(AbstractBot bot, DiceCard[] roll) {

        BotScore botscore = bot.getBotScore();
        DiceCard rollD1 = roll[0];
        DiceCard rollD2 = roll[1];

        if (isX3(rollD1)) {
            if (isQuestion(rollD2)) {
                DiceCard card = doQuestion(bot);
                evalueRoll(botscore, new DiceCard (card.getValue() * 3, card.getResourceArray()[0]));
            }
            else {
                evalueRoll(botscore, rollD2);
                evalueRoll(botscore, rollD2);
                evalueRoll(botscore, rollD2);
            }
        }

        else if (isX3(rollD2)) {
            if (isQuestion(rollD1)) {
                DiceCard card = doQuestion(bot);
                evalueRoll(botscore, new DiceCard (card.getValue() * 3, card.getResourceArray()[0]));

            }
            else {
                evalueRoll(botscore, rollD1);
                evalueRoll(botscore, rollD1);
                evalueRoll(botscore, rollD1);
            }
        }

        else if (isQuestion(rollD1) && isQuestion(rollD2)) {
            evalueRoll(botscore,doQuestion(bot));
            evalueRoll(botscore,doQuestion(bot));
        }

        else if (isQuestion(rollD1 )) {
            evalueRoll(botscore,doQuestion(bot));
            evalueRoll(botscore, rollD2);
        }

        else if (isQuestion(rollD2)) {
            evalueRoll(botscore,doQuestion(bot));
            evalueRoll(botscore, rollD1);
        }
        else {
            evalueRoll(botscore,rollD1);
            evalueRoll(botscore, rollD2);
        }
    }

    public void updateScore(AbstractBot bot, DiceCard roll) {
        BotScore botscore = bot.getBotScore();
        evalueRoll(botscore,roll);
    }

    /**
     * Cette méthode permet de transformer les valeurs affichés sur une face de dé en ressources réelles dans le score d'un bot.
     * @param botscore Le score d'un bot.
     * @param roll Un lancé de dé (un dé).
     */
    private void evalueRoll(BotScore botscore, DiceCard roll) {
        int[] value = roll.getValueArray();
        Resource[] resource = roll.getResourceArray();

        if(resource.length == 1) {
            addResource(botscore, resource[0], value[0]);
        }

        else if(resource.length > 1) {
            if(resource[0] == Resource.CHOICE) {
                addResource(botscore, resource[1], value[1]);
            }

            else if(resource[0] == Resource.PLUS) {
                for(int i = 1; i < resource.length; i++) {
                    addResource(botscore, resource[i], value[i]);
                }
            }
        }
    }

    public boolean isX3(DiceCard roll) {
        if (roll.getResource().equals(Resource.X3.resourceName())) {
            return true;
        }
        return false;
    }

    public boolean isQuestion(DiceCard roll) {
        if (roll.getResource().equals(Resource.QUESTION.resourceName())) {
            return true;
        }
        return false;
    }

    public static int getNbPlayer() {
        return nbPlayer;
    }

    public DiceCard doQuestion(AbstractBot bot) {
        int maxvalue = 0;
        Resource res = bot.getPreferredResource();

        for (DiceCard c : rolls) {
            if (c.getResourceArray().length > 1) {
                if (maxvalue < c.getValueArray()[bot.choose(c)])
                    maxvalue = c.getValueArray()[bot.choose(c)];
            }
            else {
                if (maxvalue < c.getValue() && c.getResource().equals(res.resourceName())) {
                    maxvalue = c.getValue();
                }
            }
        }
        return new DiceCard(maxvalue, res);
    }


    /**
     * Ajoute au score d'unt bot le nombre de ressources passé en paramètre.
     * @param botscore Le score d'un bot.
     * @param resource Une ressource.
     * @param value Le montant de ressource à ajouter.
     */
    public void addResource(BotScore botscore, Resource resource, int value) {
        switch(resource) {
            case GOLD:
                botscore.addGold(value);
                break;
            case LUNAR:
                botscore.addLunar(value);
                break;
            case SOLAR:
                botscore.addSolar(value);
                break;
            case VICTORY:
                botscore.addVictory(value);
                break;
        }
    }

    public void addForge(BotScore botscore, int gold) {
        botscore.addForge(gold);
    }

    public void initForge(BotScore botscore) {
        botscore.initForge();
    }

    public void payGold(BotScore botscore, int price) { botscore.removeGold(price); }

    public void paySolar(BotScore botscore, int price) { botscore.removeSolar(price); }

    public void payLunar(BotScore botscore, int price) { botscore.removeLunar(price); }

    /**
     * Etend l'inventaire après l'achat de la carte "Le coffre du Forgeron"
     * @param botscore
     */
    public void extend(BotScore botscore) { botscore.extend(); }

    public static ArrayList<DiceCard> getRolls() {
        return rolls;
    }
}