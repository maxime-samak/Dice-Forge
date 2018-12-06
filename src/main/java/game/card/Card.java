package game.card;

import bot.AbstractBot;
import game.BotScore;
import game.DiceRoll;
import game.ScoreCounter;
import game.dice.BuyDiceCard;
import game.dice.DiceCard;
import game.dice.Resource;

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
    LES_HERBES_FOLLES(2, 1, 0, false, true),
    LA_PINCE(8, 0, 6, false, true),
    L_ENIGME(10, 6, 0, false, true),
    LE_CASQUE_D_INVISIBILITE(4, 0, 5, false, true),
    LE_MIROIR_ABYSSAL(10, 5, 0, false, true);

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
    public Object doEffect(AbstractBot b) { //le bot peut tricher, à modifier.
        BotScore bs = b.getBotScore();
        String answer = "";
        switch (this) {
            case LES_SABOTS_D_ARGENT:
                answer = b.strategyCard(LES_SABOTS_D_ARGENT);
                if(answer == "dice1"){
                    DiceCard roll = DiceRoll.roll(b.getDice1());
                    roll = b.choose(roll);
                    ScoreCounter.updateScore(bs, roll);
                    return roll;
                }
                else{
                    DiceCard roll = DiceRoll.roll(b.getDice2());
                    roll = b.choose(roll);
                    ScoreCounter.updateScore(bs, roll);
                    return roll;
                }
            case L_ANCIEN:
                if (bs.getGold() < 3){}
                else {
                    answer = b.strategyCard(L_ANCIEN);
                    if (answer == "Yes") {
                        ScoreCounter.payGold(bs, 3);
                        ScoreCounter.addResource(bs, Resource.VICTORY, 4);
                        return "Carte utilisée";
                    }
                    return "Carte inutilisée";
                }
                break;

            case LES_AILES_DE_LA_GARDIENNES:
                ScoreCounter.addResource(bs, Resource.GOLD, 1);
                answer = b.strategyCard(LES_AILES_DE_LA_GARDIENNES);
                if(answer == "Lunar"){
                    ScoreCounter.addResource(bs, Resource.LUNAR, 1);
                    return new DiceCard(new int[]{0,1,1},new Resource[]{Resource.PLUS,Resource.GOLD,Resource.LUNAR});
                }
                else{
                    ScoreCounter.addResource(bs, Resource.SOLAR, 1);
                    return new DiceCard(new int[]{0,1,1},new Resource[]{Resource.PLUS,Resource.GOLD,Resource.SOLAR});
                }
            case LES_HERBES_FOLLES:
                ScoreCounter.addResource(bs, Resource.GOLD, 3);
                ScoreCounter.addResource(bs, Resource.LUNAR, 3);
                return new DiceCard(new int[]{0,3,3},new Resource[]{Resource.PLUS,Resource.GOLD,Resource.LUNAR});
            case LA_PINCE:
                DiceCard roll11 = DiceRoll.roll(b.getDice1());
                roll11 = b.choose(roll11);
                ScoreCounter.updateScore(bs, roll11);

                DiceCard roll12 = DiceRoll.roll(b.getDice2());
                roll12 = b.choose(roll12);
                ScoreCounter.updateScore(bs, roll12);

                DiceCard roll21 = DiceRoll.roll(b.getDice1());
                roll21 = b.choose(roll21);
                ScoreCounter.updateScore(bs, roll21);

                DiceCard roll22 = DiceRoll.roll(b.getDice2());
                roll22 = b.choose(roll22);
                ScoreCounter.updateScore(bs, roll22);

                return new DiceCard[]{roll11, roll12, roll21, roll22};

            case L_ENIGME:
                answer = b.strategyCard(L_ENIGME);
                if(answer == "dice1"){
                    DiceCard[] allRoll = new DiceCard[4];
                    for(int i = 0; i < 3; i++) {
                        DiceCard roll = DiceRoll.roll(b.getDice1());
                        roll = b.choose(roll);
                        ScoreCounter.updateScore(bs, roll);
                        allRoll[i] = roll;
                    }
                    return allRoll;
                }
                else {
                    DiceCard[] allRoll = new DiceCard[4];
                    for(int i = 0; i < 3; i++) {
                        DiceCard roll = DiceRoll.roll(b.getDice2());
                        roll = b.choose(roll);
                        ScoreCounter.updateScore(bs, roll);
                        allRoll[i] = roll;
                    }
                    return allRoll;
                }

            case LE_CASQUE_D_INVISIBILITE:
                answer = b.strategyCard(LE_CASQUE_D_INVISIBILITE);
                String answer1[] = answer.split("#");
                if(answer1[0]  == "dice1"){
                    BuyDiceCard.setCard(new DiceCard(0, Resource.X3), b.getDice1(),1); //(int)answer2[1] changer ce que renvoie strategyCard()
                    return b.getDice1();
                }
                else{
                    BuyDiceCard.setCard(new DiceCard(0, Resource.X3), b.getDice2(),1); //(int)answer2[1] changer ce que renvoie strategyCard()
                    return b.getDice2();
                }

            case LE_MIROIR_ABYSSAL:
                answer = b.strategyCard(LE_CASQUE_D_INVISIBILITE);
                String answer2[] = answer.split("#");
                if(answer2[0]  == "dice1"){
                    BuyDiceCard.setCard(new DiceCard(0, Resource.QUESTION), b.getDice1(),1); //(int)answer2[1] changer ce que renvoie strategyCard()
                    return b.getDice1();
                }
                else{
                    BuyDiceCard.setCard(new DiceCard(0, Resource.QUESTION), b.getDice2(),1); //(int)answer2[1] changer ce que renvoie strategyCard()
                    return b.getDice1();
                }

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

    public boolean isTypeInstant() {
        return typeInstant;
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
            case LES_HERBES_FOLLES:
                return "Recevez 3 gold et 3 lunar.";
            case LA_PINCE:
                return "Recevez les faveurs des dieux deux fois de suite.";
            case L_ENIGME:
                return "Recevez une faveur mineur 4 fois de suite.";
            case LE_CASQUE_D_INVISIBILITE:
                return "Récupérez une face X3 et forgez la aussitôt sur l'un de vos dés.";
            case LE_MIROIR_ABYSSAL:
                return "Récupérez une face QUESTION et forgez la aussitôt sur l'un de vos dés.";
            default:
                return "La carte n'existe pas.";
        }
    }

}

