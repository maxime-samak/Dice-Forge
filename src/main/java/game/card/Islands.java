package game.card;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe Islands permet de créer le deck de cartes de bases
 */
public class Islands {

    private HashMap<Integer, Card[]> islands = new HashMap<>();
    private final int nbPLayers;

    /**
     * Constructeur de base
     * @param nbPLayers
     */
    public Islands(int nbPLayers) {
        this.initIslands(nbPLayers);
        this.nbPLayers = nbPLayers;

    }

    /**
     *  Remplis la table de hachage islands des cartes achetables.
     * @param nbPLayers
     */
    public void initIslands(int nbPLayers) {
        Card[] island1 = new Card[nbPLayers * 4];
        Card[] island2 = new Card[nbPLayers * 2];

        Card[] island4 = new Card[nbPLayers * 2];
        //Card[] island5 = new Card[nbPLayers * 2];

        Card[] island6 = new Card[nbPLayers * 2];


        Card[] island10 = new Card[nbPLayers];

        for(int i = 0; i < nbPLayers; i++) {
            island1[i] = Card.L_ANCIEN;
            island1[i + nbPLayers] = Card.LES_HERBES_FOLLES;
        }
        islands.put(1, island1);

        for(int i = 0; i < nbPLayers; i++) {
            island2[i] = Card.LES_SABOTS_D_ARGENT;
            island2[i + nbPLayers] = Card.LES_AILES_DE_LA_GARDIENNES;
        }
        islands.put(2, island2);

        for(int i = 0; i < nbPLayers; i++) {
            island4[i] = Card.LE_PASSEUR;

            island4[i + nbPLayers] = Card.LA_MEDUSE;
        }
        islands.put(4, island4);

        /*for(int i = 0; i < nbPLayers; i++) {
            island5[i] = Card.LE_CASQUE_D_INVISIBILITE;
            island5[i + nbPLayers] = Card.LE_MIROIR_ABYSSAL;
        }
        islands.put(5, island5);*/

        for(int i = 0; i < nbPLayers; i++) {
            island6[i] = Card.LA_PINCE;
            island6[i + nbPLayers] = Card.L_ENIGME;
        }
        islands.put(6, island6);

        for(int i = 0; i < nbPLayers; i++) {
            island10[i] = Card.L_HYDRE;
        }
        islands.put(10, island10);

    }

    public HashMap<Integer, Card[]> getIslands() {
        return islands;
    }

    public void removeCard(Card card){
        int key = Math.max(card.getPrice()[0], card.getPrice()[1]);
        if(card.getPrice()[0] + card.getPrice()[1] == 10){
            key = 10;
        }
        for(int i = 0; i < islands.get(key).length; i++){
            if (islands.get(key)[i] == card){
                islands.get(key)[i] = null;
                break;
            }
        }
    }

    public ArrayList<Card> getIslandAvailables(int i)
    {
        ArrayList<Card> buyables = new ArrayList<>();
        Card[] cards = islands.get(i);
        for(int j = 0; j < cards.length; j++) {
            if(cards[j] != null) {
                buyables.add(cards[j]);
            }
        }
        return buyables;
    }

}