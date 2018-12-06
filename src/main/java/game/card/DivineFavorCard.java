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

    public void getEffect(AbstractBot bot) {

        Dice dice = bot.getDice1(); // getPreferedDice()
        DiceCard rollD1;
        DiceCard rollD2;

        switch (getName()) {
            case LES_SABOTS_D_ARGENT:
                rollD1 = DiceRoll.roll(dice);
                ScoreCounter.updateScore(bot.getBotScore(), rollD1);
                break;

            case LA_PINCE:
                rollD1 = DiceRoll.roll(bot.getDice1());
                rollD2 = DiceRoll.roll(bot.getDice2());
                ScoreCounter.updateScore(bot.getBotScore(), new DiceCard[]{rollD1,rollD2});

                rollD1 = DiceRoll.roll(bot.getDice1());
                rollD2 = DiceRoll.roll(bot.getDice2());
                ScoreCounter.updateScore(bot.getBotScore(), new DiceCard[]{rollD1,rollD2});
                break;

            case L_ENIGME:
                for (int i = 0; i < 4; i++) {
                    rollD1 = DiceRoll.roll(dice);
                    ScoreCounter.updateScore(bot.getBotScore(), rollD1);
                }
                break;


        }
    }
}
