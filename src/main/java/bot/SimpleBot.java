package bot;

import objects.Dice;
import objects.DiceCard;
import objects.Sanctuary;

import java.util.ArrayList;

public class SimpleBot extends AbstractBot {

    public SimpleBot(Dice d1, Dice d2, String botID) {
        super(d1,d2, botID);
    }

    public String SimpleStrat(Sanctuary sanctuary)
    {
        int goldAvailable = this.getBotscore().getGold();
        //Check pool 12 a implementer en v3
        // if(goldAvailable>=12)
        //Check de la pool 8
        if(goldAvailable >= 8)
        {
           ArrayList<DiceCard> buyable8 = sanctuary.getPoolAvailables(8);
        }





        //TODO VERIFIER LISTE DES FACES DISPONIBLES ET ACHETER FACE SI VALEUR SUPERIEUR A RESSOURCE OU SI VICTOIRE DISPONIBLE A PRIX ACHETABLE
        int lunarAvailable = this.getBotscore().getLunar();
        int solarAvailable = this.getBotscore().getSolar();
        //Echanger face acheter avec soit face equivalente plus faible, soit (pour face victoire) gold le plus faible
        //TODO VERIFIER LISTE DES CARTES DISPONIBLE ET ACHETER CARTE ACHETABLE AVEC LE PLUS DE POINTS DE VICTOIRE
        return this.rollDices();
    }

}