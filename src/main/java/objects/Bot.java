package objects;

public abstract class Bot implements InterfaceBot{

    private Dice dice1;
    private Dice dice2;

    public void initBot()
    {
        this.dice1 = new Dice(1);
        this.dice2 = new Dice(2);
    }

    public void rollDices()
    {
        double d1 = Math.random()*6;
        double d2 = Math.random()*6;

        this.dice1.showFace((int)d1);
        this.dice2.showFace((int)d2);
    }

}
