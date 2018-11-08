package bot;

import objects.*;

import java.util.ArrayList;

public class SimpleBot extends AbstractBot {

    public SimpleBot(Dice d1, Dice d2, String botID) {
        super(d1,d2, botID);
    }

    public String SimpleStrat()
    {
        return this.rollDices();
    }

    //Première stratégie de bot, le bot achète une seule face de dès si il peut et si il en trouve une meilleure que celles qu'il podssède
    // puis achète une seule carte
    public void play(Sanctuary sanctuary)
    {
        this.shopForDiceCards(sanctuary);
        int lunarAvailable = this.getBotscore().getLunar();
        int solarAvailable = this.getBotscore().getSolar();
        //Echanger face acheter avec soit face equivalente plus faible, soit (pour face victoire) gold le plus faible
        //TODO VERIFIER LISTE DES CARTES DISPONIBLE ET ACHETER CARTE ACHETABLE AVEC LE PLUS DE POINTS DE VICTOIRE
    }

    public boolean shopForDiceCards(Sanctuary sanctuary)
    {
        int goldAvailable = this.getBotscore().getGold();
        /*if(goldAvailable >= 12)
            if(shopPoolI(12,sanctuary))
                return true;*/
        if(goldAvailable >= 8)
        {
            if (shopPoolI(8, sanctuary))
                return true;
        }
        if(goldAvailable >= 6)
        {
            if(shopPoolI(6,sanctuary))
                return true;
        }
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
        if(goldAvailable >= 3)
        {
            if(shopPoolI(3,sanctuary))
                return true;
        }
        if(goldAvailable >= 2)
        {
            if(shopPoolI(2,sanctuary))
                return true;
        }
        return false;


    }

    public boolean shopPoolI(int i,Sanctuary sanctuary)
    {
        //TODO VERIFIER LISTE DES FACES DISPONIBLES ET ACHETER FACE SI VALEUR SUPERIEUR A RESSOURCE OU SI VICTOIRE DISPONIBLE A PRIX ACHETABLE
        ArrayList<DiceCard> buyable = sanctuary.getPoolAvailables(i);

        Dice d=null;
        int f=0;
        for (int cpt = 0; cpt < buyable.size(); cpt++)
        {

            DiceCard buy = buyable.get(cpt);

            for (int dice = 1; dice < 3; dice++)
            {
                for (int face = 1; face <= 6; face++)
                {
                    DiceCard fd1 = this.getDice1().getFi(face);
                    if ((fd1.getResource() == buy.getResource() && fd1.getValue() < buy.getValue() || (fd1.getResource() == Resource.GOLD.resourceName() && fd1.getValue() < buy.getValue())))
                    {
                        if(dice==1)
                        {
                            d = getDice1();
                            f=face;
                            break;
                        }
                        if(dice==2)
                        {
                            d = getDice2();
                            f=face;
                            break;
                        }
                    }
                }
                if (d != null)
                    break;
            }
            if(d != null)
            {
                if (BuyCard.setCard(sanctuary,i,buy,d,f)){
                    return true;}
            }
        }
        return false;
    }

}