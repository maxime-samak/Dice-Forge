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

    }

}