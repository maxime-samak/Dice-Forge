package bot;

import game.card.Card;
import game.dice.Dice;
import game.dice.DiceCard;

/**
 * Classe Bot implémente ce que le bot doit pouvoir faire
 */
public interface Bot {

    String getBotID();
    Dice getDice1();
    Dice getDice2();

    int choose(DiceCard d);

    //void play(Sanctuary sanctuary, Islands islands);

}

