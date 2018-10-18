package bot;

import objects.Dice;

public abstract class Bot implements InterfaceBot {

    private Dice dice1;
    private Dice dice2;

    public Bot(Dice d1,Dice d2)
    {
        this.dice1=d1;
        this.dice2=d2;
    }

    public void rollDices()
    {
        double d1 = Math.random()*6;
        double d2 = Math.random()*6;

        this.dice1.showFace((int)d1);
        this.dice2.showFace((int)d2);
    }

}
