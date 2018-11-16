package game.card;

import game.BotScore;
import game.ScoreCounter;

public class BuyCard {

    /**
     *
     * @param islands
     * @param card
     * @param botScore
     * @return
     */
    public static boolean buyCard(Islands islands, Card card, BotScore botScore){
        if(card.getPrice()[0] <= botScore.getSolar() && card.getPrice()[1] <= botScore.getLunar()) {
            for (Card i : islands.getIslands().get(Math.max(card.getPrice()[0],card.getPrice()[1]))) {
                if(i != null && i.equals(card)) {
                    ScoreCounter.paySolar(botScore, card.getPrice()[0]);
                    ScoreCounter.payLunar(botScore, card.getPrice()[1]);
                    ScoreCounter.addVictory(botScore, card.getVictory());
                    islands.removeCard(card);
                    if(card.isTypeReinforcement()){

                    }
                    return true;
                }
            }
        }

        return false;
    }

}
