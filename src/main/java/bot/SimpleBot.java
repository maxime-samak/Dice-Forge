package bot;

import objects.Dice;

public class SimpleBot extends AbstractBot {

    public SimpleBot(Dice d1, Dice d2,String name)
    {
        super(d1,d2,name);
    }

    public String SimpleStrat()
    {
       return this.rollDices();
    }

}