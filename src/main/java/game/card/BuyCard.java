package game.card;

import game.BotScore;
import game.ScoreCounter;

public class BuyCard {

    public static boolean buyCard(Islands islands, NeutralCard card, BotScore botScore, ScoreCounter scoreCounter){
        if(card.getPrice()[0] <= botScore.getSolar() && card.getPrice()[1] <= botScore.getLunar()) {
            for (AbstractCard i : islands.getIslands().get(Math.max(card.getPrice()[0],card.getPrice()[1]))) {
                if(i != null && i.equals(card)) {
                    scoreCounter.paySolar(botScore, card.getPrice()[0]);
                    scoreCounter.payLunar(botScore, card.getPrice()[1]);
                    scoreCounter.addVictory(botScore, card.getVictory());
                    return true;
                }
            }
        }

        return false;
    }

}
