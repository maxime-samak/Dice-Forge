package bot;

import objects.Dice;
import objects.DiceCard;
import objects.Resource;
import objects.Sanctuary;

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
    public String SimpleStrat2(Sanctuary sanctuary)
    {
        this.shopForDiceCards(sanctuary);
        int lunarAvailable = this.getBotscore().getLunar();
        int solarAvailable = this.getBotscore().getSolar();
        //Echanger face acheter avec soit face equivalente plus faible, soit (pour face victoire) gold le plus faible
        //TODO VERIFIER LISTE DES CARTES DISPONIBLE ET ACHETER CARTE ACHETABLE AVEC LE PLUS DE POINTS DE VICTOIRE
        return this.rollDices();
    }

    public void shopForDiceCards(Sanctuary sanctuary)
    {

        int goldAvailable = this.getBotscore().getGold();
        DiceCard c=null;
        //Check pool 12 a implementer en v3
        // if(goldAvailable>=12)
        //Check de la pool 8
        if(goldAvailable >= 8)
        {
            //TODO VERIFIER LISTE DES FACES DISPONIBLES ET ACHETER FACE SI VALEUR SUPERIEUR A RESSOURCE OU SI VICTOIRE DISPONIBLE A PRIX ACHETABLE
            ArrayList<DiceCard> buyable8 = sanctuary.getPoolAvailables(8);
            for(int cpt=0;cpt<buyable8.size();cpt++)
            {
                DiceCard buy = buyable8.get(buyable8.size()-cpt);
                for(int dice=1;dice<3;dice++)
                {
                    for(int face=0;face<6;face++)
                    {
                        DiceCard fd1 = this.getFace(dice, face);
                        if ((fd1.getResource() == buy.getResource() && fd1.getValue() < buy.getValue() || (fd1.getResource() == Resource.GOLD.resourceName() && fd1.getValue() < buy.getValue()))) {
                            c = fd1;
                            break;
                        } else {
                            c = null;
                        }
                    }
                    if(c!=null)
                        break;
                }
                if(buyCard(buy,c))
                    break;
            }
            if(c!=null)
            {

            }
        }

    }

    public boolean buyCard(DiceCard b, DiceCard c){return true;}

}