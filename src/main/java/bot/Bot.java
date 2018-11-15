package bot;

import game.dice.Dice;

public interface Bot {
    String getBotID();
    Dice getDice1();
    Dice getDice2();
}

