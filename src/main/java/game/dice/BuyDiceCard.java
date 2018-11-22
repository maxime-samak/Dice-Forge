package game.dice;

import game.BotScore;
import game.ScoreCounter;
import java.util.ArrayList;

/**
 * Classe BuyDiceCard sert à acheter des faces de dés.
 */
public class BuyDiceCard {

    private static ArrayList<DiceCard> bought = new ArrayList<>();

    /**
     * La méthode vérifie dans la pool passée en paramètre si la face que le bot veut acheter est disponible,
     * c'est à dire si il possède assez d'argent et si il n'a pas acheter de face équivalente pendant ce tour.
     * Si les vérification sont fausse la carte n'est pas acheter et la méthode renvoie false.
     * Si les vérifications passent alors la face est achettée, le gold retiré et le dé choisi changé, la méthode renvoie true.
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
        for(DiceCard i : bought) {
            if(i.equals(card)){
                return false;
            }
        }

        if(botscore.getGold() < pool) {
            return false;
        }

        else if(sanctuary.removeCard(pool, card)){
            dice.setDiceCard(cardToChange, card);
            bought.add(card);
            ScoreCounter.payGold(botscore,pool);
            return true;
        }
        return false;
    }

    /**
     *
     */
    public static void resetBotLog() {
        bought = new ArrayList<>();
    }

}