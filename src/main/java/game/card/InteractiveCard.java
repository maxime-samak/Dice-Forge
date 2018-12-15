package game.card;

import bot.AbstractBot;
import game.DiceRoll;
import game.ScoreCounter;
import game.dice.DiceCard;
import game.dice.Resource;

import java.util.ArrayList;

public class InteractiveCard extends AbstractCard {
    AbstractBot[] botArray;

    public InteractiveCard(Name name, Type type, int victory, int solarPrice, int lunarPrice, AbstractBot[] botArray) {
        super(name, type, victory, solarPrice, lunarPrice);
        this.botArray = botArray;
    }

    public Object getEffect(AbstractBot bot) {
        ScoreCounter score = new ScoreCounter();
        switch (getName()) {

            case LES_SATYRES:
                ArrayList<DiceCard> rolls = ScoreCounter.getRolls();
                rolls.clear();
                for (AbstractBot b : botArray) {
                    if (!bot.equals(b)) {
                        ScoreCounter.getRolls().add(DiceRoll.roll(bot.getDice1()));
                        ScoreCounter.getRolls().add(DiceRoll.roll(bot.getDice2()));
                    }
                }
                int maxvalue = 0;
                Resource res = bot.getPreferredResource();

                for (DiceCard c : rolls) {
                    if (c.getResourceArray().length > 1) {
                        if (maxvalue < c.getValueArray()[bot.choose(c)])
                            maxvalue = c.getValueArray()[bot.choose(c)];
                    }
                    else {
                        if (maxvalue < c.getValue() && c.getResource().equals(res.resourceName())) {
                            maxvalue = c.getValue();
                        }
                    }
                }
                rolls.remove(new DiceCard(maxvalue, res));
                score.addResource(bot.getBotScore(), res, maxvalue);

                maxvalue = 0;
                res = bot.getPreferredResource();

                for (DiceCard c : rolls) {
                    if (c.getResourceArray().length > 1) {
                        if (maxvalue < c.getValueArray()[bot.choose(c)])
                            maxvalue = c.getValueArray()[bot.choose(c)];
                    }
                    else {
                        if (maxvalue < c.getValue() && c.getResource().equals(res.resourceName())) {
                            maxvalue = c.getValue();
                        }
                    }
                }
                score.addResource(bot.getBotScore(), res, maxvalue);
                ;

            case LE_MINOTAURE:;
        }
        return null;
    }
}
