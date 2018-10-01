package engine;

import objects.Dice;

public class DiceRoll {

    public static void roll(Dice d1, Dice d2) {
        d1.showFace((int) (Math.random() * 6) + 1);
        d2.showFace((int) (Math.random() * 6) + 1);
    }

    public static void main(String[] args) {
        Dice d1 = new Dice();
        d1.solarDiceInit();
        Dice d2 = new Dice();
        d2.lunarDiceInit();
        int i = 10;
        while(i > 0) {
            roll(d1, d2);
            i--;
        }

    }
}
