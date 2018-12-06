package bot;

import game.card.AbstractCard;
import game.card.BuyCard;
import game.card.Card;
import game.card.Islands;
import game.dice.*;
import java.util.ArrayList;
import static game.dice.BuyDiceCard.setCard;

/**
 * Classe SimpleBot représente notre stratégie initiale
 */
public class SimpleBot extends AbstractBot {

    /**
     * Constructeur de SimpleBot
     */
    public SimpleBot(Dice d1, Dice d2, String botID) {
        super(d1,d2, botID);
    }

    @Override
    public void play(Sanctuary sanctuary,Islands islands) {
        buyInOrder(sanctuary,islands,false);
    }

    /**
     * Cette méthode choisit si le bot veut acheter une carte ou une face de dés
     */
    public void buyInOrder(Sanctuary sanctuary,Islands islands,Boolean secondBuy) {

        int gold = this.getBotScore().getGold();
        int solar = this.getBotScore().getSolar();
        int lunar = this.getBotScore().getLunar();

        if(secondBuy)
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
                Boolean buy = this.diceShopping(sanctuary);
                if (buy) {
                    if (solar >= 3 || (solar >= 2 && lunar >= 1)) {
                        this.cardShopping(islands);
                    }
                }
                else {
                    this.cardShopping(islands);
                }
            }
            else {
                Boolean buy = this.cardShopping(islands);
                if (buy) {
                   buyInOrder(sanctuary,islands,true);
                }
                else {
                    this.diceShopping(sanctuary);
                }
            }
        }


    }

    public Boolean diceShopping(Sanctuary sanctuary) {
        int nbBuy = 0;
        int gold = this.getBotScore().getGold();
        int[] pools = new int[]{12,8,6,5,4,3,2};

        for(int i=0;i<pools.length;i++)
        {
            if(gold>=pools[i] && nbBuy <=1)
            {
                if(diceShopping(sanctuary,pools[i])){nbBuy++;}
            }
        }

        if(nbBuy == 0) {return false;}

        else{return true;}
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
        buyable = this.favoriseVictory(buyable);
        Dice d = null;
        int f = 0;

        for (int i = 0; i < buyable.size(); i++) {
            DiceCard buy = buyable.get(i);

            for (int dice = 1; dice < 3; dice++) {
                for (int face = 1; face <= 6; face++) {
                    DiceCard fd1;
                    fd1 = this.getDice1().getFi(face);

                    if ((fd1.getResource() == buy.getResource() && fd1.getValue() < buy.getValue()) || fd1.getResource() == Resource.GOLD.resourceName() && fd1.getValue() == 1) {

                        d = this.getDice1();
                        f = face;
                        break;

                    }
                    fd1 = this.getDice2().getFi(face);
                    if ((fd1.getResource() == buy.getResource() && fd1.getValue() < buy.getValue()) || fd1.getResource() == Resource.GOLD.resourceName() && fd1.getValue() == 1) {

                        d = this.getDice2();
                        f = face;
                        break;
                    }
                }
                if (d != null) {
                    break;
                }
            }
            if(d != null) {
                if (setCard(sanctuary, pool, buy, d, f, this.getBotScore())){ return true; }
            }
        }
        return false;
    }

    /**
     * Le bot achète des faces de dés avec en priorité celles avec des points de victoire
     */
    private ArrayList<DiceCard> favoriseVictory(ArrayList<DiceCard> buyable)
    {
        int i = 0;
        ArrayList<Integer> listVictory= new ArrayList<>();
        ArrayList<DiceCard> newBuy= new ArrayList<>();
        for(int cpt=0;cpt<buyable.size();cpt++)
        {
            if(buyable.get(cpt).getResource()==Resource.VICTORY.resourceName())
            {
                listVictory.add(1);
                i++;
            }
            else
            {
                listVictory.add(0);
            }
        }
        for(int cpt2=0;cpt2<listVictory.size();cpt2++)
        {
           if(listVictory.get(cpt2)==1)
           {
               newBuy.add(buyable.get(cpt2));
               i--;
           }
           if(i==0)
           {
               break;
           }
        }
        for(int cpt3=0;cpt3<listVictory.size();cpt3++)
        {
            if(listVictory.get(cpt3)==0)
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

    protected Boolean cardShopping(Islands islands) {
        int nbBuy = 0;
        int lunar = this.getBotScore().getLunar();
        int solar = this.getBotScore().getSolar();
        int solarFee = 0;
        if(BuyDiceCard.getBought().size() > 0 || BuyCard.getBought().size() > 0){ solarFee = 2;}
        if(!(islands.getIslandAvailables(10).isEmpty()) && lunar >= 5 && solar >= 5 + solarFee) { if(shopIslandTen(islands)){nbBuy++;}}
        if(nbBuy == 0) {
            if(lunar <= solar) {
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

    protected Boolean lunarShopping(Islands islands) {
        int lunar = this.getBotScore().getLunar();
        int solar = this.getBotScore().getSolar();
        int solarFee = 0;
        if(BuyDiceCard.getBought().size() > 0 || BuyCard.getBought().size() > 0){ solarFee = 2;}

        for(int i=6;i>0;i--)
        {
            if(lunar >= i && solar>=solarFee && !(islands.getIslandAvailables(i).isEmpty()))
            {
                if(shopIslandLunar(islands,i)){return true;}
            }
        }

        return false;
    }

    protected Boolean solarShopping(Islands islands)
    {
        int solar = this.getBotScore().getSolar();

        int solarFee = 0;
        if(BuyDiceCard.getBought().size() > 0 || BuyCard.getBought().size() > 0){ solarFee = 2;}

        for(int i=6;i>0;i--)
        {
            if(solar >=i+solarFee && !(islands.getIslandAvailables(i).isEmpty())) {
                if(shopIslandSolar(islands,i)){return true;}
            }
        }

        return false;
    }

    protected Boolean shopIslandTen(Islands islands) {
        ArrayList<AbstractCard> cards = islands.getIslandAvailables(10);
        for(int cpt = 0; cpt < cards.size(); cpt++)
        {
            if(BuyCard.buyCard(this, islands,cards.get(cpt))) {
                return true;
            }
        }
        return false;
    }

    private Boolean shopIslandSolar(Islands islands, int i) {
        ArrayList<AbstractCard> cards = islands.getIslandAvailables(i);
        for(int cpt = 0; cpt < cards.size(); cpt++) {
            if(cards.get(cpt).getPrice()[0] == i) {
                if(BuyCard.buyCard(this, islands,cards.get(cpt))) { return true; }
            }
        }
        return false;
    }

    private Boolean shopIslandLunar(Islands islands,int i) {
        ArrayList<AbstractCard> cards = islands.getIslandAvailables(i);
        for(int cpt = 0; cpt < cards.size(); cpt++) {
            if(cards.get(cpt).getPrice()[1] == i) {
                if(BuyCard.buyCard(this, islands,cards.get(cpt))) { return true; }
            }
        }
        return false;
    }

    @Override
    public Resource getPreferedResource() {
        return Resource.VICTORY;
    }

    public DiceCard choose(DiceCard d) {
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
                if(this.getBotScore().getGold()<=this.getBotScore().getSolar()&&this.getBotScore().getGold()<=this.getBotScore().getLunar())
                    i=1;
                else if(this.getBotScore().getSolar()<this.getBotScore().getGold()&&this.getBotScore().getSolar()<=this.getBotScore().getLunar())
                    i=2;
                else
                    i=3;

                return new DiceCard(values[i],resources[i]);
            }
        }
    }
}
