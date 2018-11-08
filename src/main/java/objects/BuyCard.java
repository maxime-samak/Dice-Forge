package objects;

import java.util.ArrayList;

public class BuyCard {
    private static ArrayList<DiceCard> bought = new ArrayList<>();

    public static boolean setCard(Sanctuary sanctuary, int pool, DiceCard card, Dice dice, int cardToChange) {
        for(DiceCard i : bought) {
            if(i.equals(card)){
                return false;
            }
        }
        sanctuary.removeCard(pool, card);
        dice.setDiceCard(cardToChange, card);
        bought.add(card);



        return true;
    }

    public static void resetBotLog() {
        bought = new ArrayList<>();
    }

}