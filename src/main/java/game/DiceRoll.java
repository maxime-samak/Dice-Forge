package game;

import game.dice.Dice;
import game.dice.DiceCard;

import java.util.Random;
/**
 * Classe DiceRoll représente un lancé de dés pseudo-aléatoire
 */
public class DiceRoll {

    public static DiceCard[] roll(Dice d1, Dice d2) {
        Random random = new Random();
        int rollD1 = random.nextInt(6) + 1;
        int rollD2 = random.nextInt(6) + 1;

        DiceCard[] output = new DiceCard[]{d1.getFi(rollD1), d2.getFi(rollD2)};

        return output;
    }

    public static DiceCard roll(Dice d1) {
        Random random = new Random();
        int rollD1 = random.nextInt(6) + 1;

        DiceCard output = d1.getFi(rollD1);

        return output;
    }
}