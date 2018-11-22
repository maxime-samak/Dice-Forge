package game;

import game.dice.Dice;

/**
 * Classe DiceRoll représente un lancé de dés pseudo-aléatoire
 */
public class DiceRoll {

    public static String roll(Dice d1, Dice d2) {
        int rollD1 = (int) (Math.random() * 6) + 1;
        int rollD2 = (int) (Math.random() * 6) + 1;

        d1.showFace(rollD1); // print
        d2.showFace(rollD2); // print

        String token1 = d1.getFi(rollD1).getValue() + "@" + d1.getFi(rollD1).getResource();
        String token2 = d2.getFi(rollD1).getValue() + "@" + d2.getFi(rollD1).getResource();

        for(int i = 1; i < d1.getFi(rollD1).getValueArray().length; i++) {
            token1 += d1.getFi(rollD1).getValueArray()[i] + "@" + d1.getFi(rollD1).getResourceArray()[i];
            if(i + 1 < d1.getFi(rollD1).getValueArray().length) { token1 += "@";}
        }

        for(int i = 1; i < d2.getFi(rollD2).getValueArray().length; i++) {
            token1 += d2.getFi(rollD2).getValueArray()[i] + "@" + d2.getFi(rollD2).getResourceArray()[i];
            if(i + 1 < d2.getFi(rollD2).getValueArray().length) { token1 += "@";}
        }

        String output = token1 + "%" + token2;
        //output = x@nom%y@nom
        //output complexe = x@nom%y@nom@z@nom
        return output;
    }

    public static String roll(Dice d1) {
        int rollD1 = (int) (Math.random() * 6) + 1;

        d1.showFace(rollD1); // print

        String output = d1.getFi(rollD1).getValue() + "@" + d1.getFi(rollD1).getResource();
        //output = x@nom
        return output;
    }



}