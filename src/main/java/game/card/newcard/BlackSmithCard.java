package game.card.newcard;

import bot.AbstractBot;
import game.ScoreCounter;

public class BlackSmithCard extends AbstractCard {

    public BlackSmithCard(Name name, Type type, int victory, int solarPrice, int lunarPrice) {
        super(name, type, victory, solarPrice, lunarPrice);
    }

    public void getEffect(AbstractBot bot) {
        switch (getName()) {
            case LE_COFFRE_DU_FORGERON:
                ScoreCounter.extend(bot.getBotScore());
                break;

            case LE_MARTEAU_DU_FORGERON:
                ScoreCounter.initForge(bot.getBotScore());
        }
    }
}
