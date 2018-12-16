package bot;

import game.BotScore;
import game.card.AbstractCard;
import game.card.BuyCard;
import game.card.Inventory;
import game.card.Islands;
import game.dice.*;

import java.util.ArrayList;

import static game.dice.BuyDiceCard.setCard;


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

    protected Boolean cardShopping(Islands islands,Inventory inventory) {
        int nbBuy = 0;
        int solarFee = 0;
        if(BuyDiceCard.getBought().size() > 0 || BuyCard.getBought().size() > 0){ solarFee = 2;}
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

    /**
     * Cette fonction permets au bot d'utiliser les faces de dès à choix, il choisira alors la ressource qu'il veut dans la liste disponible d'après la face de dè envoyée en paramètre.
     * @param d
     * @return
     */
    public int choose(DiceCard d){
        if(d.getResource()!=Resource.CHOICE.resourceName())
        {
            return 0;
        }
        else
        {
            int i;
            Resource[] resources = d.getResourceArray();
            int[] values = d.getValueArray();

            if(resources[1].resourceName()==Resource.VICTORY.resourceName())
                return 1;
            else
            {
                if(this.getPreferredResource()==Resource.GOLD)
                    i=1;
                else if(this.getPreferredResource()==Resource.SOLAR)
                    i=2;
                else
                    i=3;

            }
            return i;
        }
    }

    public boolean tradeGold() {
        return false; //choix par défaut arbitraire
    }

    public Resource getPreferredResource() {
        if(this.getBotScore().getGold()<=this.getBotScore().getSolar()&&this.getBotScore().getGold()<=this.getBotScore().getLunar())
            return Resource.GOLD;
        else if(this.getBotScore().getSolar()<this.getBotScore().getGold()&&this.getBotScore().getSolar()<=this.getBotScore().getLunar())
            return Resource.SOLAR;
        else
            return Resource.LUNAR;
        //choix par défaut arbitraire
    }

    public Dice getPreferredDice() {
        return dice1; //choix par défaut arbitraire
    }

    public int getPreferredFace() {
        return 4; //choix par défaut arbitraire
    }



}