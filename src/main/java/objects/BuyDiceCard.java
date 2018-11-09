package objects;

import engine.BotScore;
import engine.ScoreCounter;

import java.util.ArrayList;

public class BuyDiceCard {
    private static ArrayList<DiceCard> bought = new ArrayList<>();

    public static boolean setCard(Sanctuary sanctuary, int pool, DiceCard card, Dice dice, int cardToChange, BotScore botscore) {
        for(DiceCard i : bought) {
            if(i.equals(card)){
                return false;
            }
        }
        sanctuary.removeCard(pool, card);
        dice.setDiceCard(cardToChange, card);
        bought.add(card);
        ScoreCounter.deduceGold(botscore,pool);



        return true;
    }

    public static void resetBotLog() {
        bought = new ArrayList<>();
    }

}