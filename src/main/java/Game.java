import bot.AbstractBot;
import bot.SimpleBot;
import game.ScoreCounter;
import game.card.Islands;
import game.dice.BuyDiceCard;
import game.dice.Dice;
import game.dice.Sanctuary;

import static game.DiceRoll.roll;

public class Game {

    private final int nbPlayers;
    private final SimpleBot[] botArray;
    private final int nbTurn;
    private final Sanctuary sanctuary;
    private final Islands islands;

    /**
     * Créer et lance un partie avec un nombre de joueur passé en paramètre.
     * @param nbPlayers
     */
    public Game(int nbPlayers) {

        this.nbPlayers = nbPlayers;
        this.botArray = new SimpleBot[nbPlayers];
        this.sanctuary = new Sanctuary(nbPlayers);
        this.islands= new Islands(nbPlayers);

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

    /**
     * la méthode fais passer les tours, a chaque tour de "jeu" il y'a un tour de chaque joueur,
     * au tour d'un joueur tout le monde lance les dés et le dis joueur peut aplliquer sa stratégie.
     */
    public void turn() {
        for (int i = 0; i < nbPlayers; i++){
            System.out.println("Tour de: " + botArray[i].getBotID());
            for (int j = 0; j < nbPlayers; j++){
                System.out.println("Lancer de dés " + botArray[j].getBotID() + ":");
                String lancer = roll(botArray[j].getDice1(), botArray[j].getDice2());
                ScoreCounter.updateScore(botArray[j].getBotScore(),lancer);
                System.out.println(botArray[j].getBotScore().getInfos() + "\n");
            }
            //System.out.println("DES DU BOT AVANT");
            //System.out.println(botArray[i].getDice1().toString());
            //System.out.println(botArray[i].getDice2().toString());
            System.out.println("Phase d'action de "+botArray[i].getBotID()+" :");
            botArray[i].play(sanctuary,islands);
            System.out.println("____\n");
            BuyDiceCard.resetBotLog();
            //System.out.println("DES DU BOT APRES");
            //System.out.println(botArray[i].getDice1().toString());
            //System.out.println(botArray[i].getDice2().toString());
        }
    }

    public void begin() {
        System.out.println("\nEtat initial des scores:\n");
        for(int i = 0; i < nbPlayers; i++) {
            System.out.println(botArray[i].getBotID() + ": " + botArray[i].getBotScore().getInfos());
        }
        System.out.println();

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
            finalScore += bot.getBotID() + ": " + bot.getBotScore().getInfos() + "\n";
        }
        System.out.println();
        System.out.println("*************************************");
        System.out.println("Scores:");
        System.out.println(finalScore);
        System.out.println(winner + " est le gagnant");
        System.out.println("*************************************");

    }
}