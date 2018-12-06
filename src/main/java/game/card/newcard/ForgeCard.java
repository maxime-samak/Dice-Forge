package game.card.newcard;

import bot.AbstractBot;
import game.dice.BuyDiceCard;
import game.dice.Dice;
import game.dice.DiceCard;
import game.dice.Resource;

public class ForgeCard extends AbstractCard{

    public ForgeCard(AbstractCard.Name name, AbstractCard.Type type, int victory, int solarPrice, int lunarPrice) {
        super(name, type, victory, solarPrice, lunarPrice);
    }

    public void getEffect(AbstractBot bot) {
        switch (getName()) {
            case LE_MIRROIR_ABYSSAL:
                BuyDiceCard.setSpecial(bot.getDice2(), bot.getPreferedFace(), new DiceCard(0, Resource.QUESTION));
                break;

            case LE_CASQUE_D_INVISIBILITE:
                BuyDiceCard.setSpecial(bot.getDice2(), bot.getPreferedFace(), new DiceCard(0, Resource.X3));
                break;
        }
    }

    private void setCard(Dice dice, int face, DiceCard card) {

    }
}
