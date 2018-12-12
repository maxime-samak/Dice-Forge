package game.card;

import bot.AbstractBot;
import game.DiceRoll;
import game.ScoreCounter;
import game.dice.Dice;
import game.dice.DiceCard;

public class DivineFavorCard extends AbstractCard {

    public DivineFavorCard(Name name, Type type, int victory, int solarPrice, int lunarPrice) {
        super(name, type, victory, solarPrice, lunarPrice);
    }

    public Object getEffect(AbstractBot bot) {

        Dice dice = bot.getDice1(); // getPreferredDice()
        DiceCard rollD1;
        DiceCard rollD2;

        switch (getName()) {
            case LES_SABOTS_D_ARGENT:
                rollD1 = DiceRoll.roll(dice);
                rollD1=bot.choose(rollD1);
                ScoreCounter.updateScore(bot.getBotScore(), rollD1);
                return rollD1;

            case LA_PINCE:
                DiceCard rollD3 = DiceRoll.roll(bot.getDice1());
                rollD3=bot.choose(rollD3);
                DiceCard rollD4 = DiceRoll.roll(bot.getDice2());
                rollD4=bot.choose(rollD4);
                ScoreCounter.updateScore(bot.getBotScore(), new DiceCard[]{rollD3,rollD4});

                rollD1 = DiceRoll.roll(bot.getDice1());
                rollD2 = DiceRoll.roll(bot.getDice2());
                ScoreCounter.updateScore(bot.getBotScore(), new DiceCard[]{rollD1,rollD2});
                return "Reçu -> "+rollD3.toString()+","+rollD4.toString()+","+rollD1.toString()+","+rollD2.toString();

            case L_ENIGME:
                String res = "Reçu -> ";
                for (int i = 0; i < 4; i++) {
                    rollD1 = DiceRoll.roll(dice);
                    res+=rollD1.toString();
                    ScoreCounter.updateScore(bot.getBotScore(), rollD1);
                }
                return res;

        }
        return null;
    }
}
