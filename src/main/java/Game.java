import bot.AbstractBot;
import bot.SimpleBot;
import engine.ScoreCounter;
import objects.Dice;
import objects.Sanctuary;

public class Game {

    private final int nbPlayers;
    private final SimpleBot[] botarray;
    private final int nbTurn;
    private final Sanctuary sanctuary;

    public Game(int nbPlayers) {

        this.nbPlayers = nbPlayers;
        botarray = new SimpleBot[nbPlayers];
        this.sanctuary=new Sanctuary(nbPlayers);

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
                String lancer = botarray[j].rollDices();
                ScoreCounter.updateScore(botarray[j].getBotscore(),lancer);
            }
            botarray[i].play(sanctuary);
        }
    }

    public void begin() {
        for(int i = 0; i < nbTurn; i++) {
            System.out.println("**** Tour : " + (i + 1) + " ****");
            this.turn();
        }
    }

    public void end() {
        String finalScore = "";
        String winner = botarray[0].getBotID();
        int acc = botarray[0].getBotscore().getVictory();
        for(AbstractBot bot : botarray) {
            if(bot.getBotscore().getVictory() > acc) {
                winner = bot.getBotID();
                acc = bot.getBotscore().getVictory();
            }
            else {

            }
            finalScore += bot.getBotID() + ": " + bot.getBotscore().getVictory() + "\n";
        }
        System.out.println();
        System.out.println("*************************************");
        System.out.println("Scores:");
        System.out.println(finalScore);
        System.out.println(winner + " is the winner");
        System.out.println("************************************* \n");

    }

}