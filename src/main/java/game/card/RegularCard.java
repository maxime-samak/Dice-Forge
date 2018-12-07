package game.card;

import bot.AbstractBot;

public class RegularCard extends AbstractCard {

    public RegularCard(Name name, Type type, int victory, int solarPrice, int lunarPrice) {
        super(name, type, victory, solarPrice, lunarPrice);
    }

    public Object getEffect(AbstractBot bot) {
        return null;
        //ne fait rien
    }

}
