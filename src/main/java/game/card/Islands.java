package game.card;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * La classe Islands permet de creer et de contenir toutes les cartes du jeu pour la partie en cour.
 */
public class Islands {

    private HashMap<Integer, AbstractCard[]> islands = new HashMap<>();

    /**
     * Constructeur de base qui applique la méthode d'initialisation des îles.
     *
     * @param nbPlayers le nombre de carte à créer est fonction du nommbre de joueurs.
     */
    public Islands(int nbPlayers) {
        this.initIslands(nbPlayers);
    }

    /**
     * Méthode d'initialisation des îles.
     *
     * @param nbPlayers
     */
    public void initIslands(int nbPlayers) {

        AbstractCard[] island1 = new AbstractCard[nbPlayers * 4];

        AbstractCard[] island2 = new AbstractCard[nbPlayers * 2];

        AbstractCard[] island3 = new AbstractCard[nbPlayers * 2];

        AbstractCard[] island4 = new AbstractCard[nbPlayers * 2];

        AbstractCard[] island5 = new AbstractCard[nbPlayers * 2];

        AbstractCard[] island6 = new AbstractCard[nbPlayers * 2];

        AbstractCard[] island10 = new AbstractCard[nbPlayers];

        for (int i = 0; i < nbPlayers; i++) {
            island1[i] = new BlackSmithCard(AbstractCard.Name.LE_MARTEAU_DU_FORGERON, AbstractCard.Type.INSTANT, 0, 0, 1);
            island1[i + nbPlayers] = new BlackSmithCard(AbstractCard.Name.LE_COFFRE_DU_FORGERON, AbstractCard.Type.INSTANT, 2, 0, 1);
            island1[i + (nbPlayers * 2)] = new ResourceCard(AbstractCard.Name.L_ANCIEN, AbstractCard.Type.RECURRENT, 0, 1, 0);
            island1[i + (nbPlayers * 3)] = new ResourceCard(AbstractCard.Name.LES_HERBES_FOLLES, AbstractCard.Type.INSTANT, 2, 1, 0);
        }
        islands.put(1, island1);

        for (int i = 0; i < nbPlayers; i++) {
            island2[i] = new DivineFavorCard(AbstractCard.Name.LES_SABOTS_D_ARGENT, AbstractCard.Type.RECURRENT, 2, 0, 2);
            island2[i + nbPlayers] = new ResourceCard(AbstractCard.Name.LES_AILES_DE_LA_GARDIENNES, AbstractCard.Type.RECURRENT, 4, 2, 0);


            island3[i] = new DivineFavorCard(AbstractCard.Name.LES_SABOTS_D_ARGENT, AbstractCard.Type.RECURRENT, 2, 0, 2);
            island3[i + nbPlayers] = new ResourceCard(AbstractCard.Name.LES_AILES_DE_LA_GARDIENNES, AbstractCard.Type.RECURRENT, 4, 2, 0);




            island4[i] = new RegularCard(AbstractCard.Name.LE_PASSEUR, AbstractCard.Type.INSTANT, 12, 0,4);
            island4[i + nbPlayers] = new RegularCard(AbstractCard.Name.LA_MEDUSE, AbstractCard.Type.INSTANT, 14, 4,0);

            island5[i] = new ForgeCard(AbstractCard.Name.LE_CASQUE_D_INVISIBILITE, AbstractCard.Type.INSTANT, 4, 0, 5);
            island5[i + nbPlayers] = new ForgeCard(AbstractCard.Name.LE_MIROIR_ABYSSAL, AbstractCard.Type.INSTANT, 10, 5, 0);

            island6[i] = new DivineFavorCard(AbstractCard.Name.LA_PINCE, AbstractCard.Type.INSTANT, 8, 0, 6);
            island6[i + nbPlayers] = new DivineFavorCard(AbstractCard.Name.LA_PINCE, AbstractCard.Type.INSTANT, 10, 0, 6);

            island10[i] = new RegularCard(AbstractCard.Name.L_HYDRE, AbstractCard.Type.INSTANT, 26, 5, 5);
        }
        islands.put(2, island2);
        islands.put(3, island3);
        islands.put(4, island4);
        islands.put(5, island5);
        islands.put(6, island6);
        islands.put(10, island10);

    }

    public boolean removeCard(AbstractCard cardToRemove) {
        int key = (cardToRemove.getPrice()[0] + cardToRemove.getPrice()[1]);
        for(int i = 0; i < islands.get(key).length; i++) {
            if (islands.get(key)[i] != null && islands.get(key)[i].equals(cardToRemove)) {
                islands.get(key)[i] = null;
                return true;
            }
        }
        return false;
    }

    public ArrayList<AbstractCard> getIslandAvailables(int i) {
        ArrayList<AbstractCard> buyables = new ArrayList<>();
        AbstractCard[] cards = islands.get(i);

        for(int j = 0; j < islands.get(i).length; j++) {
            if(islands.get(i)[j] != null) {
                buyables.add(islands.get(i)[j]);
            }
        }
        return buyables;
    }

    public HashMap<Integer, AbstractCard[]> getIslands() {
        return islands;
    }
}

