package game.card;

import bot.AbstractBot;

import java.util.ArrayList;
import java.util.HashMap;

public class CardAssignement {

    private static CardAssignement uniqueInstance = null;
    private static HashMap<AbstractBot, ArrayList<Card>> cardAssignement =  new HashMap<>();

    public static ArrayList<Card> getListCard(AbstractBot bot) {
        return cardAssignement.get(bot);
    }

    protected static void setCardAssignement(AbstractBot bot, Card card){
        if(card.isTypeReinforcement()){
            if (cardAssignement.containsKey(bot)) {
                cardAssignement.get(bot).add(card);
            }
            else {
                ArrayList<Card> listCard = new ArrayList<>();
                listCard.add(card);
                cardAssignement.put(bot, listCard);
            }
        }
    }
}
