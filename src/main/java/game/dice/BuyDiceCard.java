package game.dice;

import game.BotScore;
import game.ScoreCounter;
import java.util.ArrayList;

public class BuyDiceCard {

    private static ArrayList<DiceCard> bought = new ArrayList<>();

    public static boolean setCard(Sanctuary sanctuary, int pool, DiceCard card, Dice dice, int cardToChange, BotScore botscore) {
        for(DiceCard i : bought) {
            if(i.equals(card)){
                return false;
            }
        }

        if(botscore.getGold() < pool) {
            return false;
        }

        else if(sanctuary.removeCard(pool, card)){
            dice.setDiceCard(cardToChange, card);
            bought.add(card);
            ScoreCounter.payGold(botscore,pool);
            return true;
        }
        return false;
    }

    public static void resetBotLog() {
        bought = new ArrayList<>();
    }

}