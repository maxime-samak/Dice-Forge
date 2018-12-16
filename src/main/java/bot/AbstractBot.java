package bot;

import game.BotScore;
import game.card.AbstractCard;
import game.card.BuyCard;
import game.card.Inventory;
import game.card.Islands;
import game.dice.*;

import java.util.ArrayList;


/**
 * Classe abstraite AbstractBot implémente l'interface Bot
 */
public abstract class AbstractBot implements Bot {

    private final String botID;
    private final Dice dice1;
    private final Dice dice2;
    private final BotScore botscore;
    private final String color;

    /**
     * Constructeur d'AbstractBot
     * @param d1 son premier dé
     * @param d2 son second dé
     * @param botID son identification
     * @param color sa couleur d'écriture
     */
    public AbstractBot(Dice d1, Dice d2, String botID,String color) {
        this.botID = botID;
        this.dice1 = d1;
        this.dice2 = d2;
        this.botscore = new BotScore();
        this.color=color;
    }

    public BotScore getBotScore() {
        return botscore;
    }

    public String getBotID() { return botID; }

    public Dice getDice1() {
        return dice1;
    }

    public Dice getDice2() {
        return dice2;
    }

    public String getColor() { return color; }

    /**
     * Le bot achète des faces de dés avec en priorité celles avec des points de victoire
     */
    protected ArrayList<DiceCard> favoriseVictory(ArrayList<DiceCard> buyable)
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

    /**
     * Le bot vérifie si les Islands possède encore au moins une carte en commençant par la plus cher (6), si oui alors si il possède les ressources il tentera d'acheter sinon il passe à l'ïle suivante.
     * @param islands
     * @param inventory
     * @return
     */
    protected Boolean lunarShopping(Islands islands,Inventory inventory) {
        int solarFee = 0;
        if(BuyDiceCard.getBought().size() > 0 || BuyCard.getBought().size() > 0){ solarFee = 2;}

        for(int i=6;i>0;i--)
        {
            if (this.getBotScore().getLunar() >= i && this.getBotScore().getSolar() >= solarFee && !(islands.getIslandAvailables(i).isEmpty()))
            {
                if (shopIslandLunar(islands, i,inventory))
                {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Le bot vérifie si les Islands possède encore au moins une carte en commençant par la plus cher (6), si oui alors si il possède les ressources il tentera d'acheter sinon il passe à l'ïle suivante.
     * @param islands
     * @param inventory
     * @return
     */
    protected Boolean solarShopping(Islands islands,Inventory inventory)
    {

        int solarFee = 0;
        if(BuyDiceCard.getBought().size() > 0 || BuyCard.getBought().size() > 0){ solarFee = 2;}

        for(int i=6;i>0;i--)
        {
            if (this.getBotScore().getSolar() >= i + solarFee && !(islands.getIslandAvailables(i).isEmpty()))
            {
                if (shopIslandSolar(islands, i,inventory))
                {
                    return true;
                }
            }

        }

        return false;
    }


    protected Boolean shopIslandTen(Islands islands,Inventory inventory) {
        ArrayList<AbstractCard> cards = islands.getIslandAvailables(10);
        for(int cpt = 0; cpt < cards.size(); cpt++)
        {

            if(BuyCard.buyCard(this, islands,cards.get(cpt),inventory)) {
                return true;
            }
        }
        return false;
    }


    private Boolean shopIslandSolar(Islands islands, int i,Inventory inventory) {
        ArrayList<AbstractCard> cards = islands.getIslandAvailables(i);
        for(int cpt = 0; cpt < cards.size(); cpt++) {
            if(cards.get(cpt).getPrice()[0] == i && this.getBotScore().getSolar()>=i) {
                if(BuyCard.buyCard(this, islands,cards.get(cpt),inventory)) { return true; }
            }
        }
        return false;
    }


    private Boolean shopIslandLunar(Islands islands,int i,Inventory inventory) {
        ArrayList<AbstractCard> cards = islands.getIslandAvailables(i);
        for(int cpt = 0; cpt < cards.size(); cpt++) {
            if(cards.get(cpt).getPrice()[1] == i && this.getBotScore().getLunar()>=i) {
                if(BuyCard.buyCard(this, islands,cards.get(cpt),inventory)) { return true; }
            }
        }
        return false;
    }

    /**
     * Défini la méthode obligatoire play à implémenter qui permet d'instancier un Sanctuaire et une Islande
     */
    public void play(Sanctuary sanctuary, Islands islands, Inventory invetory){}

    public abstract int choose(DiceCard d);
    //public abstract void play(Sanctuary sanctuary, Islands islands);

    public boolean tradeGold() {
        if(this.getBotScore().getGold()>=3)
            return true; //choix par défaut arbitraire
        return false;
    }

    public Resource getPreferredResource() {
        return Resource.SOLAR; //choix par défaut arbitraire
    }

    public Dice getPreferredDice() {
        return dice1; //choix par défaut arbitraire
    }

    public int getPreferredFace() {
        return 4; //choix par défaut arbitraire
    }



}