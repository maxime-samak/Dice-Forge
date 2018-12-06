package game.card;

import bot.AbstractBot;

import java.util.ArrayList;
import java.util.HashMap;

public class Inventory {

    private static HashMap<AbstractBot, ArrayList<AbstractCard>> inventory = new HashMap<>();

    public Inventory(AbstractBot[] botArray) {
        for (AbstractBot bot : botArray) {
            inventory.put(bot, new ArrayList<>());
        }
    }

    protected void affectCard(AbstractBot bot, AbstractCard card) {
        inventory.get(bot).add(card);
    }

    public ArrayList<AbstractCard> getRecurrent(AbstractBot bot) {
        ArrayList<AbstractCard> output = new ArrayList<>();
        for (AbstractCard card : inventory.get(bot)) {
            output.add(card);
        }
        return output;
    }

    public String toString() {
        String inventory = "";
        for (AbstractBot bot : this.inventory.keySet()) {
            String output = "(" + bot.getBotID() + ")" +" [";
            for (int i = 0; i < this.inventory.get(bot).size(); i++) {
                output += this.inventory.get(bot).get(i);
                if (i + 1 < this.inventory.get(bot).size()) {
                    output += ",";
                }
                output += "]\n";
                inventory += output;
            }
        }
        return inventory;
    }

}
