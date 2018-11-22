package game.card;

import game.BotScore;
import game.ScoreCounter;
import game.dice.Resource;

public class BuyCard {

    /**
     *
     * @param islands
     * @param card
     * @param botScore
     * @return
     */
    public static boolean buyCard(Islands islands, Card card, BotScore botScore,boolean buyed){
        if(card.getPrice()[0] <= botScore.getSolar() && card.getPrice()[1] <= botScore.getLunar()) {
            for (Card i : islands.getIslands().get(Math.max(card.getPrice()[0],card.getPrice()[1]))) {
                if(i != null && i.equals(card)) {
                    if(buyed)
                    {
                        ScoreCounter.paySolar(botScore, card.getPrice()[0]+2);
                    }
                    else
                    {
                        ScoreCounter.paySolar(botScore, card.getPrice()[0]);
                    }
                    ScoreCounter.payLunar(botScore, card.getPrice()[1]);
                    ScoreCounter.addResource(botScore, Resource.VICTORY, card.getVictory());
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
