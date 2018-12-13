package bot;

import game.card.*;
import game.dice.*;

import java.util.ArrayList;
import static game.dice.BuyDiceCard.setCard;
import static game.dice.Resource.GOLD;
import static game.dice.Resource.LUNAR;
import static game.dice.Resource.SOLAR;

/**
 * Classe SavingBot utilise les mêmes fonctions que SimpleBot mais implémente une stratégie différente : Le bot garde ses ressources et n'achète que les objets(cartes, faces de dès) les plus chers encore disponibles dans la partie.
 */
public class SavingBot extends AbstractBot {


    public SavingBot(Dice d1, Dice d2, String botID,String color) {
        super(d1,d2, botID,color);
    }

    @Override
    public void play(Sanctuary sanctuary,Islands islands,Inventory inventory) {
        buyInOrder(sanctuary,islands,inventory);
    }

    public void buyInOrder(Sanctuary sanctuary,Islands islands,Inventory inventory) {

        if(BuyDiceCard.getBought().size()==0 && BuyCard.getBought().size()>0)
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
                if (this.diceShopping(sanctuary)) {
                    if (this.getBotScore().getSolar() >= 3 || (this.getBotScore().getSolar() >= 2 && this.getBotScore().getLunar() >= 1)) {
                        this.cardShopping(islands,inventory);
                    }
                }
                else {
                    this.cardShopping(islands,inventory);
                }
            }
            else {
                if (this.cardShopping(islands,inventory)){
                    buyInOrder(sanctuary,islands,inventory);
                }
                else {
                    this.diceShopping(sanctuary);
                }
            }
        }


    }

    /**
     * Le bot vérifie quel pool de faces n'est pas vide en commençant par la plus cher, si il n'a pas assez pour acheter dans cete pool, il ne fait rien, sinon il tente d'acheter.
     * @param sanctuary
     * @return
     */
    public Boolean diceShopping(Sanctuary sanctuary) {
        int gold = this.getBotScore().getGold();
        int[] pools = new int[]{12,8,6,5,4,3,2};

        for(int i=0;i<pools.length;i++)
        {
            if(!(sanctuary.getPoolAvailables(pools[i]).isEmpty())) {
                if (gold >= pools[i])
                {
                    if (diceShopping(sanctuary, pools[i]))
                        return true;
                }
                break;
            }
        }
        return false;
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
        buyable = this.favoriseSolLun(buyable);
        Dice d = null;
        int f = 0;

        for (int i = 0; i < buyable.size(); i++) {
            DiceCard buy = buyable.get(i);

                for (int face = 1; face <= 6; face++) {
                    DiceCard fd1;
                    fd1 = this.getDice1().getFi(face);

                    if ((fd1.getResource() == buy.getResource() && fd1.getValue() < buy.getValue()-1) || fd1.getResource() == GOLD.resourceName() && fd1.getValue() == 1) {

                        d = this.getDice1();
                        f = face;
                        break;

                    }
                    fd1 = this.getDice2().getFi(face);
                    if ((fd1.getResource() == buy.getResource() && fd1.getValue() < buy.getValue()-1) || fd1.getResource() == GOLD.resourceName() && fd1.getValue() == 1) {

                        d = this.getDice2();
                        f = face;
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
     * La fonction réarrange les faces disponibles à l'achat pour le bot, en lui présentant en priorité les faces SOLAR et LUNAR
     * @param buyable
     * @return
     */
    private ArrayList<DiceCard> favoriseSolLun(ArrayList<DiceCard> buyable)
    {
        int i = 0;
        ArrayList<Integer> listSolLun= new ArrayList<>();
        ArrayList<DiceCard> newBuy= new ArrayList<>();
        for(int cpt=0;cpt<buyable.size();cpt++)
        {
            if(buyable.get(cpt).getResource()== SOLAR.resourceName() || buyable.get(cpt).getResource()== LUNAR.resourceName() )
            {
                listSolLun.add(1);
                i++;
            }
            else
            {
                listSolLun.add(0);
            }
        }
        for(int cpt2=0;cpt2<listSolLun.size();cpt2++)
        {
            if(listSolLun.get(cpt2)==1)
            {
                i--;
                newBuy.add(buyable.get(cpt2));
            }
            if(i==0)
            {
                break;
            }
        }
        for(int cpt3=0;cpt3<listSolLun.size();cpt3++)
        {
            if(listSolLun.get(cpt3)==0)
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

    private Boolean cardShopping(Islands islands,Inventory inventory) {
        int nbBuy = 0;
        int solarFee = 0;
        if(BuyDiceCard.getBought().size() > 0 || BuyCard.getBought().size() > 0){ solarFee = 2;}
        if(!(islands.getIslandAvailables(10).isEmpty()) && this.getBotScore().getLunar() >= 5 && this.getBotScore().getSolar() >= 5 + solarFee) { if(shopIslandTen(islands,inventory)){nbBuy++;}}
        if(nbBuy == 0) {
            if(this.getBotScore().getSolar() >= this.getBotScore().getLunar()) {
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
     * Le bot vérifie si les Islands possède encore au moins une carte en commençant par la plus cher (6), si oui alors si il possède les ressources il tentera d'acheter sinon il ne fait rien.
     * @param islands
     * @param inventory
     * @return
     */
    private Boolean lunarShopping(Islands islands,Inventory inventory) {
        int solarFee = 0;
        if(BuyDiceCard.getBought().size() > 0 || BuyCard.getBought().size() > 0){ solarFee = 2;}

        for(int i=6;i>0;i--)
        {
            if(!(islands.getIslandAvailables(i).isEmpty()))
            {
                if(this.getBotScore().getLunar() >= i && this.getBotScore().getSolar()>=solarFee){
                    if (shopIslandLunar(islands, i,inventory))
                        return true;
                }
                break;
            }
        }

        return false;
    }

    /**
     * Le bot vérifie si les Islands possède encore au moins une carte en commençant par la plus cher (6), si oui alors si il possède les ressources il tentera d'acheter sinon il ne fait rien.
     * @param islands
     * @param inventory
     * @return
     */
    private Boolean solarShopping(Islands islands,Inventory inventory)
    {
        int solarFee = 0;
        if(BuyDiceCard.getBought().size() > 0 || BuyCard.getBought().size() > 0){ solarFee = 2;}

        for(int i=6;i>0;i--)
        {
            if(!(islands.getIslandAvailables(i).isEmpty()))
            {
                if(this.getBotScore().getSolar() >=i+solarFee){
                    if (shopIslandSolar(islands, i,inventory))
                        return true;
                }
                break;
            }
        }
        return false;
    }

    private Boolean shopIslandTen(Islands islands, Inventory inventory) {
        ArrayList<AbstractCard> cards = islands.getIslandAvailables(10);
        for(int cpt=0;cpt<cards.size();cpt++) {
            if(BuyCard.buyCard(this, islands,cards.get(cpt),inventory)) {
                return true;
            }
        }
        return false;
    }

    private Boolean shopIslandSolar(Islands islands,int i,Inventory inventory) {
        ArrayList<AbstractCard> cards = islands.getIslandAvailables(i);
        for (int cpt = 0; cpt < cards.size(); cpt++) {
            if (cards.get(cpt).getPrice()[0] == i) {
                if (BuyCard.buyCard(this, islands, cards.get(cpt),inventory)) {
                    return true;
                }
            }
        }
        return false;
    }

    private Boolean shopIslandLunar(Islands islands,int i,Inventory inventory) {
        ArrayList<AbstractCard> cards = islands.getIslandAvailables(i);
        for(int cpt=0; cpt<cards.size(); cpt++) {
            if(cards.get(cpt).getPrice()[1]==i) {
                if(BuyCard.buyCard(this, islands,cards.get(cpt),inventory)) { return true; }
            }
        }
        return false;
    }


    @Override
    public Resource getPreferredResource() {
        int solar=this.getBotScore().getSolar();
        int gold=this.getBotScore().getGold();
        int lunar=this.getBotScore().getLunar();
        if(solar<=lunar&&solar<=gold)
            return SOLAR;
        else if(lunar<solar&&lunar<=gold)
            return LUNAR;
        else
            return GOLD; //choix par défaut arbitraire
    }

    /**
     * Cette fonction permets au bot d'utiliser les faces de dès à choix, il choisira alors la ressource qu'il veut dans la liste disponible d'après la face de dè envoyée en paramètre.
     * @param d
     * @return
     */
    public int choose(DiceCard d)
    {
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
                if(getPreferredResource()==GOLD)
                    i = 1;
                else if(getPreferredResource()==SOLAR)
                    i = 2;
                else if(getPreferredResource()==LUNAR)
                    i = 3;
                else
                    i = 0;
            }
                return i;
        }
    }


    /**
     * Cette fonction permet au Bot de décider si oui ou non il veut utiliser la carte "L'ancien".
     * @return
     */
    @Override
    public boolean tradeGold()
    {
        if(this.getBotScore().getGold()>=10)
            return true;
        return false;
    }
}