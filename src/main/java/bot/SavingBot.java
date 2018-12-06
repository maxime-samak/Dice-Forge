package bot;

import game.card.AbstractCard;
import game.card.BuyCard;
import game.card.Card;
import game.card.Islands;
import game.dice.*;

import java.util.ArrayList;
import static game.dice.BuyDiceCard.setCard;
import static game.dice.Resource.GOLD;
import static game.dice.Resource.LUNAR;
import static game.dice.Resource.SOLAR;

/**
 * Classe SimpleBot représente notre stratégie initiale
 */
public class SavingBot extends AbstractBot {


    public SavingBot(Dice d1, Dice d2, String botID) {
        super(d1,d2, botID);
    }

    @Override
    public void play(Sanctuary sanctuary,Islands islands) {
        buyInOrder(sanctuary,islands);
    }

    public void buyInOrder(Sanctuary sanctuary,Islands islands) {

        int gold = this.getBotScore().getGold();
        int solar = this.getBotScore().getSolar();
        int lunar = this.getBotScore().getLunar();

        if(BuyDiceCard.getBought().size()==0 && BuyCard.getBought().size()>0)
        {
            if((gold > lunar && gold > solar)) {
                this.diceShopping(sanctuary);
            }
            else {
                this.cardShopping(islands);
            }
        }
        else {
            if ((gold > lunar && gold > solar)) {
                if (this.diceShopping(sanctuary)) {
                    if (solar >= 3 || (solar >= 2 && lunar >= 1)) {
                        this.cardShopping(islands);
                    }
                }
                else {
                    this.cardShopping(islands);
                }
            }
            else {
                if (this.cardShopping(islands)){
                    buyInOrder(sanctuary,islands);
                }
                else {
                    this.diceShopping(sanctuary);
                }
            }
        }


    }

    public Boolean diceShopping(Sanctuary sanctuary) {
        int gold = this.getBotScore().getGold();
        int[] pools = new int[]{12,8,6,5,4,3,2};

        for(int i=0;i<pools.length;i++)
        {
            if(!(sanctuary.getPoolAvailables(pools[i]).isEmpty())) {
                if (gold >= pools[i])
                {
                    if (diceShopping(sanctuary, pools[i]))
                        return true;
                }
                break;
            }
        }
        return false;
    }

    /**
     * Le bot va vérifier les faces disponibles dans la pool passée en paramètre, il va ensuite vérifier, pour chaque face qu'il peut acheter,
     * si il possède un emplacement sur un de ses deux dés où il est intéréssant de placer cette nouvelle face. Si oui il achète la face et la remplace,
     * si non alors il n'achète rien et la méthode renvois false.
     *
     * @param sanctuary
     * @param pool
     * @return
     */
    public boolean diceShopping(Sanctuary sanctuary, int pool) {
        ArrayList<DiceCard> buyable = sanctuary.getPoolAvailables(pool);
        buyable = this.favoriseSolLun(buyable);
        Dice d = null;
        int f = 0;

        for (int i = 0; i < buyable.size(); i++) {
            DiceCard buy = buyable.get(i);

                for (int face = 1; face <= 6; face++) {
                    DiceCard fd1;
                    fd1 = this.getDice1().getFi(face);

                    if ((fd1.getResource() == buy.getResource() && fd1.getValue() < buy.getValue()-1) || fd1.getResource() == GOLD.resourceName() && fd1.getValue() == 1) {

                        d = this.getDice1();
                        f = face;
                        break;

                    }
                    fd1 = this.getDice2().getFi(face);
                    if ((fd1.getResource() == buy.getResource() && fd1.getValue() < buy.getValue()-1) || fd1.getResource() == GOLD.resourceName() && fd1.getValue() == 1) {

                        d = this.getDice2();
                        f = face;
                        break;
                    }
            }
            if(d != null) {
                if (setCard(sanctuary, pool, buy, d, f, this.getBotScore())){ return true; }
            }
        }
        return false;
    }


    private ArrayList<DiceCard> favoriseSolLun(ArrayList<DiceCard> buyable)
    {
        int i = 0;
        ArrayList<Integer> listSolLun= new ArrayList<>();
        ArrayList<DiceCard> newBuy= new ArrayList<>();
        for(int cpt=0;cpt<buyable.size();cpt++)
        {
            if(buyable.get(cpt).getResource()== SOLAR.resourceName() || buyable.get(cpt).getResource()== SOLAR.resourceName() )
            {
                listSolLun.add(1);
                i++;
            }
            else
            {
                listSolLun.add(0);
            }
        }
        for(int cpt2=0;cpt2<listSolLun.size();cpt2++)
        {
            if(listSolLun.get(cpt2)==1)
            {
                i--;
                newBuy.add(buyable.get(cpt2));
            }
            if(i==0)
            {
                break;
            }
        }
        for(int cpt3=0;cpt3<listSolLun.size();cpt3++)
        {
            if(listSolLun.get(cpt3)==0)
            {
                newBuy.add(buyable.get(cpt3));
            }
        }


        return newBuy;
    }

    /**public void cheat() {
     getBotScore().addSolar(9);

     ScoreCounter.updateScore(getBotScore(), "12@GOLD%12@GOLD");


     }**/

    private Boolean cardShopping(Islands islands) {
        int nbBuy = 0;
        int lunar = this.getBotScore().getLunar();
        int solar = this.getBotScore().getSolar();
        int solarFee = 0;
        if(BuyDiceCard.getBought().size() > 0 || BuyCard.getBought().size() > 0){ solarFee = 2;}
        if(!(islands.getIslandAvailables(10).isEmpty()) && lunar >= 5 && solar >= 5 + solarFee) { if(shopIslandTen(islands)){nbBuy++;}}
        if(nbBuy == 0) {
            if(solar >= lunar) {
                if (solarShopping(islands)) {
                    return true;
                }
                else {
                    if (lunarShopping(islands)) {
                        return true;
                    }
                }
            }
            else {
                if (lunarShopping(islands)) {
                    return true;
                }
                else {
                    if (solarShopping(islands))
                    {
                        return true;
                    }
                }
            }

        }
        return false;

    }

    private Boolean lunarShopping(Islands islands) {
        int lunar = this.getBotScore().getLunar();
        int solar = this.getBotScore().getSolar();
        int solarFee = 0;
        if(BuyDiceCard.getBought().size() > 0 || BuyCard.getBought().size() > 0){ solarFee = 2;}

        for(int i=6;i>0;i--)
        {
            if(!(islands.getIslandAvailables(i).isEmpty()))
            {
                if(lunar >= i && solar>=solarFee){
                    if (shopIslandLunar(islands, i))
                        return true;
                }
                break;
            }
        }

        return false;
    }

    private Boolean solarShopping(Islands islands)
    {
        int solar = this.getBotScore().getSolar();

        int solarFee = 0;
        if(BuyDiceCard.getBought().size() > 0 || BuyCard.getBought().size() > 0){ solarFee = 2;}

        for(int i=6;i>0;i--)
        {
            if(!(islands.getIslandAvailables(i).isEmpty()))
            {
                if(solar >=i+solarFee){
                    if (shopIslandSolar(islands, i))
                        return true;
                }
                break;
            }
        }
        return false;
    }

    private Boolean shopIslandTen(Islands islands) {
        ArrayList<AbstractCard> cards = islands.getIslandAvailables(10);
        for(int cpt=0;cpt<cards.size();cpt++) {
            if(BuyCard.buyCard(this, islands,cards.get(cpt))) {
                return true;
            }
        }
        return false;
    }

    private Boolean shopIslandSolar(Islands islands,int i) {
        ArrayList<AbstractCard> cards = islands.getIslandAvailables(i);
        for (int cpt = 0; cpt < cards.size(); cpt++) {
            if (cards.get(cpt).getPrice()[0] == i) {
                if (BuyCard.buyCard(this, islands, cards.get(cpt))) {
                    return true;
                }
            }
        }
        return false;
    }

    private Boolean shopIslandLunar(Islands islands,int i) {
        ArrayList<AbstractCard> cards = islands.getIslandAvailables(i);
        for(int cpt=0; cpt<cards.size(); cpt++) {
            if(cards.get(cpt).getPrice()[1]==i) {
                if(BuyCard.buyCard(this, islands,cards.get(cpt))) { return true; }
            }
        }
        return false;
    }


    @Override
    public Resource getPreferedResource() {
        int solar=this.getBotScore().getSolar();
        int gold=this.getBotScore().getGold();
        int lunar=this.getBotScore().getLunar();
        if(solar<=lunar&&solar<=gold)
            return SOLAR;
        else if(lunar<solar&&lunar<=gold)
            return LUNAR;
        else
            return GOLD; //choix par défaut arbitraire
    }

    public DiceCard choose(DiceCard d)
    {
        if(d.getResource()!=Resource.CHOICE.resourceName())
        {
            return d;
        }
        else
        {
            int i;
            Resource[] resources = d.getResourceArray();
            int[] values = d.getValueArray();

            if(resources[1].resourceName()==Resource.VICTORY.resourceName())
                return new DiceCard(values[1],resources[1]);
            else
            {
                if(getPreferedResource()==GOLD)
                    i = 1;
                else if(getPreferedResource()==SOLAR)
                    i = 2;
                else if(getPreferedResource()==LUNAR)
                    i = 3;
                else
                    i = 0;
            }
                return new DiceCard(values[i],resources[i]);
        }
    }
}