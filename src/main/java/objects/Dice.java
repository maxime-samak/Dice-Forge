package objects;

public class Dice {

    private DiceCard F1;
    private DiceCard F2;
    private DiceCard F3;
    private DiceCard F4;
    private DiceCard F5;
    private DiceCard F6;

    public Dice() {}

    public void solarDiceInit() {
        F1.setResource(Resource.solar);
        F2.setResource(Resource.gold);
        F3.setResource(Resource.gold);
        F4.setResource(Resource.gold);
        F5.setResource(Resource.gold);
        F6.setResource(Resource.gold);

        F1.setValue(1);
        F2.setValue(1);
        F3.setValue(1);
        F4.setValue(1);
        F5.setValue(1);
        F6.setValue(1);
    }

    public void lunarDiceInit() {
        F1.setResource(Resource.lunar);
        F2.setResource(Resource.victory);
        F3.setResource(Resource.gold);
        F4.setResource(Resource.gold);
        F5.setResource(Resource.gold);
        F6.setResource(Resource.gold);

        F1.setValue(1);
        F2.setValue(2);
        F3.setValue(1);
        F4.setValue(1);
        F5.setValue(1);
        F6.setValue(1);
    }

}
