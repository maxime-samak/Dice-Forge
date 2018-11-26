package game.card;

import bot.AbstractBot;
import game.BotScore;
import game.ScoreCounter;
import game.dice.Resource;

import java.util.ArrayList;

/**
 *
 * Classe BuyCard permet l'achat d'objet Carte.
 *
 */
public class BuyCard {

    private static ArrayList<Card> boughtArray = new ArrayList<>();
    private static Boolean feePayed;

    public static ArrayList<Card> getBoughtArray() {
        return boughtArray;
    }

    public static Boolean getFeePayed() {
        return feePayed;
    }

    /**
     * Cette méthode fait payer le cout de la carte au bot. Elle ajoute également les points de victoire, éxécute l'effet de la carte, supprime la carte de l'ile.
     * @param islands
     * @param card
     * @param botScore
     * @return
     */
    public static boolean buyCard(Islands islands, Card card, BotScore botScore, boolean buyed, AbstractBot bot){
        if(card.getPrice()[0] <= botScore.getSolar() && card.getPrice()[1] <= botScore.getLunar()) {
            for (Card i : islands.getIslands().get(Math.max(card.getPrice()[0],card.getPrice()[1]))) {
                if(i != null && i.equals(card)) {
                    if(buyed)
                    {
                        ScoreCounter.paySolar(botScore, card.getPrice()[0]+2);
                        feePayed=true;
                    }
                    else
                    {
                        ScoreCounter.paySolar(botScore, card.getPrice()[0]);
                    }
                    ScoreCounter.payLunar(botScore, card.getPrice()[1]);
                    ScoreCounter.addResource(botScore, Resource.VICTORY, card.getVictory());
                    boughtArray.add(card);
                    islands.removeCard(card);
                    if(card.isTypeReinforcement()){
                        CardAssignement.setCardAssignement(bot, card);
                    }
                    return true;
                }
            }
        }

        return false;
    }

    public static void resetBotLog() {
        boughtArray = new ArrayList<>();
        feePayed=false;
    }
}
