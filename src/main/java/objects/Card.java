package objects;

public class Card extends AbstractCard {

    public Card(int pointGloire, String type, Object[] coutCarte, boolean carteConseiller){
        super(pointGloire, type, coutCarte, carteConseiller);
    }

    public void effetCarte(){};

    public Card[] cardInit(){
        Card[] Deck = new Card[12];
        Object[] coutCarte = {4, Resource.LUNAR};

        Deck[0] = new Card(12, "", coutCarte, true);
        Deck[1] = Deck[0];
        Deck[2] = Deck[0];
        Deck[3] = Deck[0];

        Deck[4] = new Card(14, "", coutCarte, true);
        Deck[5] = Deck[4];
        Deck[6] = Deck[4];
        Deck[7] = Deck[4];

        Object[][] coutCarte2 = {{4, Resource.LUNAR},{4, Resource.SOLAR}};
        Deck[8] = new Card(26, "", coutCarte2, true);
        Deck[9] = Deck[8];
        Deck[10] = Deck[8];
        Deck[11] = Deck[8];

        return Deck;
    }
}
