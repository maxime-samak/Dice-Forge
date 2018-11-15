package bot;

import game.dice.Dice;
import game.dice.DiceCard;
import game.dice.Resource;
import game.dice.Sanctuary;
import java.util.ArrayList;
import static game.dice.BuyDiceCard.setCard;

public class SimpleBot extends AbstractBot {

    public SimpleBot(Dice d1, Dice d2, String botID) {
        super(d1,d2, botID);
    }

    public void play(Sanctuary sanctuary) { this.diceShopping(sanctuary); }

    public void diceShopping(Sanctuary sanctuary) {
        int gold = this.getBotScore().getGold();
        /*if(goldAvailable >= 12)
            if(shopPoolI(12,sanctuary))
                return true;*/
        if(gold >= 8) { diceShopping(sanctuary, 8); }

        else if(gold >= 6) { diceShopping(sanctuary,6); }
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
        else if(gold >= 3) { diceShopping(sanctuary, 3); }

        else if(gold >= 2) { diceShopping(sanctuary, 2 ); }

        else { System.out.println(this.getBotID() + "passe son tour. \n"); }
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

        Dice d = null;
        int f = 0;
        for (int i = 0; i < buyable.size(); i++) {

            DiceCard buy = buyable.get(i);

            for (int dice = 1; dice < 3; dice++) {
                for (int face = 1; face <= 6; face++) {
                    DiceCard fd1;
                    if(dice == 1){ fd1 = this.getDice1().getFi(face);}
                    else{fd1 = this.getDice2().getFi(face);}

                    if ((fd1.getResource() == buy.getResource() && fd1.getValue() < buy.getValue()) || (fd1.getResource() == Resource.GOLD.resourceName() && fd1.getValue() <= buy.getValue())) {

                        if(dice == 1) {
                            d = this.getDice1();
                            f = face;
                            break;
                        }
                        if(dice == 2) {
                            d = this.getDice2();
                            f = face;
                            break;
                        }
                    }
                }
                if (d != null) {
                    break;
                }
            }
            if(d != null) {
                if (setCard(sanctuary,pool,buy,d,f,this.getBotScore())){
                    return true; }
            }
        }
        return false;
    }

    /**public void cheat() {
        getBotScore().addSolar(9);

        ScoreCounter.updateScore(getBotScore(), "12@GOLD%12@GOLD");


    }**/
}