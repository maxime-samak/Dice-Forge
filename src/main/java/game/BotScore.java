package game;

/**
 * Classe BotScore permet au bot de connaitre ses ressources et également de déterminer le gagnant.
 * Toutes les méthodes de cette classe permettent de manipuler les valeurs des ressources du score d'un bot.
 */
public class BotScore {

    private int gold = 0;
    private int solar = 0;
    private int lunar = 0;
    private int victory = 0;
    private int extended = 0;
    private int forge;

    public BotScore() {}

    public int getGold() {
        return gold;
    }

    public int getSolar() {
        return solar;
    }

    public int getLunar() {
        return lunar;
    }

    public int getVictory() {
        return victory;
    }

    protected void addGold(int gold) {
        gold = Math.abs(gold);
        if(this.gold + gold >= 12 + (4 * extended)) {
            this.gold = 12 + (4 * extended);
        }
        else {this.gold += gold;}
    }

    protected void addSolar(int solar) {
        solar = Math.abs(solar);
        if(this.solar + solar >= 6 + (2 * extended)) {
            this.solar = 6 + (2 * extended);
        }
        else {this.solar += solar;}
    }

    protected void addLunar(int lunar) {
        lunar = Math.abs(lunar);
        if(this.lunar + lunar >= 6 + (2 * extended)) {
            this.lunar = 6 + (2 * extended);
        }
        else {this.lunar += lunar;}
    }

    protected void addVictory(int victory) {
        victory = Math.abs(victory);
        this.victory += victory;
    }

    protected void removeGold(int gold) {
        gold = Math.abs(gold);
        if(this.gold - gold < 0)
            this.gold = 0;
        else
            this.gold = this.gold - gold;
    }
    protected void removeSolar(int solar) {
        solar = Math.abs(solar);
        if(this.solar - solar <= 0) {
            this.solar = 0;
        }
        else{
            this.solar -= solar;
        }
    }

    protected void removeLunar(int lunar) {
        lunar = Math.abs(lunar);
        if(this.lunar - lunar <= 0) {
            this.lunar = 0;
        }
        else{
            this.lunar -= lunar;
        }
    }

    protected void extend() {
        extended++;
    }

    protected void initForge() {
        forge = 0;
    }

    protected void addForge(int gold) {
        gold = Math.abs(gold);
        forge += gold;
    }

    public String getInfos() {
        return "Gold : "+this.getGold()+", Solar : "+this.getSolar()+", Lunar : "+this.getLunar()+", Victory : "+getVictory();
    }

}