package objects;

import java.util.HashMap;

public class Sanctuaire {
    private HashMap<Integer, DiceCard[]> pools = new HashMap<>();

    private final int nbPlayers;

    public Sanctuaire(int nbPlayers){
        this.initPool();
        this.nbPlayers = nbPlayers;
    }

    public void initPool() {

        DiceCard[] lunar2 = new DiceCard[4]; //1lunar
        DiceCard[] gold2 = new DiceCard[4]; //3gold

        DiceCard[] solar3 = new DiceCard[4]; //1solar
        DiceCard[] gold3 = new DiceCard[4]; //4gold

        DiceCard[] mult4 = new DiceCard[4]; //choix

        DiceCard[] mult5 = new DiceCard[4]; //choix

        DiceCard[] lunar6 = new DiceCard[4]; //3lunar

        DiceCard[] solar8 = new DiceCard[4]; //3solar
        DiceCard[] victory8 = new DiceCard[4]; //3victory

        DiceCard[] mult12 = new DiceCard[4]; //choix


        // pool2
        for(int i = 0; i < 4; i++) {
            lunar2[i] = new DiceCard(2, Resource.LUNAR);
        }
        for(int i = 4; i < 8; i++) {
            gold2[i] = new DiceCard(3, Resource.GOLD);
        }

        //pool3
        for(int i = 0; i < 4; i++) {
            solar3[i] = new DiceCard(1, Resource.SOLAR);
        }
        for(int i = 4; i < 8; i++) {
            gold3[i] = new DiceCard(4, Resource.GOLD);
        }

        //pool4 à faire pour v3

        //pool5 à faire pour v3

        //pool6

        //pool6
        for(int i = 0; i < 4; i++) {
            solar8[i] = new DiceCard(3, Resource.SOLAR);
        }
        for(int i = 4; i < 8; i++) {
            victory8[i] = new DiceCard(3, Resource.VICTORY);
        }

        //pool8

        for(int i = 0; i < 4; i++) {
            solar8[i] = new DiceCard(3, Resource.SOLAR);
        }
        for(int i = 4; i < 8; i++) {
            victory8[i] = new DiceCard(3, Resource.VICTORY);
        }

        //pool12 à faire pour v3
    }

    public DiceCard getDiceCardFromPool(int i) {
        DiceCard dc = pools.get(i)[0];
        pools.get(i)[0] = null;
        return dc;
    }

}