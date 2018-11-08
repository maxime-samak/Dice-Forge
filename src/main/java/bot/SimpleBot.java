package bot;

import objects.Dice;

public class SimpleBot extends AbstractBot {

    public SimpleBot(Dice d1, Dice d2, String botID) {
        super(d1,d2, botID);
    }

    public String SimpleStrat()
    {
       return this.rollDices();
    }

}