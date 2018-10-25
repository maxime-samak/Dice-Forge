import bot.SimpleBot;
import engine.ScoreCounter;
import objects.Dice;

public class Partie {
    //initialisation de la partie
    public static void main(String[] args) {
        Dice d1= new Dice();
        Dice d2= new Dice();
        d1.solarDiceInit();
        d2.lunarDiceInit();
        SimpleBot bot1 = new SimpleBot(d1,d2);
        System.out.println("Début de la partie :");
        for(int i = 0;i<Integer.parseInt(args[0]);i++) {
            System.out.println("Lancer de dés :");
            String lancer=bot1.SimpleStrat();
            ScoreCounter.updateScore(bot1.getBotscore(),lancer);
        }
        System.out.println("Fin de la partie :");
        System.out.println("Score du joueur 1 : "+bot1.getBotscore().getInfos());
        System.out.println("JOUEUR 1 A GAGNER, FELICITATIONS!");
    }
}