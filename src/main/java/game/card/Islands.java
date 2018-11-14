package game.card;

import java.util.HashMap;

public class Islands {

    private HashMap<Integer, AbstractCard[]> islands = new HashMap<>();
    private final int nbPLayers;

    public Islands(int nbPLayers) {
        this.initIslands(nbPLayers);
        this.nbPLayers = nbPLayers;

    }

    public void initIslands(int nbPLayers) {

        NeutralCard[] island4 = new NeutralCard[nbPLayers * 2];

        NeutralCard[] island5 = new NeutralCard[nbPLayers];

        for(int i = 0; i < nbPLayers; i++) {
            island4[i] = new NeutralCard(12, Effect.SIMPLE, 0, 4);

            island4[i + nbPLayers] = new NeutralCard(14, Effect.SIMPLE, 4,0);
        }
        islands.put(4, island4);

        for(int i = 0; i < nbPLayers; i++) {
            island5[i] = new NeutralCard(26, Effect.SIMPLE, 5, 5);
        }
        islands.put(5, island5);

    }

    public HashMap<Integer, AbstractCard[]> getIslands() {
        return islands;
    }

}