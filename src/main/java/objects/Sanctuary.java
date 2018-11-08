package objects;

import objects.DiceCard;
import objects.Resource;

import java.util.ArrayList;
import java.util.HashMap;

public class Sanctuary {
    private HashMap<Integer, DiceCard[]> pools = new HashMap<>();

    private final int nbPlayers;

    public Sanctuary(int nbPlayers){
        this.initPool();
        this.nbPlayers = nbPlayers;
    }

    public void initPool() {

        DiceCard[] pool2 = new DiceCard[8]; //1lunar and 3gold

        DiceCard[] pool3 = new DiceCard[8]; //1solar and 4 gold

        DiceCard[] pool4 = new DiceCard[4]; //choix

        DiceCard[] pool5 = new DiceCard[4]; //choix

        DiceCard[] pool6 = new DiceCard[4]; //2lunar

        DiceCard[] pool8 = new DiceCard[8]; //3solar and 3 victory

        DiceCard[] pool12 = new DiceCard[4]; //choix


        // pool2
        for(int i = 0; i < 4; i++) {
            pool2[i] = new DiceCard(2, Resource.LUNAR);
        }
        for(int i = 4; i < 8; i++) {
            pool2[i] = new DiceCard(3, Resource.GOLD);
        }
        pools.put(2,pool2);
        //pool3
        for(int i = 0; i < 4; i++) {
            pool3[i] = new DiceCard(1, Resource.SOLAR);
        }
        for(int i = 4; i < 8; i++) {
            pool3[i] = new DiceCard(4, Resource.GOLD);
        }
        pools.put(3,pool3);

        //pool4 à faire pour v3

        //pool5 à faire pour v3

        //pool6
        for(int i = 0; i < 4; i++) {
            pool6[i] = new DiceCard(2, Resource.LUNAR);
        }
        pools.put(6,pool6);

        //pool8
        for(int i = 0; i < 4; i++) {
            pool8[i] = new DiceCard(3, Resource.SOLAR);
        }
        for(int i = 4; i < 8; i++) {
            pool8[i] = new DiceCard(3, Resource.VICTORY);
        }
        pools.put(8,pool8);

        //pool12 à faire pour v3
    }

    public DiceCard getDiceCardFromPool(int i) {
        DiceCard dc = pools.get(i)[0];
        pools.get(i)[0] = null;
        return dc;
    }

    public ArrayList<DiceCard> getPoolAvailables(int i)
    {
        ArrayList<DiceCard> buyables= new ArrayList<DiceCard>();
        int b=0;
        DiceCard[] cards = pools.get(i);
        for(int cpt=0;cpt<cards.length;cpt++)
        {
            if(cards[cpt]!=null)
            {
                buyables.add(cards[cpt]);
                b++;
            }
        }
        return buyables;
    }

}
