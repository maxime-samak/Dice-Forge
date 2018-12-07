package game.card;

import bot.AbstractBot;

/**
 * Cette classe représentes la structure général d'un carte.
 */
public abstract class AbstractCard implements Card {

    private final Name name;
    private final Type type;
    private final int victory;
    private final int[] price;

    protected enum Name {
        LE_MARTEAU_DU_FORGERON,
        LE_COFFRE_DU_FORGERON,

        L_ANCIEN,
        LES_HERBES_FOLLES,

        LES_SABOTS_D_ARGENT,
        LES_SATYRES,

        LES_AILES_DE_LA_GARDIENNES,
        LE_MINOTAURE,

        LE_PASSEUR,
        LE_CASQUE_D_INVISIBILITE,

        LA_MEDUSE,
        LE_MIROIR_ABYSSAL,

        LA_PINCE,
        L_ENIGME,
        L_HYDRE
    }

    protected enum Type {
        INSTANT,
        RECURRENT
    }

    public AbstractCard(Name name, Type type, int victory, int solarPrice, int lunarPrice) {
        this.name = name;
        this.type = type;
        this.victory = victory;

        this.price = new int[2];
        this.price[0] = solarPrice;
        this.price[1] = lunarPrice;
    }

    public abstract Object getEffect(AbstractBot bot);

    public boolean equals(AbstractCard card) {
        if (this.name == card.name) {
            return true;
        }
        else { return false; }
    }

    public int[] getPrice() {
        return price;
    }

    public Name getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public int getVictory() {
        return victory;
    }

    public String toString() {
        return name.toString();
    }

    public String effectToString(){
        String noEffect = "Pas d’effet. Cette carte ne donne que des points de victoire.";
        switch(getName()){
            case LES_SABOTS_D_ARGENT:
                return "Recevez les ressources d'un lancé de dé";
            case L_ANCIEN:
                return "Vous pouvez dépensez 3 gold pour gagner 4 point gloire";
            case LES_AILES_DE_LA_GARDIENNES:
                return "Recevez 1 gold et 1 lunar ou solar";
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
            case LE_MARTEAU_DU_FORGERON:
                return "Vous pouvez envoyer des Golds dans la forge et recevrez des Victory";
            case LE_COFFRE_DU_FORGERON:
                return "Banque étendue";
            default:
                return "La carte n'existe pas.";
        }
    }


}
