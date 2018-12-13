package game.dice;

import game.BotScore;
import game.ScoreCounter;
import game.card.BuyCard;
import java.util.ArrayList;

/**
 * Classe BuyDiceCard sert à acheter des faces de dés.
 */
public class BuyDiceCard {

    private static ArrayList<DiceCard> bought = new ArrayList<>();
    private static ArrayList<DiceCard> replaced = new ArrayList<>();
    private static ArrayList<Integer> prices = new ArrayList<>();
    private static Boolean first=true;

    public static ArrayList<DiceCard> getBought() {
        return bought;
    }

    public static ArrayList<DiceCard> getReplaced() { return replaced; }

    public static ArrayList<Integer> getPrices() {
        return prices;
    }

    public static Boolean getFirst() { return first; }

    public static void changeFirst() {
        first = false;
    }

    /**
     * La méthode vérifie dans la pool passée en paramètre si la face que le bot veut acheter est disponible,
     * c'est à dire si il possède assez d'argent et si il n'a pas acheter de face équivalente pendant ce tour.
     * Si les vérification sont fausse la carte n'est pas acheter et la méthode renvoie false.
     * Si les vérifications passent alors la face est achetée, le gold retiré et le dé choisi changé, la méthode renvoie true.
     * La méthode vérifie si le bot n'a pas d'abord effectué son maximum d'action possible
     *
     * @param sanctuary
     * @param pool pool où se trouve la face à acheter
     * @param card nouvelle face à acheter
     * @param dice dé où la face à changer se trouve
     * @param cardToChange emplacement de la face à changer sur le dé
     * @param botscore inventaire du bot
     * @return
     */
    public static boolean setCard(Sanctuary sanctuary, int pool, DiceCard card, Dice dice, int cardToChange, BotScore botscore) {
        if(botscore.getGold() < pool || BuyCard.getBought().size()>=2) {
            return false;
        }

        for(DiceCard i : bought) {
            if(i.equals(card)){
                return false;
            }
        }

        if(BuyCard.getBought().size() > 0) {

            if (bought.size() == 0) {
                if (!(botscore.getSolar() >= 2)) {
                    return false;
                }

                else if(sanctuary.removeCard(pool, card)) {
                    ScoreCounter.paySolar(botscore,2);
                    replaced.add(dice.getFi(cardToChange));
                    dice.setDiceCard(cardToChange, card);
                    bought.add(card);
                    prices.add(pool);
                    ScoreCounter.payGold(botscore,pool);
                    return true;
                }
            }
        }

        if (sanctuary.removeCard(pool, card)){
            replaced.add(dice.getFi(cardToChange));
            dice.setDiceCard(cardToChange, card);
            bought.add(card);
            prices.add(pool);
            ScoreCounter.payGold(botscore,pool);
            return true;
        }
        return false;
    }

    public static boolean setSpecial(Dice dice, int face, DiceCard newcard) {
        if (!newcard.equals(new DiceCard(0,Resource.X3)) && !newcard.equals(new DiceCard(0,Resource.QUESTION))) {
            return false;
        }
        else {
            dice.setDiceCard(face, newcard);
            return true;
        }
    }

    /**
     * Réinitialise l'historique d'achat des bots en créant une nouvelle ArrayList vierge qui remplace l'ancien.
     */
    public static void resetBotLog() {
        bought = new ArrayList<>();
        prices = new ArrayList<>();
        first=true;
    }

    /**
     * Modifie la face de dé souhaité, et la remplace par card.
     * @param diceCard
     * @param dice
     * @param cardToChange
     */
    public static void setCard(DiceCard diceCard, Dice dice, int cardToChange){
        dice.setDiceCard(cardToChange, diceCard);
    }

}