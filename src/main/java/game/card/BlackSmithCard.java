package game.card;

import bot.AbstractBot;
import game.ScoreCounter;

public class BlackSmithCard extends AbstractCard {

    public BlackSmithCard(Name name, Type type, int victory, int solarPrice, int lunarPrice) {
        super(name, type, victory, solarPrice, lunarPrice);
    }

    public Object getEffect(AbstractBot bot) {
        switch (getName()) {
            case LE_COFFRE_DU_FORGERON:
                ScoreCounter.extend(bot.getBotScore());
                return "Inventaire étendu";

            case LE_MARTEAU_DU_FORGERON:
                ScoreCounter.initForge(bot.getBotScore());
                return null;
        }
        return null;
    }
}
