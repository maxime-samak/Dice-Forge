package game.card;

import java.util.ArrayList;
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

        NeutralCard[] island10 = new NeutralCard[nbPLayers];

        for(int i = 0; i < nbPLayers; i++) {
            island4[i] = new NeutralCard(12, Effect.SIMPLE, 0, 4);

            island4[i + nbPLayers] = new NeutralCard(14, Effect.SIMPLE, 4,0);
        }
        islands.put(4, island4);

        for(int i = 0; i < nbPLayers; i++) {
            island10[i] = new NeutralCard(26, Effect.SIMPLE, 5, 5);
        }
        islands.put(10, island10);

    }

    public HashMap<Integer, AbstractCard[]> getIslands() {
        return islands;
    }

    public void removeCard(AbstractCard card){
        int key = Math.max(card.getPrice()[0], card.getPrice()[1]);
        for(int i = 0; i < islands.get(key).length; i++){
            if (islands.get(key)[i] == card){
                islands.get(key)[i] = null;
                break;
            }
        }
    }

    public ArrayList<AbstractCard> getIslandAvailables(int i)
    {
        ArrayList<AbstractCard> buyables = new ArrayList<>();
        AbstractCard[] cards = islands.get(i);
        for(int j = 0; j < cards.length; j++) {
            if(cards[j] != null) {
                buyables.add(cards[j]);
            }
        }
        return buyables;
    }

}