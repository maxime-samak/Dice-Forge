package game.card;

import bot.SimpleBot;
import game.BotScore;
import game.DiceRoll;
import game.ScoreCounter;
import game.dice.Dice;
import game.dice.DiceCard;
import game.dice.Resource;

/**
 * Classe Card permet la création de cartes prédéfinies
 *
 */
public enum Card {

    LES_SABOTS_D_ARGENT(2, 0, 2, true),
    L_ANCIEN(0, 1, 0, true),
    LES_AILES_DE_LA_GARDIENNES(4, 2, 0, true),
    LE_PASSEUR(12, 0, 4, false),
    LA_MEDUSE(14, 4, 0, false),
    L_HYDRE(26, 5, 5, false);

    private int victory;
    private int cost_solar;
    private int cost_lunar;
    private boolean typeReinforcement;

    /**
     * Construction de la classe, qui prend 4 attributs
     * @param victory
     * @param cost_solar
     * @param cost_lunar
     * @param typeReinforcement
     */
     Card(int victory, int cost_solar, int cost_lunar, boolean typeReinforcement) {
        this.victory = victory;
        this.cost_solar = cost_solar;
        this.cost_lunar = cost_lunar;
        this.typeReinforcement = typeReinforcement;
    }

    /**
     * Effect éxecute un des case.
     * @param b
     */
    public void doEffect(SimpleBot b) { //le bot peut tricher, à modifier.
        BotScore bs = b.getBotScore();
        String anwers = "";
        switch (this) {
            case LES_SABOTS_D_ARGENT:
                anwers = b.strategyCard(LES_SABOTS_D_ARGENT);
                if(anwers == "dice1"){
                    DiceCard roll = DiceRoll.roll(b.getDice1());
                    ScoreCounter.updateScore(bs, roll);
                }
                else{
                    DiceCard roll = DiceRoll.roll(b.getDice2());
                    ScoreCounter.updateScore(bs, roll);
                }
                break;

            case L_ANCIEN:
                if (bs.getGold() < 3){}
                else {
                    anwers = b.strategyCard(L_ANCIEN);
                    if (anwers == "Yes") {
                        ScoreCounter.payGold(bs, 3);
                        ScoreCounter.addResource(bs, Resource.VICTORY, 4);
                    }
                }
                break;

            case LES_AILES_DE_LA_GARDIENNES:
                ScoreCounter.addResource(bs, Resource.GOLD, 1);
                anwers = b.strategyCard(LES_AILES_DE_LA_GARDIENNES);
                if(anwers == "Lunar"){ ScoreCounter.addResource(bs, Resource.LUNAR, 1);;}
                else{ ScoreCounter.addResource(bs, Resource.VICTORY, 1);;}
                break;

            default: {
            }
        }
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
            default:
                return "La carte n'existe pas.";
        }
    }

}

