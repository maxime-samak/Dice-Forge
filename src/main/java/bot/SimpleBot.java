package bot;

import game.Inventory;
import game.card.AbstractCard;
import game.card.BuyCard;
import game.card.Islands;
import game.dice.Dice;
import game.dice.DiceCard;
import game.dice.Resource;
import game.dice.Sanctuary;
import java.util.ArrayList;
import static game.dice.BuyDiceCard.setCard;

public class SimpleBot extends AbstractBot {

    private Inventory inventory;

    public SimpleBot(Dice d1, Dice d2, String botID) {
        super(d1,d2, botID);
    }

    public void play(Sanctuary sanctuary,Islands islands)
    {
        this.diceShopping(sanctuary);
        this.cardShopping(islands);
    }

    public void diceShopping(Sanctuary sanctuary) {
        int nbBuy=0;
        int gold = this.getBotScore().getGold();
        /*if(goldAvailable >= 12)
            if(shopPoolI(12,sanctuary))
                return true;*/
        if(gold >= 8 && nbBuy<1) { if(diceShopping(sanctuary, 8))nbBuy++; }

        if(gold >= 6 && nbBuy<1) { if(diceShopping(sanctuary,6))nbBuy++; }
        /*if(goldAvailable >= 5)
        {
            if(shopPoolI(5,sanctuary))
                return true;
        }
        if(goldAvailable >= 4)
        {
            if(shopPoolI(4,sanctuary))
                return true;
         }*/
        if(gold >= 3 && nbBuy<1) { if(diceShopping(sanctuary, 3))nbBuy++; }

        if(gold >= 2 && nbBuy<1) { if(diceShopping(sanctuary, 2 ))nbBuy++; }

        if(nbBuy==0) { System.out.println(this.getBotID() + " n'achète pas de face."); }
        if(nbBuy>0) { System.out.println(this.getBotID() + " n'achète plus de face."); }
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
        int dNum =0;
        int f = 0;
        DiceCard oldFace=null;
        for (int i = 0; i <buyable.size(); i++) {

            DiceCard buy = buyable.get(i);

            for (int dice = 1; dice < 3; dice++) {
                for (int face = 1; face <= 6; face++) {
                    DiceCard fd1;
                    fd1 = this.getDice1().getFi(face);

                    if ((fd1.getResource() == buy.getResource() && fd1.getValue() < buy.getValue()) || (fd1.getResource() == Resource.GOLD.resourceName() && fd1.getValue() <= buy.getValue())) {

                        d = this.getDice1();
                        dNum=1;
                        f = face;
                        oldFace=d.getFi(f);
                        break;

                    }
                    fd1 = this.getDice2().getFi(face);
                    if ((fd1.getResource() == buy.getResource() && fd1.getValue() < buy.getValue()) || (fd1.getResource() == Resource.GOLD.resourceName() && fd1.getValue() <= buy.getValue())) {

                        d = this.getDice2();
                        dNum=2;
                        f = face;
                        oldFace=d.getFi(f);
                        break;
                    }
                }
                if (d != null) {
                    break;
                }
            }
            if(d != null) {
                if (setCard(sanctuary,pool,buy,d,f,this.getBotScore())){
                    System.out.println("Le "+this.getBotID()+" a acheter la face "+buy.toString()+" et l'a placé sur le dé "+dNum+" à la face "+f+" ("+oldFace+")");
                    return true; }
            }
        }
        return false;
    }


    private ArrayList<DiceCard> favoriseVictory(ArrayList<DiceCard> buyable)
    {
        int i=0;
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


    private void cardShopping(Islands islands)
    {
        int nbBuy=0;
        int lunar = this.getBotScore().getLunar();
        int solar = this.getBotScore().getSolar();
        if(lunar >= 5 && solar >= 5) { shopIsland(islands,10); nbBuy++; }
        if(lunarShopping(islands)){nbBuy++;}
        if(solarShopping(islands)){nbBuy++;}
        if(nbBuy==0) { System.out.println(this.getBotID() + " n'achète pas de carte."); }
        if(nbBuy>0) { System.out.println(this.getBotID() + " n'achète plus de carte."); }

    }

    private Boolean lunarShopping(Islands islands)
    {
        int lunar = this.getBotScore().getLunar();
        int buyCard=0;
       /* if(lunar >= 6 && buyCard ==0)
        {
            shopIslandLunar(islands,5);
        }
        if(lunar >= 5 && buyCard ==0)
        {
            shopIslandLunar(islands,5);
        }*/
        if(lunar >= 4 && buyCard ==0)
        {
            shopIslandLunar(islands,4);
        }
       /* if(lunar >= 3 && buyCard ==0)
        {
            shopIslandLunar(islands,3);
        }
        if(lunar >= 2 && buyCard ==0)
        {
            shopIslandLunar(islands,2);
        }
        if(lunar >= 1 && buyCard ==0)
        {
            shopIslandLunar(islands,1);
        }*/
        return false;
    }

    private Boolean solarShopping(Islands islands)
    {
        int solar = this.getBotScore().getSolar();
        int buyCard=0;
       /* if(solar >= 6 && buyCard ==0)
        {
            shopIslandSolar(islands,6);
        }
        if(solar >= 5 && buyCard ==0)
        {
            shopIslandSolar(islands,5);
        }*/
        if(solar >= 4 && buyCard ==0)
        {
            shopIslandSolar(islands,4);
        }
       /* if(solar >= 3 && buyCard ==0)
        {
            shopIslandSolar(islands,3);
        }
        if(solar >= 2 && buyCard ==0)
        {
            shopIslandSolar(islands,2);
        }
        if(solar >= 1 && buyCard ==0)
        {
            shopIslandSolar(islands,1);
        }*/
        return false;
    }

    private Boolean shopIsland(Islands islands,int i)
    {
        AbstractCard[] cards = islands.getIslands().get(i);
        for(int cpt=0;cpt<cards.length;cpt++)
        {
            if(BuyCard.buyCard(islands,cards[cpt],this.getBotScore()))
            {
                return true;
            }
        }
        return false;
    }

    private Boolean shopIslandSolar(Islands islands,int i)
    {
        ArrayList<AbstractCard> cards = islands.getIslandAvailables(i);
        for(int cpt=0;cpt<cards.size();cpt++)
        {
            if(cards.get(cpt).getPrice()[0]==i)
            {
                if(BuyCard.buyCard(islands,cards.get(cpt),this.getBotScore())==true)
                {
                    System.out.println("Le "+this.getBotID()+" a acheté une carte à "+i+" Solar et à gagner "+cards.get(cpt).getVictory()+" Victory");
                    return true;
                }
            }
        }
        return false;
    }

    private Boolean shopIslandLunar(Islands islands,int i)
    {
        ArrayList<AbstractCard> cards = islands.getIslandAvailables(i);
        for(int cpt=0;cpt<cards.size();cpt++)
        {
            if(cards.get(cpt).getPrice()[1]==i)
            {
                if(BuyCard.buyCard(islands,cards.get(cpt),this.getBotScore())==true)
                {
                    System.out.println("Le "+this.getBotID()+" a acheté une carte à "+i+" Lunar et à gagner "+cards.get(cpt).getVictory()+" Victory");
                    return true;
                }
            }
        }
        return false;
    }

}