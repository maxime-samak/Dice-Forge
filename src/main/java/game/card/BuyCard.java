package game.card;

import bot.AbstractBot;
import game.BotScore;
import game.ScoreCounter;
import game.dice.BuyDiceCard;
import game.dice.Resource;

import java.util.ArrayList;

/**
 *
 * Classe BuyCard permet l'achat d'objet Carte.
 *
 */
public class BuyCard {

    private static ArrayList<Card> bought = new ArrayList<>();

    public static ArrayList<Card> getBought() {
        return bought;
    }

    /**
     * Cette méthode fait payer le cout de la carte au bot. Elle ajoute également les points de victoire, éxécute l'effet de la carte, supprime la carte de l'ile.
     * @param islands
     * @param card
     * @param botScore
     * @return
     */
    public static boolean buyCard(Islands islands, Card card, BotScore botScore, AbstractBot bot){
        if(card.getPrice()[0] <= botScore.getSolar() && card.getPrice()[1] <= botScore.getLunar()) {
            for (Card c : islands.getIslands().get(card.getPrice()[0] + card.getPrice()[1])) {
                if(c != null && c.equals(c)) {

                    if((BuyDiceCard.getBought().size() > 0 || bought.size() > 0) && botScore.getSolar() >= card.getPrice()[0] + 2) {
                        ScoreCounter.paySolar(botScore, c.getPrice()[0] + 2);
                        ScoreCounter.payLunar(botScore, c.getPrice()[1]);
                        ScoreCounter.addResource(botScore, Resource.VICTORY, c.getVictory());
                        BuyCard.bought.add(c);
                        islands.removeCard(c);

                        if(c.isTypeReinforcement()){
                            CardAssignement.setCardAssignement(bot, c);
                        }
                        if(c.isTypeInstant()){
                            c.doEffect(bot);
                        }

                        return true;
                    }

                    else if (!(BuyDiceCard.getBought().size() > 0 && bought.size() > 0)) {
                        ScoreCounter.paySolar(botScore, c.getPrice()[0]);
                        ScoreCounter.payLunar(botScore, c.getPrice()[1]);
                        ScoreCounter.addResource(botScore, Resource.VICTORY, c.getVictory());
                        BuyCard.bought.add(c);
                        islands.removeCard(c);

                        if(c.isTypeReinforcement()){
                            CardAssignement.setCardAssignement(bot, c);
                        }

                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static void resetBotLog() {
        bought = new ArrayList<>();
    }
}
