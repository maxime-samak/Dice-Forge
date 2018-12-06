package bot;

import game.card.newcard.Islands;
import game.dice.Dice;
import game.dice.Sanctuary;

/**
 * Classe Bot implémente ce que le bot doit pouvoir faire
 */
public interface Bot {

    String getBotID();

    Dice getDice1();

    Dice getDice2();

    //void play(Sanctuary sanctuary, Islands islands);

}

