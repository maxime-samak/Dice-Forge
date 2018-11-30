package bot;

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


    public SimpleBot(Dice d1, Dice d2, String botID) {
        super(d1,d2, botID);
    }

    public void play(Sanctuary sanctuary,Islands islands) {
        buyInOrder(sanctuary,islands,false);
    }

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

        if(gold >= 12 && nbBuy < 1) { if(diceShopping(sanctuary,12)) nbBuy++; }

        if(gold >= 8 && nbBuy < 1) { if(diceShopping(sanctuary, 8)) nbBuy++; }

        if(gold >= 6 && nbBuy < 1) { if(diceShopping(sanctuary,6)) nbBuy++; }

        if(gold >= 5 && nbBuy < 1) { if(diceShopping(sanctuary,5)) nbBuy++; }

        if(gold >= 4 && nbBuy < 1) { if(diceShopping(sanctuary,4)) nbBuy++; }

        if(gold >= 3 && nbBuy < 1) { if(diceShopping(sanctuary, 3)) nbBuy++; }

        if(gold >= 2 && nbBuy < 1) { if(diceShopping(sanctuary, 2)) nbBuy++; }

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


    private Boolean cardShopping(Islands islands) {
        int nbBuy = 0;
        int lunar = this.getBotScore().getLunar();
        int solar = this.getBotScore().getSolar();
        int solarFee = 0;
        if(BuyDiceCard.getBought().size() > 0 || BuyCard.getBought().size() > 0){ solarFee = 2;}
        if(lunar >= 5 && solar >= 5 + solarFee) { shopIsland(islands,10); nbBuy++; }
        if(nbBuy == 0) {
            if(lunar <= solar) {
                if (solarShopping(islands)) {
                    nbBuy++;
                    return true;
                }
                else {
                    if (lunarShopping(islands)) {
                        nbBuy++;
                        return true;
                    }
                }
            }
            else {
                if (lunarShopping(islands)) {
                    nbBuy++;
                    return true;
                }
                else {
                    if (solarShopping(islands))
                    {
                        nbBuy++;
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
        int buyCard=0;
        int solarFee = 0;
        if(BuyDiceCard.getBought().size() > 0 || BuyCard.getBought().size() > 0){ solarFee = 2;}
       /* if(lunar >= 6 && buyCard ==0 && solar>=solarFee)
        {
            if(shopIslandLunar(islands,6)){return true;}
        }
        if(lunar >= 5 && buyCard ==0 && solar>=solarFee)
        {
            if(shopIslandLunar(islands,5)){return true;}
        }*/
        if(lunar >= 4 && buyCard ==0 && solar>=solarFee)
        {
            if(shopIslandLunar(islands,4)){return true;}
        }
       /* if(lunar >= 3 && buyCard ==0 && solar>=solarFee)
        {
            if(shopIslandLunar(islands,3)){return true;}
        }*/
        if(lunar >= 2 && buyCard ==0 && solar>=solarFee)
        {
            if(shopIslandLunar(islands,2)){return true;}
        }
        if(lunar >= 1 && buyCard ==0 && solar>=solarFee)
        {
            if(shopIslandLunar(islands,1)){return true;}
        }
        return false;
    }

    private Boolean solarShopping(Islands islands)
    {
        int solar = this.getBotScore().getSolar();
        int buyCard=0;
        int solarFee = 0;
        if(BuyDiceCard.getBought().size() > 0 || BuyCard.getBought().size() > 0){ solarFee = 2;}
       /* if(solar >= 6+solarFee && buyCard ==0)
        {
            if(shopIslandSolar(islands,6)){return true;}
        }
        if(solar >= 5+solarFee && buyCard ==0)
        {
            if(shopIslandSolar(islands,5)){return true;}
        }*/
        if(solar >= 4+solarFee && buyCard ==0)
        {
            if(shopIslandSolar(islands,4)){return true;}
        }
       /* if(solar >= 3+solarFee && buyCard ==0)
        {
            if(shopIslandSolar(islands,3)){return true;}
        }*/
        if(solar >= 2+solarFee && buyCard ==0)
        {
            if(shopIslandSolar(islands,2)){return true;}
        }
        if(solar >= 1+solarFee && buyCard ==0)
        {
            if(shopIslandSolar(islands,1)){return true;}
        }
        return false;
    }

    private Boolean shopIsland(Islands islands,int i)
    {
        Card[] cards = islands.getIslands().get(i);
        for(int cpt=0;cpt<cards.length;cpt++)
        {
            if(BuyCard.buyCard(islands,cards[cpt],this.getBotScore(), this))
            {
                return true;
            }
        }
        return false;
    }

    private Boolean shopIslandSolar(Islands islands,int i)
    {
        ArrayList<Card> cards = islands.getIslandAvailables(i);
        for(int cpt=0;cpt<cards.size();cpt++)
        {
            if(cards.get(cpt).getPrice()[0]==i)
            {
                if(BuyCard.buyCard(islands,cards.get(cpt),this.getBotScore(),this)==true) { return true; }
            }
        }
        return false;
    }

    private Boolean shopIslandLunar(Islands islands,int i)
    {
        ArrayList<Card> cards = islands.getIslandAvailables(i);
        for(int cpt=0;cpt<cards.size();cpt++)
        {
            if(cards.get(cpt).getPrice()[1]==i)
            {
                if(BuyCard.buyCard(islands,cards.get(cpt),this.getBotScore(), this)==true) { return true; }
            }
        }
        return false;
    }

    public String strategyCard(Card card){
        switch (card) {
            case L_ANCIEN:
                return "Yes";
            case LES_AILES_DE_LA_GARDIENNES:
                if (this.getBotScore().getSolar() > this.getBotScore().getLunar()) {
                    return "Lunar";
                } else {
                    return "Solar";
                }
            case LES_SABOTS_D_ARGENT:
                return "dice1";
            default: return "Unknown";
        }
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
                if(this.getBotScore().getGold()<=this.getBotScore().getSolar()&&this.getBotScore().getGold()<=this.getBotScore().getLunar())
                    i=1;
                if(this.getBotScore().getSolar()<this.getBotScore().getGold()&&this.getBotScore().getSolar()<=this.getBotScore().getLunar())
                    i=2;
                else
                    i=3;

                return new DiceCard(values[i],resources[i]);
            }
        }
    }
}
