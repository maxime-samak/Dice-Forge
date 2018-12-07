package game.card;

import bot.AbstractBot;
import game.ScoreCounter;
import game.dice.Resource;

public class ResourceCard extends AbstractCard {

    public ResourceCard(Name name, Type type, int victory, int solarPrice, int lunarPrice) {
        super(name, type, victory, solarPrice, lunarPrice);
    }

    public Object getEffect(AbstractBot bot) {
        switch (getName()) {
            case L_ANCIEN:
                if (bot.tradeGold() && bot.getBotScore().getGold() >= 3) {
                    ScoreCounter.payGold(bot.getBotScore(),3);
                    ScoreCounter.addResource(bot.getBotScore(), Resource.VICTORY, 4);
                    return "Offre acceptée";
                }
                return "Offre rejetée";

            case LES_HERBES_FOLLES:
                ScoreCounter.addResource(bot.getBotScore(), Resource.GOLD, 3);
                ScoreCounter.addResource(bot.getBotScore(), Resource.LUNAR, 3);
                return null;

            case LES_AILES_DE_LA_GARDIENNES:
                switch (bot.getPreferedResource()) {
                    case GOLD:
                        ScoreCounter.addResource(bot.getBotScore(), Resource.GOLD, 1);
                        return "Reçu -> 1 GOLD";
                    case SOLAR:
                        ScoreCounter.addResource(bot.getBotScore(), Resource.SOLAR, 1);
                        return "Reçu -> 1 SOLAR";
                    case LUNAR:
                        ScoreCounter.addResource(bot.getBotScore(), Resource.LUNAR, 1);
                        return "Reçu -> 1 LUNAR";
                    default:
                        ScoreCounter.addResource(bot.getBotScore(), Resource.GOLD, 1);
                        return "Reçu -> 1 GOLD";
                }
        }
        return null;
    }


}
