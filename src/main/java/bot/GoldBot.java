package bot;

import game.card.Islands;
import game.dice.Dice;
import game.dice.DiceCard;
import game.dice.Resource;
import game.dice.Sanctuary;

import java.util.ArrayList;

import static game.dice.BuyDiceCard.setCard;

/**
 * Ce bot a pour but de jouer avec une stratégie lié à l'or.
 * L'achat de faces GOLD.
 * Le gain d'une grande quantité d'or.
 * Et la convertion de cet or en points de victoire via certaines cartes.
 * Sont ses 3 objectifs de jeu.
 */
public abstract class GoldBot extends AbstractBot {
    private boolean[] completedGoals = new boolean[]{false, false, false, false};
    //[0] posséder la face 2 SOLAR
    //[0] posséder la face 2 LUNAR
    //[0] posséder la face x3


    public GoldBot(Dice d1, Dice d2, String botID) { super(d1, d2, botID); }

    public void play(Sanctuary sanctuary, Islands islands) {

        int gold = this.getBotScore().getGold();
        int solar = this.getBotScore().getSolar();
        int lunar = this.getBotScore().getLunar();

    }

    /*public Boolean diceShopping(Sanctuary sanctuary) {
        int nbBuy = 0;
        int gold = this.getBotScore().getGold();

        //if(gold >= 12 && nbBuy < 1) { if(diceShopping(sanctuary,12)) nbBuy++; }

        if(gold >= 8 && nbBuy < 1) { if(diceShopping(sanctuary, 8)) nbBuy++; }

        if(gold >= 6 && nbBuy < 1) { if(diceShopping(sanctuary,6)) nbBuy++; }

        if(gold >= 5 && nbBuy < 1) { if(diceShopping(sanctuary,5)) nbBuy++; }

        if(gold >= 4 && nbBuy < 1) { if(diceShopping(sanctuary,4)) nbBuy++; }

        if(gold >= 3 && nbBuy < 1) { if(diceShopping(sanctuary, 3)) nbBuy++; }

        if(gold >= 2 && nbBuy < 1) { if(diceShopping(sanctuary, 2)) nbBuy++; }

        if(nbBuy == 0) {return false;}

        else{return true;}
    }*/

    /*public boolean diceShopping(Sanctuary sanctuary, int pool) {
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
    }*/
}
