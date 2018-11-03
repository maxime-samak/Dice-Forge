package objects;

import java.util.HashMap;

import static objects.Resource.*;

public class Dice {

    private DiceCard F1;
    private DiceCard F2;
    private DiceCard F3;
    private DiceCard F4;
    private DiceCard F5;
    private DiceCard F6;

    public Dice(){}

    public DiceCard getFi(int i)
    {
        switch (i)
        {
            case 1:
                return F1;
            case 2:
                return F2;
            case 3:
                return F3;
            case 4:
                return F4;
            case 5:
                return F5;
            case 6:
                return F6;
            default:
                return null;
        }
    }

    public void solarDiceInit() {
        HashMap<Resource,Integer> gold = new HashMap();
        HashMap<Resource,Integer> solar = new HashMap();
        gold.put(Resource.GOLD,1);
        solar.put(Resource.SOLAR,1);

        F1 = new DiceCard(solar,"");
        F2 = new DiceCard(gold,"");
        F3 = new DiceCard(gold,"");
        F4 = new DiceCard(gold,"");
        F5 = new DiceCard(gold,"");
        F6 = new DiceCard(gold,"");
    }

    public void lunarDiceInit() {
        HashMap<Resource,Integer> gold = new HashMap();
        HashMap<Resource,Integer> lunar = new HashMap();
        HashMap<Resource,Integer> victory = new HashMap();
        gold.put(Resource.GOLD,1);
        lunar.put(Resource.LUNAR,1);
        victory.put(Resource.VICTORY,2);

        F1 = new DiceCard(lunar,"");
        F2 = new DiceCard(victory,"");
        F3 = new DiceCard(gold,"");
        F4 = new DiceCard(gold,"");
        F5 = new DiceCard(gold,"");
        F6 = new DiceCard(gold,"");
    }

    public void showFace(int i) {
        switch(i){
            case 1:
                for(int j = 0; j < F1.getResource().size(); j++) {
                    System.out.println(F1.getValue(F1.getResource().get(j)) + " " + F1.getResource().get(j));
                }
                break;
            case 2:
                for(int j = 0; j < F2.getResource().size(); j++) {
                    System.out.println(F2.getValue(F2.getResource().get(j)) + " " + F2.getResource().get(j));
                }
                break;
            case 3:
                for(int j = 0; j < F3.getResource().size(); j++) {
                    System.out.println(F3.getValue(F3.getResource().get(j)) + " " + F3.getResource().get(j));
                }
                break;
            case 4:
                for(int j = 0; j < F4.getResource().size(); j++) {
                    System.out.println(F4.getValue(F4.getResource().get(j)) + " " + F4.getResource().get(j));
                }
                break;
            case 5:
                for(int j = 0; j < F5.getResource().size(); j++) {
                    System.out.println(F5.getValue(F5.getResource().get(j)) + " " + F5.getResource().get(j));
                }
                break;
            case 6:
                for(int j = 0; j < F6.getResource().size(); j++) {
                    System.out.println(F6.getValue(F6.getResource().get(j)) + " " + F6.getResource().get(j));
                }
                break;
            default:
                System.out.println(">>>Problème<<<");
        }
    }

    public void setface(DiceCard dc) {
        F3 = dc;
    }
    public String toString(){
        return F1 + "\n" + F2 + "\n" + F3 + "\n" + F4 + "\n" + F5 + "\n" + F6 + "\n";
    }

}
