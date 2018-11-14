package game.card;

public abstract class AbstractCard implements Card {

    private final int[] price = new int[2];
    private final int victory;
    private final Effect type;

    public AbstractCard(int victory, Effect type, int solarCost, int lunarCost) {
        this.victory = victory;
        this.type = type;
        this.price[0] = solarCost;
        this.price[1] = lunarCost;
    }

    public boolean equals(AbstractCard card) {
        if (this.price[1] == card.getPrice()[1] && this.price[0] == card.getPrice()[0] && this.victory == card.getVictory()) {
            return true;
        }

        else { return false; }
    }

    public abstract void getEffect();

    public int[] getPrice(){
        return price;
    }

    public int getVictory() {
        return victory;
    }

}