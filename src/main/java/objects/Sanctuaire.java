package objects;

import java.util.HashMap;

public class Sanctuaire {
    private HashMap<Integer, DiceCard[]> pools = new HashMap<>();

    public Sanctuaire(){
        this.initPool();
    }


    public void initPool() {

        DiceCard[] pool2 = new DiceCard[8];
        DiceCard[] pool3 = new DiceCard[8];
        DiceCard[] pool4 = new DiceCard[4];
        DiceCard[] pool5 = new DiceCard[4];
        DiceCard[] pool6 = new DiceCard[4];
        DiceCard[] pool8 = new DiceCard[8];
        DiceCard[] pool12 = new DiceCard[4];

        HashMap<Resource,Integer> gold = new HashMap();
        HashMap<Resource,Integer> lunar = new HashMap();
        HashMap<Resource,Integer> solar = new HashMap();
        HashMap<Resource,Integer> victory = new HashMap();
        HashMap<Resource,Integer> multichoices = new HashMap();
        HashMap<Resource,Integer> multiadd = new HashMap();

        // pool2
        gold.put(Resource.GOLD,3);
        lunar.put(Resource.LUNAR,1);
        for(int i = 0; i < 4; i++) {
            pool2[i] = new DiceCard(gold,"");
        }
        for(int i = 4; i < 8; i++) {
            pool2[i] = new DiceCard(lunar,"");
        }
        pools.put(2,pool2);

        //pool3
        gold.put(Resource.GOLD,4);
        solar.put(Resource.SOLAR,1);
        for(int i = 0; i < 4; i++) {
            pool3[i] = new DiceCard(gold,"");
        }
        for(int i = 4; i < 8; i++) {
            pool3[i] = new DiceCard(solar,"");
        }
        pools.put(3,pool3);

        //pool4
        multiadd.put(Resource.LUNAR,1);
        multiadd.put(Resource.GOLD,2);
        pool4[0] = new DiceCard(multiadd,"+");

        gold.put(Resource.GOLD,6);
        pool4[1] = new DiceCard(gold,"");

        multichoices.put(Resource.LUNAR,1);
        multichoices.put(Resource.SOLAR,1);
        multichoices.put(Resource.GOLD,1);
        pool4[2] = new DiceCard(multichoices,"?");

        multiadd.remove(Resource.LUNAR);
        multiadd.remove(Resource.GOLD);
        multiadd.put(Resource.SOLAR,1);
        multiadd.put(Resource.VICTORY,1);
        pool4[3] = new DiceCard(multiadd,"+");

        pools.put(4,pool4);


        //pool5
        multichoices.remove(Resource.LUNAR);
        multichoices.put(Resource.SOLAR,2);
        multichoices.put(Resource.GOLD,3);
        for(int i = 0; i < 4; i++) {
            pool5[i] = new DiceCard(multichoices, "?");
        }
        pools.put(5,pool5);

        //pool6
        lunar.put(Resource.LUNAR,2);
        for(int i = 0; i < 4; i++) {
            pool6[i] = new DiceCard(lunar, "");
        }
        pools.put(6,pool6);

        //pool8
        solar.put(Resource.SOLAR,2);
        victory.put(Resource.VICTORY,3);
        for(int i = 0; i < 4; i++) {
            pool8[i] = new DiceCard(solar, "");
        }
        for(int i = 4; i < 8; i++) {
            pool8[i] = new DiceCard(victory, "");
        }
        pools.put(8,pool8);

        //pool12
        multiadd.put(Resource.LUNAR,1);
        multiadd.put(Resource.GOLD,1);
        multiadd.put(Resource.SOLAR,1);
        multiadd.put(Resource.VICTORY,1);
        pool12[0] = new DiceCard(multiadd,"?");

        multichoices.put(Resource.SOLAR,2);
        multichoices.put(Resource.LUNAR,2);
        multichoices.put(Resource.GOLD,2);
        pool12[1] = new DiceCard(multichoices,"+");

        victory.put(Resource.VICTORY,4);
        pool12[2] = new DiceCard(victory,"");

        multiadd.remove(Resource.GOLD);
        multiadd.remove(Resource.SOLAR);
        multiadd.put(Resource.LUNAR,2);
        multiadd.put(Resource.VICTORY,2);
        pool12[3] = new DiceCard(victory,"+");

        pools.put(12,pool12);

    }

    public DiceCard getDiceCardFromPool(int i) {
        DiceCard dc = pools.get(i)[0];
        pools.get(i)[0] = null;
        return dc;
    }

}
