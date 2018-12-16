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

    private static ArrayList<AbstractCard> bought = new ArrayList<>();
    private static ArrayList<Object> effects = new ArrayList<>();
    private static ScoreCounter scoreCounter = new ScoreCounter();

    public static ArrayList<AbstractCard> getBought() {
        return bought;
    }

    public static ArrayList<Object> getEffects() {
        return effects;
    }

    /**
     * Cette méthode fait payer le cout de la carte au bot.
     * Elle ajoute également les points de victoire, éxécute l'effet de la carte, supprime la carte de l'ile.
     * La méthode vérifie que le Bot n'a pas effectué le maximum d'actions pour ce tour.
     * @param bot
     * @param card
     * @param islands
     * @return
     */
    public static boolean buyCard(AbstractBot bot, Islands islands, AbstractCard card,Inventory inventory) {
        if(bought.size()>=2 || (bought.size()==1 && BuyDiceCard.getBought().size()>=1))
        {
            return false;
        }

        BotScore score = bot.getBotScore();
        if (card.getPrice()[0] <= score.getSolar() && card.getPrice()[1] <= score.getLunar()) {

            for (AbstractCard c : islands.getIslands().get(card.getPrice()[0] + card.getPrice()[1])) {
                if (c != null && c.equals(c)) {

                    if ((BuyDiceCard.getBought().size() > 0 || bought.size() > 0) && bot.getBotScore().getSolar() >= (c.getPrice()[0] + 2) && bot.getBotScore().getLunar()>=c.getPrice()[1]) {
                        scoreCounter.paySolar(score, c.getPrice()[0] + 2);
                        scoreCounter.payLunar(score, c.getPrice()[1]);
                        scoreCounter.addResource(score, Resource.VICTORY, c.getVictory());
                        bought.add(c);
                        inventory.affectCard(bot,c);
                        islands.removeCard(c);

                        if (c.getType().equals(AbstractCard.Type.INSTANT)) {
                            Object effect = c.getEffect(bot);
                            effects.add(effect);
                        }
                        else
                            effects.add(null);

                        return true;
                    }
                    else if ((BuyDiceCard.getBought().size() <= 0 && bought.size() <= 0)&& bot.getBotScore().getSolar() >= (c.getPrice()[0] + 2) && bot.getBotScore().getLunar()>=c.getPrice()[1]) {

                        scoreCounter.paySolar(score, c.getPrice()[0]);
                        scoreCounter.payLunar(score, c.getPrice()[1]);
                        scoreCounter.addResource(score, Resource.VICTORY, c.getVictory());
                        bought.add(c);
                        inventory.affectCard(bot,c);
                        islands.removeCard(c);

                        if (c.getType().equals(AbstractCard.Type.INSTANT)) {
                            Object effect = c.getEffect(bot);
                            effects.add(effect);
                        }
                        else
                            effects.add(null);
                        BuyDiceCard.changeFirst();

                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static void resetBotLog() {
        bought = new ArrayList<>();
        effects= new ArrayList<>();
    }
}