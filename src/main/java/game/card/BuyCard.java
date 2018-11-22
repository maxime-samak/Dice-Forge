package game.card;

import game.BotScore;
import game.ScoreCounter;

/**
 *
 * Classe BuyCard permet l'achat d'objet Carte.
 *
 */
public class BuyCard {

    /**
     * Cette méthode fait payer le cout de la carte au bot. Elle ajoute également les points de victoire, éxécute l'effet de la carte, supprime la carte de l'ile.
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
