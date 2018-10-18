package bot;

import objects.Dice;

public class SimpleBot extends Bot {

    public SimpleBot(Dice d1, Dice d2)
    {
        super(d1,d2);
    }

    public void SimpleStrat()
    {
        this.rollDices();
    }

}
