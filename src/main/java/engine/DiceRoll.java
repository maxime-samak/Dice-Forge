package engine;

import objects.Dice;

public class DiceRoll {

    public static String roll(Dice d1, Dice d2) {
        int rollD1 = (int) (Math.random() * 6) + 1;
        int rollD2 = (int) (Math.random() * 6) + 1;

        d1.showFace(rollD1); // print
        d2.showFace(rollD2); // print

        /* String output = d1.getFi(rollD1).getValue() + "@" + d1.getFi(rollD1).getResource()
                + "%" +d2.getFi(rollD2).getValue() + "@" + d2.getFi(rollD2).getResource();
        //output = x@nom%y@nom */

        String output = "";
        for(int j = 0; j < d1.getFi(rollD1).getResource().size(); j++) {
           output += d1.getFi(rollD1).getValue(d1.getFi(rollD1).getResource().get(j)) + "@" + d1.getFi(rollD1).getResource().get(j);
        }
        output += "%";
        for(int j = 0; j < d2.getFi(rollD1).getResource().size(); j++) {
            output += d2.getFi(rollD2).getValue(d2.getFi(rollD2).getResource().get(j)) + "@" + d2.getFi(rollD2).getResource().get(j);
        }
        return output;

    }
}