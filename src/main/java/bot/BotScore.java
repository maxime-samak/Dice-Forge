package bot;

public class BotScore {

    private int gold = 0;
    private int solar = 0;
    private int lunar = 0;
    private int victory = 0;
    private int extended = 0; //on prévoit que le nb max de ressource puisse être etendu (v3).

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

    public void addGold(int gold) {
        gold = Math.abs(gold);
        if(this.gold + gold >= 12 + (4 * extended)) {
            this.gold = 12 + (4 * extended);
        }
        else {this.gold += gold;}
    }

    public void addSolar(int solar) {
        solar = Math.abs(solar);
        if(this.solar + solar >= 6 + (2 * extended)) {
            this.solar = 6 + (2 * extended);
        }
        else {this.solar += solar;}
    }

    public void addLunar(int lunar) {
        lunar = Math.abs(lunar);
        if(this.lunar + lunar >= 6 + (2 * extended)) {
            this.lunar = 6 + (2 * extended);
        }
        else {this.lunar += lunar;}
    }

    public void addVictory(int victory) {
        this.victory += victory;
    }

    /** Ready for v2 + v3

    public void removeGold(int gold) {
        gold = Math.abs(gold);
        if(this.gold - gold <= 0) {
            this.gold = 0;
        }
        else{
            this.gold -= gold;
        }
    }

     public void removeSolar(int solar) {
        solar = Math.abs(solar);
        if(this.solar - solar <= 0) {
            this.solar = 0;
        }
        else{
            this.solar -= solar;
        }
    }

    public void removeLunar(int lunar) {
        lunar = Math.abs(lunar);
        if(this.lunar - lunar <= 0) {
            this.lunar = 0;
        }
        else{
            this.lunar -= lunar;
        }
    }**/
}