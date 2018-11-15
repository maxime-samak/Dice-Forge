package game;

import game.card.AbstractCard;

import java.util.ArrayList;

public class Inventory {


    private ArrayList<AbstractCard> cardAssignement;

    public Inventory(ArrayList<AbstractCard> cardAssignement) {
        this.cardAssignement = cardAssignement;
    }

    public ArrayList<AbstractCard> getCardAssignement() {
        return cardAssignement;
    }

    /**
     * protected pour pas que le bot puisse tricher.
     * @param cardAssignement est une liste des cartes Engrenage détenu par un joueur.
     * @param card est une carte avec un type d'effet Engrenage(effet à chaque tour jusqu'à la fin).
     */
    protected void setCardAssignement(ArrayList<AbstractCard> cardAssignement, AbstractCard card) {
        cardAssignement.add(card);
    }
}
