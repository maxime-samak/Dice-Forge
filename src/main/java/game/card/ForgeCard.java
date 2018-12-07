package game.card;

import bot.AbstractBot;
import game.dice.BuyDiceCard;
import game.dice.Dice;
import game.dice.DiceCard;
import game.dice.Resource;

public class ForgeCard extends AbstractCard{

    public ForgeCard(AbstractCard.Name name, AbstractCard.Type type, int victory, int solarPrice, int lunarPrice) {
        super(name, type, victory, solarPrice, lunarPrice);
    }

    public Object getEffect(AbstractBot bot) {
        switch (getName()) {
            case LE_MIROIR_ABYSSAL:
                BuyDiceCard.setSpecial(bot.getDice2(), bot.getPreferredFace(), new DiceCard(0, Resource.QUESTION));
               return "Face spéciale QUESTION ajoutée";

            case LE_CASQUE_D_INVISIBILITE:
                BuyDiceCard.setSpecial(bot.getDice2(), bot.getPreferredFace(), new DiceCard(0, Resource.X3));
                return "Face spéciale X3 ajoutée";

        }
        return null;
    }

    private void setCard(Dice dice, int face, DiceCard card) {

    }
}
