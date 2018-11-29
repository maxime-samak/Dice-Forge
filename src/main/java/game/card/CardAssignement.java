package game.card;

import bot.AbstractBot;

import java.util.ArrayList;
import java.util.HashMap;

public class CardAssignement {

    private static HashMap<AbstractBot, ArrayList<Card>> cardAssignement =  new HashMap<>();

    public static ArrayList<Card> getListCard(AbstractBot bot) {
        return cardAssignement.get(bot);
    }

    protected static void setCardAssignement(AbstractBot bot, Card card){
        if(card.isTypeReinforcement()){
                cardAssignement.get(bot).add(card);
        }
    }

    public static void initCardAssignement(AbstractBot[] bots){
        for(AbstractBot bot : bots){
            ArrayList<Card> listCard = new ArrayList<>();
            cardAssignement.put(bot, listCard);
        }
    }

}
