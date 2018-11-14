package game.card;

import game.BotScore;

public class BuyCard {

    public static boolean buyCard(Islands islands, NeutralCard card, BotScore botScore){
        if(card.getPrice()[0] <= botScore.getSolar() && card.getPrice()[1] <= botScore.getLunar()) {
            for (AbstractCard i : islands.getIslands().get(Math.max(card.getPrice()[0],card.getPrice()[1]))) {
                if(i != null && i.equals(card)) {
                    return true;
                }
            }
        }

        return false;
    }

}
