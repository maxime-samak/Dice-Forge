package game.card;

import game.BotScore;
import game.DiceRoll;
import game.ScoreCounter;
import game.dice.Dice;

import java.util.Scanner;

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

    Card(int victory, int cost_solar, int cost_lunar, boolean typeReinforcement) {
        this.victory = victory;
        this.cost_solar = cost_solar;
        this.cost_lunar = cost_lunar;
        this.typeReinforcement = typeReinforcement;
    }

    protected void doEffect(Dice d1, BotScore bs, ScoreCounter sc) {
        switch (this) {
            case LES_SABOTS_D_ARGENT: // lui demander le dé ?
                System.out.println("Biche");
                String roll = DiceRoll.roll(d1);
                roll += "%0@LUNAR";
                sc.updateScore(bs, roll);
                break;

            case L_ANCIEN:
                System.out.println("Ancien");
                if (bs.getGold() < 3){
                    System.out.println("Vous n'avez pas assez d'or pour effectuer l'effet de la carte L'ancien");
                }
                else{// bug à réparer
                    System.out.println(L_ANCIEN + "\n Voulez-vous effectuer cette action ?y/n \n");
                    Scanner scan = new Scanner(System.in);
                    String res = scan.nextLine();

                    while(res != "y" || res != "n") {
                        System.out.println("Saisie incorecte. y/n  ?");
                        res = scan.nextLine();
                    }
                    if (res == "y") {
                        sc.payGold(bs, 3);
                        sc.addVictory(bs, 4);
                    }
                }
                break;

            case LES_AILES_DE_LA_GARDIENNES:
                System.out.println("Gardienne");
                sc.gainGold(bs, 1);
                //implémentater : soit un solar ou un lunar
                sc.gainLunar(bs, 1);
                sc.gainSolar(bs, 1);
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

