package game.dice;

import java.util.ArrayList;
import java.util.HashMap;

public class Sanctuary {

    private HashMap<Integer, DiceCard[]> pools = new HashMap<>();
    private final int nbPlayers;

    public Sanctuary(int nbPlayers){
        this.nbPlayers = nbPlayers;
        this.initPool();

    }

    public void initPool() {

        DiceCard[] pool2 = new DiceCard[nbPlayers * 2]; //1lunar and 3gold

        DiceCard[] pool3 = new DiceCard[nbPlayers * 2]; //1solar and 4 gold

        //DiceCard[] pool4 = new DiceCard[4]; //choix

        //DiceCard[] pool5 = new DiceCard[4]; //choix

        DiceCard[] pool6 = new DiceCard[nbPlayers]; //2lunar

        DiceCard[] pool8 = new DiceCard[nbPlayers * 2]; //3solar and 3 victory

        //DiceCard[] pool12 = new DiceCard[4]; //choix

        // pool2
        for(int i = 0; i < nbPlayers; i++) {
            pool2[i] = new DiceCard(1, Resource.LUNAR);

            pool2[i + nbPlayers] = new DiceCard(3, Resource.GOLD);
        }
        pools.put(2,pool2);

        //pool3
        for(int i = 0; i < nbPlayers; i++) {
            pool3[i] = new DiceCard(1, Resource.SOLAR);

            pool3[i + nbPlayers] = new DiceCard(4, Resource.GOLD);
        }
        pools.put(3,pool3);

        //pool4 à faire pour v3

        //pool5 à faire pour v3

        //pool6
        for(int i = 0; i < nbPlayers; i++) {
            pool6[i] = new DiceCard(2, Resource.LUNAR);
        }
        pools.put(6,pool6);

        //pool8
        for(int i = 0; i < nbPlayers; i++) {
            pool8[i] = new DiceCard(3, Resource.SOLAR);

            pool8[i + nbPlayers] = new DiceCard(3, Resource.VICTORY);
        }
        pools.put(8,pool8);

        //pool12 à faire pour v3
    }

    public ArrayList<DiceCard> getPoolAvailables(int i) {
        ArrayList<DiceCard> buyables= new ArrayList<DiceCard>();
        DiceCard[] cards = pools.get(i);
        for(int j = 0; j < cards.length; j++) {
            if(cards[j] != null) {
                buyables.add(cards[j]);
            }
        }
        return buyables;
    }

    public boolean removeCard(int pool, DiceCard cardToRemove) {
        for(int i = 0; i < pools.get(pool).length; i++) {
            if(pools.get(pool)[i] != null && pools.get(pool)[i].equals(cardToRemove)) {
                pools.get(pool)[i] = null;
                return true;
            }
        }
        return false;
    }

    public HashMap<Integer, DiceCard[]> getPools() { return this.pools; }
}
