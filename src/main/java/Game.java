import bot.AbstractBot;
import bot.SimpleBot;
import game.ScoreCounter;
import game.dice.BuyDiceCard;
import game.dice.Dice;
import game.dice.Sanctuary;

public class Game {

    private final int nbPlayers;
    private final SimpleBot[] botArray;
    private final int nbTurn;
    private final Sanctuary sanctuary;

    public Game(int nbPlayers) {

        this.nbPlayers = nbPlayers;
        this.botArray = new SimpleBot[nbPlayers];
        this.sanctuary = new Sanctuary(nbPlayers);

        if(nbPlayers == 3) {this.nbTurn = 10;}
        else {this.nbTurn = 9;}

        for(int i = 0; i < nbPlayers; i++) {
            Dice d1 = new Dice();
            Dice d2 = new Dice();
            d1.solarDiceInit();
            d2.lunarDiceInit();

            botArray[i] = new SimpleBot(d1, d2, "bot#" + (i+1));
        }
    }

    public void turn() {
        for (int i = 0; i < nbPlayers; i++){
            System.out.println("Tour de: " + botArray[i].getBotID());
            for (int j = 0; j < nbPlayers; j++){
                System.out.println("Lancer de dés " + botArray[j].getBotID() + ":");
                String lancer = botArray[j].rollDices();
                ScoreCounter.updateScore(botArray[j].getBotScore(),lancer);
                System.out.println(botArray[j].getBotScore().getInfos() + "\n");
            }
            //System.out.println("DES DU BOT AVANT");
            //System.out.println(botArray[i].getDice1().toString());
            //System.out.println(botArray[i].getDice2().toString());
            botArray[i].play(sanctuary);
            BuyDiceCard.resetBotLog();
            //System.out.println("DES DU BOT APRES");
            //System.out.println(botArray[i].getDice1().toString());
            //System.out.println(botArray[i].getDice2().toString());
        }
    }

    public void begin() {
        for(int i = 0; i < nbTurn; i++) {
            System.out.println("**** Tour: " + (i + 1) + " ****");
            this.turn();
            System.out.println("******************\n");
        }
    }

    public void end() {
        String finalScore = "";
        String winner = botArray[0].getBotID();
        int acc = botArray[0].getBotScore().getVictory();
        for(AbstractBot bot : botArray) {
            if(bot.getBotScore().getVictory() > acc) {
                winner = bot.getBotID();
                acc = bot.getBotScore().getVictory();
            }
            else {

            }
            finalScore += bot.getBotID() + ": " + bot.getBotScore().getVictory() + "\n";
        }
        System.out.println();
        System.out.println("*************************************");
        System.out.println("Scores:");
        System.out.println(finalScore);
        System.out.println(winner + " est le gagnant");
        System.out.println("*************************************");

    }
}