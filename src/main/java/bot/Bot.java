package bot;

import game.dice.Dice;

/**
 * Classe Bot implémente ce que le bot doit pouvoir faire
 */
public interface Bot {
    String getBotID();
    Dice getDice1();
    Dice getDice2();

    /*
     * ajouter play()
     */

}

