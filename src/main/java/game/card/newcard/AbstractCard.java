package game.card.newcard;


import bot.AbstractBot;

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
        LE_MIRROIR_ABYSSAL,

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

    public abstract void getEffect(AbstractBot bot);

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


}
