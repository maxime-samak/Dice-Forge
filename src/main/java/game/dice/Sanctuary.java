package game.dice;

import java.util.ArrayList;
import java.util.*;
import java.util.HashMap;

public class Sanctuary {

    private HashMap<Integer, DiceCard[]> pools = new HashMap<>();
    private final int nbPlayers;

    public Sanctuary(int nbPlayers){
        this.nbPlayers = nbPlayers;
        this.initPool();

    }

    public void initPool() {

        int[] random = {0,1,2,3};

        DiceCard[] pool2 = new DiceCard[nbPlayers * 2]; //1lunar and 3gold

        DiceCard[] pool3 = new DiceCard[nbPlayers * 2]; //1solar and 4 gold

        /* *********************** */
        /* -- DiceCard Complexe -- */
        /* *********************** */
        DiceCard[] pool4 = new DiceCard[nbPlayers]; //Complexe

        DiceCard[] pool5 = new DiceCard[nbPlayers]; //Complexe

        DiceCard[] pool6 = new DiceCard[nbPlayers]; //2lunar

        DiceCard[] pool8 = new DiceCard[nbPlayers * 2]; //3solar and 3 victory

        DiceCard[] pool12 = new DiceCard[nbPlayers]; //Complexe

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

        //pool4
        /* *********************** */
        /* -- DiceCard Complexe -- */
        /* *********************** */

        shuffle(random);
        for (int i = 0; i < nbPlayers; i++){
            pool4[i] = randomPool4(random[i]);
        }
        pools.put(4,pool4);

        //pool5
        /* *********************** */
        /* -- DiceCard Complexe -- */
        /* *********************** */
        for (int i = 0; i < nbPlayers; i++){
            pool5[i] = new DiceCard(new int[]{0, 3, 2}, new Resource[]{Resource.CHOICE, Resource.VICTORY, Resource.GOLD});
        }
        pools.put(5, pool5);

        //pool6
        for(int i = 0; i < nbPlayers; i++) {
            pool6[i] = new DiceCard(2, Resource.LUNAR);
        }
        pools.put(6,pool6);

        //pool8
        for(int i = 0; i < nbPlayers; i++) {
            pool8[i] = new DiceCard(2, Resource.SOLAR);

            pool8[i + nbPlayers] = new DiceCard(3, Resource.VICTORY);
        }
        pools.put(8,pool8);

        //pool12
        /* *********************** */
        /* -- DiceCard Complexe -- */
        /* *********************** */
        shuffle(random);
        for (int i = 0; i < nbPlayers; i++){
            pool12[i] = randomPool12(random[i]);
        }
        pools.put(12,pool12);
    }

    /**
     * La méthode renvoie les faces de dès qui n'ont pas été achetées dans une pool donnée dont le numéro est passé en paramètre (i).
     *
     * @param i numéro de la pool
     * @return
     */
    public ArrayList<DiceCard> getPoolAvailables(int i) {
        ArrayList<DiceCard> buyables = new ArrayList<DiceCard>();
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

    /* *********************** */
    /* -- DiceCard Complexe -- */
    /* *********************** */
    private DiceCard randomPool4(int e){
        e = e % 4;
        switch (e) {
            case 0:
                return new DiceCard(new int[]{0, 2, 1} , new Resource[]{Resource.PLUS, Resource.GOLD, Resource.LUNAR});
            case 1:
                return new DiceCard(6, Resource.GOLD);
            case 2:
                return new DiceCard(new int[]{0, 1, 1}, new Resource[]{Resource.PLUS, Resource.VICTORY, Resource.SOLAR});
            case 3:
                return new DiceCard(new int[]{0, 1, 1, 1}, new Resource[]{Resource.CHOICE, Resource.GOLD, Resource.SOLAR, Resource.LUNAR});
            default:
                return  new DiceCard(-1, Resource.GOLD);
        }
    }

    private DiceCard randomPool12(int e){
        e = e % 4;
        switch (e) {
            case 0:
                return new DiceCard(4, Resource.VICTORY);
            case 1:
                return new DiceCard(new int[]{0, 2 ,2}, new  Resource[]{Resource.PLUS, Resource.VICTORY, Resource.LUNAR});
            case 2:
                return new DiceCard(new int[]{0, 2, 2, 2}, new Resource[]{Resource.CHOICE, Resource.GOLD, Resource.SOLAR, Resource.LUNAR});
            case 3:
                return new DiceCard(new int[]{0, 1, 1, 1, 1}, new Resource[]{Resource.PLUS, Resource.VICTORY, Resource.GOLD, Resource.SOLAR, Resource.LUNAR});
            default:
                return  new DiceCard(-1, Resource.GOLD);
        }
    }

    /* *********************** */
    /* -- DiceCard Complexe -- */
    /* *********************** */
    private int[] shuffle(int[] e){
        Random r = new Random();
        for (int x = 0; x < 500; x++){
            int i = r.nextInt(4);
            int j = r.nextInt(4);
            int tmp = e[i];
            e[i] = e[j];
            e[j] = tmp;
        }
        return e;
    }

    public HashMap<Integer, DiceCard[]> getPools() { return this.pools; }
}
