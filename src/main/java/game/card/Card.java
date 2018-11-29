package game.card;

import bot.SimpleBot;
import game.BotScore;
import game.DiceRoll;
import game.ScoreCounter;
import game.dice.Dice;
import game.dice.DiceCard;
import game.dice.Resource;

import static game.dice.Resource.X3;

/**
 * Classe Card permet la création de cartes prédéfinies
 *
 */
public enum Card {

    LES_SABOTS_D_ARGENT(2, 0, 2, true, false),
    L_ANCIEN(0, 1, 0, true, false),
    LES_AILES_DE_LA_GARDIENNES(4, 2, 0, true, false),
    LE_PASSEUR(12, 0, 4, false, false),
    LA_MEDUSE(14, 4, 0, false, false),
    L_HYDRE(26, 5, 5, false, false),
    LES_SATYRES(6,0,3,false,true),
    LE_CASQUE_D_INVISIBILITE(4,0,5, false, true),
    LE_COFFRE_DU_FORGERON(2,0,1,false, true);

    private int victory;
    private int cost_solar;
    private int cost_lunar;
    private boolean typeReinforcement;
    private boolean typeInstant;

    /**
     * Construction de la classe, qui prend 4 attributs
     * @param victory
     * @param cost_solar
     * @param cost_lunar
     * @param typeReinforcement
     * @param typeInstant
     */

    Card(int victory, int cost_solar, int cost_lunar, boolean typeReinforcement, boolean typeInstant) {
        this.victory = victory;
        this.cost_solar = cost_solar;
        this.cost_lunar = cost_lunar;
        this.typeReinforcement = typeReinforcement;
        this.typeInstant = typeInstant;
    }

    /**
     * Effect éxecute un des case.
     * @param b
     */
    public Object doEffect(SimpleBot b) { //le bot peut tricher, à modifier.
        BotScore bs = b.getBotScore();
        String anwers = "";
        switch (this) {
            case LES_SABOTS_D_ARGENT:
                anwers = b.strategyCard(LES_SABOTS_D_ARGENT);
                if(anwers == "dice1"){
                    DiceCard roll = DiceRoll.roll(b.getDice1());
                    roll = b.choose(roll);
                    ScoreCounter.updateScore(bs, roll);
                    return roll;
                }
                else{
                    DiceCard roll = DiceRoll.roll(b.getDice2());
                    ScoreCounter.updateScore(bs, roll);
                    return roll;
                }
            case L_ANCIEN:
                if (bs.getGold() < 3){}
                else {
                    anwers = b.strategyCard(L_ANCIEN);
                    if (anwers == "Yes") {
                        ScoreCounter.payGold(bs, 3);
                        ScoreCounter.addResource(bs, Resource.VICTORY, 4);
                        return "Carte utilisée";
                    }
                    return "Carte inutilisée";
                }
                break;

            case LES_AILES_DE_LA_GARDIENNES:
                ScoreCounter.addResource(bs, Resource.GOLD, 1);
                anwers = b.strategyCard(LES_AILES_DE_LA_GARDIENNES);
                if(anwers == "Lunar"){
                    ScoreCounter.addResource(bs, Resource.LUNAR, 1);
                    return new DiceCard(new int[]{0,1,1},new Resource[]{Resource.PLUS,Resource.GOLD,Resource.LUNAR});
                }
                else{
                    ScoreCounter.addResource(bs, Resource.SOLAR, 1);
                    return new DiceCard(new int[]{0,1,1},new Resource[]{Resource.PLUS,Resource.GOLD,Resource.SOLAR});
                }

            case LE_COFFRE_DU_FORGERON:
                ScoreCounter.incrementExtended(b.getBotScore());
                break;

            default: {
            }
        }
        return null;
    }

    public int getVictory() {
        return victory;
    }

    public int[] getPrice() {
        int[] price = {cost_solar, cost_lunar,};
        return price;
    }

    public boolean isTypeReinforcement() {
        return typeReinforcement;
    }

    public String toString(){
        String reinforcement = " à tous les tours jusqu'à la fin.";
        String noEffect = "Pas d’effet. Cette carte ne donne que des points de victoire.";
        switch(this){
            case LES_SABOTS_D_ARGENT:
                return "Recevez les ressources d'un lancé de dé" + reinforcement;
            case L_ANCIEN:
                return "Vous pouvez dépensez 3 gold pour gagner 4 point gloire" + reinforcement;
            case LES_AILES_DE_LA_GARDIENNES:
                return "Recevez 1 gold et 1 lunar ou solar" + reinforcement;
            case LE_PASSEUR:
                return noEffect;
            case LA_MEDUSE:
                return noEffect;
            case L_HYDRE:
                return noEffect;
            case LE_COFFRE_DU_FORGERON:
                return "Etend la réserve du joueur";
            default:
                return "La carte n'existe pas.";
        }
    }

}

