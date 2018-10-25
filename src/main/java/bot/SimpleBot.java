package bot;

import objects.Dice;

public class SimpleBot extends AbstractBot {

    public SimpleBot(Dice d1, Dice d2)
    {
        super(d1,d2);
    }

    public String SimpleStrat()
    {
       return this.rollDices();
    }

}