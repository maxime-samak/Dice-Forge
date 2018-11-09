package objects;

public class Card extends AbstractCard {

    public Card(int victoryPoints, String type, Object[][] priceCard, boolean advidseCard){
        super(victoryPoints, type, priceCard, advidseCard);
    }

    public void cardEffect(){}

    public Card[] deckInit(){
        Card[] Deck = new Card[12];
        Object[][] priceCard = {{0, Resource.SOLAR},{4, Resource.LUNAR}};

        Deck[0] = new Card(12, "", priceCard, true);
        Deck[1] = Deck[0];
        Deck[2] = Deck[0];
        Deck[3] = Deck[0];

        Deck[4] = new Card(14, "", priceCard, true);
        Deck[5] = Deck[4];
        Deck[6] = Deck[4];
        Deck[7] = Deck[4];

        Object[][] priceCard2 = {{4, Resource.SOLAR}, {4, Resource.LUNAR}};
        Deck[8] = new Card(26, "", priceCard2, true);
        Deck[9] = Deck[8];
        Deck[10] = Deck[8];
        Deck[11] = Deck[8];

        return Deck;
    }
}
