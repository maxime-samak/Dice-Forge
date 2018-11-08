import bot.SimpleBot;
import engine.ScoreCounter;
import objects.Dice;

public class Game {

    private SimpleBot[] botarray = new SimpleBot[4];

    private final int nbPlayers;
    private final int nbTurn;

    public Game(int nbPlayers) {

        this.nbPlayers = nbPlayers;
        if(nbPlayers == 3) {this.nbTurn = 10;}
        else {this.nbTurn = 9;}

        for(int i = 0; i < nbPlayers; i++) {
            Dice d1 = new Dice();
            Dice d2 = new Dice();
            d1.solarDiceInit();
            d2.lunarDiceInit();

            botarray[i] = new SimpleBot(d1, d2, "bot#" + (i+1));
        }
    }

    public void turn() {
        for (int i = 0; i < nbPlayers; i++){
            System.out.println("Tour de : " + botarray[i].getBotID());
            for (int j = 0; j < nbPlayers; j++){
                System.out.println("Lancer de dés " + botarray[j].getBotID() + " :");
                String lancer = botarray[j].SimpleStrat();
                ScoreCounter.updateScore(botarray[j].getBotscore(),lancer);
            }
            botarray[i].play();
        }
    }

    public void begin() {
        for(int i = 0; i<nbTurn; i++) {
            System.out.println("**** Tour : " + (i + 1) + " ****");
            this.turn();
        }
    }

}