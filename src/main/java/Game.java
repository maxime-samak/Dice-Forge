import bot.SimpleBot;
import engine.ScoreCounter;
import objects.Dice;

public class Game {
    private SimpleBot[] botarray = new SimpleBot[4];
    private int nbPlayers;
    private int nbTurn;

    public Game(int nbPlayers) {
        Dice d1= new Dice();
        Dice d2= new Dice();
        d1.solarDiceInit();
        d2.lunarDiceInit();
        this.nbPlayers = nbPlayers;
        this.nbTurn = 10;

        for(int i = 0; i<nbPlayers;i++) {
            botarray[i] = new SimpleBot(d1, d2, "bot" + i);
        }
    }

    public void turn() {
        for (int i = 0; i<nbPlayers; i++){
            System.out.println("Tour de : " + botarray[i].getName());
            for (int j = 0; j<nbPlayers; j++){
                System.out.println("Lancer de dés " + botarray[j].getName() + " :");
                String lancer = botarray[j].SimpleStrat();
                ScoreCounter.updateScore(botarray[j].getBotscore(),lancer);
            }
            botarray[i].play();

        }
    }

    public void turns() {
        for(int i = 0; i<nbTurn; i++) {
            System.out.println("**** Tour : " + (i + 1) + " ****");
            this.turn();
        }
    }

    public void begin() {
        this.turns();
    }


}
