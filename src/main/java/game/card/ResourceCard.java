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
                return "Reçu 3 "+Resource.GOLD.resourceName()+"et 3 "+Resource.GOLD.resourceName();

            case LES_AILES_DE_LA_GARDIENNES:
                switch (bot.getPreferredResource()) {
                    case GOLD:
                        ScoreCounter.addResource(bot.getBotScore(), Resource.GOLD, 1);
                        return "Reçu -> 1 "+Resource.GOLD.resourceName();
                    case SOLAR:
                        ScoreCounter.addResource(bot.getBotScore(), Resource.SOLAR, 1);
                        return "Reçu -> 1 "+Resource.SOLAR.resourceName();
                    case LUNAR:
                        ScoreCounter.addResource(bot.getBotScore(), Resource.LUNAR, 1);
                        return "Reçu -> 1 "+Resource.LUNAR.resourceName();
                    default:
                        ScoreCounter.addResource(bot.getBotScore(), Resource.GOLD, 1);
                        return "Reçu -> 1 "+Resource.GOLD.resourceName();
                }
        }
        return null;
    }


}
