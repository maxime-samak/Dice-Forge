package game;

import game.dice.Dice;
import game.dice.DiceCard;

import java.util.Random;
/**
 * Classe DiceRoll représente un lancé de dés pseudo-aléatoire
 */
public class DiceRoll {

    public static DiceCard roll(Dice d1) {
        Random random = new Random();
        int rollD1 = random.nextInt(6) + 1;

        DiceCard output = d1.getFi(rollD1);

        return output;
    }
}