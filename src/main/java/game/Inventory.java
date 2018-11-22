package game;

import game.card.Card;

import java.util.ArrayList;

/**
 *
 */
public class Inventory {


    private ArrayList<Card> cardAssignement;

    public Inventory(ArrayList<Card> cardAssignement) {
        this.cardAssignement = cardAssignement;
    }

    public ArrayList<Card> getCardAssignement() {
        return cardAssignement;
    }

    /**
     * protected pour pas que le bot puisse tricher.
     * @param cardAssignement est une liste des cartes Engrenage détenu par un joueur.
     * @param card est une carte avec un type d'effet Engrenage(effet à chaque tour jusqu'à la fin).
     */
    protected void setCardAssignement(ArrayList<Card> cardAssignement, Card card) {
        cardAssignement.add(card);
    }
}
