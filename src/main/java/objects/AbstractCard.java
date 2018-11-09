package objects;

public abstract class AbstractCard implements InterfaceCard{

    private final int victoryPoints;
    private String type; // A optimiser : changer surement en enum(effet immédiat, et deux autres types d'effet à durée permanente
    private final Object[][] priceCard;
    private final boolean advidseCard;

    public AbstractCard(int victoryPoints, String type, Object[][] priceCard, boolean advidseCard) {
        this.victoryPoints = victoryPoints;
        this.type = type;
        this.priceCard = priceCard;
        this.advidseCard = advidseCard;
    }

    public abstract void cardEffect();

    Object[][] getPriceCard(){
        return priceCard;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }
}