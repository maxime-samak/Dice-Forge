package game.card;

import bot.AbstractBot;

import java.util.ArrayList;
import java.util.HashMap;

public class Inventory {

    private HashMap<AbstractBot, ArrayList<AbstractCard>> inventory = new HashMap<>();

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
            if(card.getType().equals(AbstractCard.Type.RECURRENT))
                output.add(card);
        }
        return output;
    }

    public String toString(AbstractBot bot) {
        String output = "Inventaire de " + bot.getBotID() + " :" +" [";
        for (int i = 0; i < this.inventory.get(bot).size(); i++) {
            output =output+this.inventory.get(bot).get(i).getName();
            if (i + 1 < this.inventory.get(bot).size()) {
                output += ",";
            }
        }
        output += "]\n";
        return output;
    }

}
