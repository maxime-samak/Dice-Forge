package objects;

import static objects.Resource.*;

public class Dice {

    private DiceCard F1;
    private DiceCard F2;
    private DiceCard F3;
    private DiceCard F4;
    private DiceCard F5;
    private DiceCard F6;

    public Dice(){}

    public DiceCard getFi(int i) {
        switch (i) {
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
        F1 = new DiceCard(1, SOLAR);
        F2 = new DiceCard(1, GOLD);
        F3 = new DiceCard(1, GOLD);
        F4 = new DiceCard(1, GOLD);
        F5 = new DiceCard(1, GOLD);
        F6 = new DiceCard(1, GOLD);
    }

    public void lunarDiceInit() {
        F1 = new DiceCard(1, LUNAR);
        F2 = new DiceCard(2, VICTORY);
        F3 = new DiceCard(1, GOLD);
        F4 = new DiceCard(1, GOLD);
        F5 = new DiceCard(1, GOLD);
        F6 = new DiceCard(1, GOLD);
    }

    public void showFace(int i) {
        switch(i){
            case 1:
                System.out.println(F1.getValue() + " " + F1.getResource());
                break;
            case 2:
                System.out.println(F2.getValue() + " " + F2.getResource());
                break;
            case 3:
                System.out.println(F3.getValue() + " " + F3.getResource());
                break;
            case 4:
                System.out.println(F4.getValue() + " " + F4.getResource());
                break;
            case 5:
                System.out.println(F5.getValue() + " " + F5.getResource());
                break;
            case 6:
                System.out.println(F6.getValue() + " " + F6.getResource());
                break;
            default:
                System.out.println(">>>Problème<<<");
        }
    }

    protected void setDiceCard(int i, DiceCard card) {
        switch (i) {
            case 1:
                F1 = card;
                break;
            case 2:
                F2 = card;
                break;
            case 3:
                F3 = card;
                break;
            case 4:
                F4 = card;
                break;
            case 5:
                F5 = card;
                break;
            case 6:
                F6 = card;
                break;
            default:
                System.out.println("Out of range > Error");
        }
    }

    public String toString(){ return F1 + "\n" + F2 + "\n" + F3 + "\n" + F4 + "\n" + F5 + "\n" + F6 + "\n"; }
}