package bot;

import game.card.*;
import game.dice.*;


/**
 * Classe SimpleBot représente notre stratégie initiale
 */
public class SimpleBot extends AbstractBot {

    /**
     * Constructeur SimpleBot
     * @param d1 son premier dé
     * @param d2 son second dé
     * @param botID son identification
     * @param color sa couleur d'écriture
     */
    public SimpleBot(Dice d1, Dice d2, String botID,String color) {
        super(d1,d2, botID,color);
    }

    @Override
    public void play(Sanctuary sanctuary, Islands islands, Inventory inventory) { buyInOrder(sanctuary,islands,false,inventory); }

    /**
     * Cette méthode choisit si le bot veut acheter une carte ou une face de dés
     */
    public void buyInOrder(Sanctuary sanctuary,Islands islands,Boolean secondBuy,Inventory inventory) {

        if(secondBuy)
        {
            if((this.getBotScore().getGold() > this.getBotScore().getLunar() && this.getBotScore().getGold() > this.getBotScore().getSolar())) {
                this.diceShopping(sanctuary);
            }
            else {
                this.cardShopping(islands,inventory);
            }
        }
        else {
            if ((this.getBotScore().getGold() > this.getBotScore().getLunar() && this.getBotScore().getGold() > this.getBotScore().getSolar())) {
                Boolean buy = this.diceShopping(sanctuary);
                if (buy) {
                    if (this.getBotScore().getSolar() >= 3 || (this.getBotScore().getSolar() >= 2 && this.getBotScore().getLunar() >= 1)) {
                        this.cardShopping(islands,inventory);
                    }
                }
                else {
                    this.cardShopping(islands,inventory);
                }
            }
            else {
                Boolean buy = this.cardShopping(islands,inventory);
                if (buy) {
                   buyInOrder(sanctuary,islands,true,inventory);
                }
                else {
                    this.diceShopping(sanctuary);
                }
            }
        }


    }

    public Boolean diceShopping(Sanctuary sanctuary) {
        int nbBuy = 0;
                int[] pools = new int[]{12,8,6,5,4,3,2};

        for(int i=0;i<pools.length;i++)
        {
            if(this.getBotScore().getGold()>=pools[i])
            {
                if(diceShopping(sanctuary,pools[i])){nbBuy++;}
            }
        }

        if(nbBuy == 0) {return false;}

        else{return true;}
    }


    /**public void cheat() {
        getBotScore().addSolar(9);

        ScoreCounter.updateScore(getBotScore(), "12@GOLD%12@GOLD");


    }**/

}
