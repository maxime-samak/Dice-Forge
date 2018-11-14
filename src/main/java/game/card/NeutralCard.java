package game.card;

public class NeutralCard extends AbstractCard {

    public NeutralCard(int victory, Effect type, int solarPrice, int lunarPrice, boolean advidseCard){
        super(victory, type, solarPrice, lunarPrice, advidseCard);
    }

    public void getEffect(){}

}
