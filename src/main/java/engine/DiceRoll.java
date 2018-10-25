package engine;

import objects.Dice;

public class DiceRoll {

    public static void roll(Dice d1, Dice d2) {
        d1.showFace((int) (Math.random() * 6) + 1);
        d2.showFace((int) (Math.random() * 6) + 1);
    }

}