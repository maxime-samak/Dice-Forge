package game.card;

import bot.AbstractBot;

public class RegularCard extends AbstractCard {

    public RegularCard(Name name, Type type, int victory, int solarPrice, int lunarPrice) {
        super(name, type, victory, solarPrice, lunarPrice);
    }

    public void getEffect(AbstractBot bot) {
        //ne fait rien
    }

}
