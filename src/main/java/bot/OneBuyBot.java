package bot;

import game.card.BuyCard;
import game.card.Inventory;
import game.card.Islands;
import game.dice.*;

import java.util.ArrayList;

import static game.dice.BuyDiceCard.setCard;


public class OneBuyBot extends AbstractBot {

    /**
     * Constructeur SimpleBot
     * @param d1 son premier dé
     * @param d2 son second dé
     * @param botID son identification
     * @param color sa couleur d'écriture
     */
    public OneBuyBot(Dice d1, Dice d2, String botID, String color) {
        super(d1,d2, botID,color);
    }

    @Override
    public void play(Sanctuary sanctuary, Islands islands, Inventory inventory) { buyInOrder(sanctuary,islands,false,inventory); }

    /**
     * Cette méthode choisit si le bot veut acheter une carte ou une face de dés
     */
    public void buyInOrder(Sanctuary sanctuary,Islands islands,Boolean secondBuy,Inventory inventory) {

            if ((this.getBotScore().getGold() > this.getBotScore().getLunar() && this.getBotScore().getGold() > this.getBotScore().getSolar())) {
                if(!this.diceShopping(sanctuary))
                    this.cardShopping(islands,inventory);
            }
            else {
                if(!this.cardShopping(islands,inventory))
                    this.diceShopping(sanctuary);
            }
        }

    public Boolean diceShopping(Sanctuary sanctuary) {
        int nbBuy = 0;
        int[] pools = new int[]{12,8,6,5,4,3,2};

        for(int i=0;i<pools.length;i++)
        {
            if(this.getBotScore().getGold()>=pools[i] && nbBuy<1)
            {
                if(diceShopping(sanctuary,pools[i])){nbBuy++;}
            }
        }

        if(nbBuy == 0) {return false;}

        else{return true;}
    }

    public boolean diceShopping(Sanctuary sanctuary, int pool) {
        ArrayList<DiceCard> buyable = sanctuary.getPoolAvailables(pool);
        buyable = this.favoriseVictory(buyable);
        Dice d = null;
        int f = 0;
        if(BuyDiceCard.getBought().size()>0 || BuyCard.getBought().size()>0)
            return false;

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

    protected Boolean cardShopping(Islands islands,Inventory inventory) {
        int nbBuy = 0;
        int solarFee = 0;
        if(BuyDiceCard.getBought().size() > 0 || BuyCard.getBought().size() > 0){ return false;}
        if(!(islands.getIslandAvailables(10).isEmpty()) && this.getBotScore().getLunar() >= 5 && this.getBotScore().getSolar() >= 5 + solarFee) { if(shopIslandTen(islands,inventory)){nbBuy++;}}
        if(nbBuy == 0) {
            if(this.getBotScore().getLunar() < this.getBotScore().getSolar()) {
                if (solarShopping(islands,inventory)) {
                    return true;
                }
                else {
                    if (lunarShopping(islands,inventory)) {
                        return true;
                    }
                }
            }
            else {
                if (lunarShopping(islands,inventory)) {
                    return true;
                }
                else {
                    if (solarShopping(islands,inventory))
                    {
                        return true;
                    }
                }
            }

        }
        return false;

    }


    /**public void cheat() {
     getBotScore().addSolar(9);

     ScoreCounter.updateScore(getBotScore(), "12@GOLD%12@GOLD");


     }**/



}
