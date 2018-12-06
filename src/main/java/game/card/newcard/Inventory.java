package game.card.newcard;

import java.util.ArrayList;

public class Inventory {

    private ArrayList<AbstractCard> inventory;

    public Inventory() {
        inventory = new ArrayList<>();
    }

    public ArrayList<AbstractCard> getRecurrent() {
        ArrayList<AbstractCard> output = new ArrayList<>();
        for (AbstractCard card : inventory) {
            output.add(card);
        }
        return output;
    }

    public String toString() {
        String output = "[";
        for (int i = 0; i < inventory.size(); i++) {
            output += inventory.get(i).toString();
            if (i + 1 < inventory.size()) {
                output += ", ";
            }
        }
        return output;
    }

}
