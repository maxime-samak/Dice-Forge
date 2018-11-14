package game.card;

public abstract class AbstractCard implements Card {

    private final int[] price = new int[2];
    private final int victory;
    private Effect type;
    private final boolean advidseCard;

    public AbstractCard(int victory, Effect type, int solarCost, int lunarCost, boolean advidseCard) {
        this.victory = victory;
        this.type = type;
        this.price[0] = solarCost;
        this.price[1] = lunarCost;
        this.advidseCard = advidseCard;
    }

    public abstract void getEffect();

    public int[] getPrice(){
        return price;
    }

    public int getVictory() {
        return victory;
    }

}