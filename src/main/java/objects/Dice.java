package objects;

import static objects.Resource.*;

public class Dice {

    private DiceCard F1;
    private DiceCard F2;
    private DiceCard F3;
    private DiceCard F4;
    private DiceCard F5;
    private DiceCard F6;

    public Dice(int i)
    {
        switch (i)
        {
            case 1:
                solarDiceInit();
                break;
            case 2:
                lunarDiceInit();
                break;
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
}
