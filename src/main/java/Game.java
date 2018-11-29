import bot.AbstractBot;
import bot.Bot;
import bot.SimpleBot;
import game.ScoreCounter;
import game.card.BuyCard;
import game.card.Card;
import game.card.CardAssignement;
import game.card.Islands;
import game.dice.*;

import java.util.ArrayList;

import static game.DiceRoll.roll;

/**
 * Classe Game représente celui qui gère les tours de jeux, l'affichage du vainqueur, le lancé de dés
 */
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

            botArray[i] = new SimpleBot(d1, d2, "bot#" + (i + 1));
            ScoreCounter.addResource(botArray[i].getBotScore(), Resource.GOLD, this.nbPlayers - i);
        }
        CardAssignement.initCardAssignement(botArray);
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
                DiceCard[] roll = roll(botArray[j].getDice1(), botArray[j].getDice2());
                DiceCard dc0=botArray[j].choose(roll[0]);
                DiceCard dc1=botArray[j].choose(roll[1]);
                if(roll[0].getResource()==Resource.CHOICE.resourceName()&&roll[1].getResource()==Resource.CHOICE.resourceName())
                    System.out.println(roll[0] +" ("+dc0+" choisi )"+ "\n" + roll[1]+" ("+dc1+" choisi )");
                else if(roll[0].getResource()==Resource.CHOICE.resourceName())
                    System.out.println(roll[0] +" ("+dc0+" choisi )"+ "\n" + roll[1]);
                else if(roll[0].getResource()==Resource.CHOICE.resourceName())
                    System.out.println(roll[0] + "\n" + roll[1]+" ("+dc1+" choisi )");
                else
                    System.out.println(roll[0] + "\n" + roll[1]);
                ScoreCounter.updateScore(botArray[j].getBotScore(),new DiceCard[]{dc0,dc1});
                System.out.println(botArray[j].getBotScore().getInfos() + "\n");
            }
            //System.out.println("DES DU BOT AVANT");
            //System.out.println(botArray[i].getDice1().toString());
            //System.out.println(botArray[i].getDice2().toString());
            System.out.println("Phase d'action de " + botArray[i].getBotID()+" :");
            for(int k = 0;  k < CardAssignement.getListCard(botArray[i]).size()-1; k++){
                System.out.println("Exécution carte renfort: " + CardAssignement.getListCard(botArray[i]).get(k).name());
                System.out.println(CardAssignement.getListCard(botArray[i]).get(k).toString());
                Object result=CardAssignement.getListCard(botArray[i]).get(k).doEffect(botArray[i]);
                if(result!=null)
                    System.out.println(botArray[i].getBotID()+" a reçu : "+result.toString());
                System.out.println(botArray[i].getBotScore().getInfos() + "\n");
            }
            botArray[i].play(sanctuary,islands);
            printChanges(botArray[i].getBotID());
            System.out.println("____\n");
            BuyDiceCard.resetBotLog();
            BuyCard.resetBotLog();
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

    public void printChanges(String bot) {

        printDiceCards(bot);
        if ((BuyDiceCard.getBought().size() > 0 && BuyCard.getBought().size() > 0) || BuyCard.getBought().size() > 1) {
            System.out.println("Le bot " + bot + " a payé 2 SOLAR pour jouer une action supplémentaire");

        }
        printCards(bot);

    }

    private void printDiceCards(String bot) {
        if(BuyDiceCard.getBought().isEmpty()) {
            System.out.println("Le bot " + bot + " n'a pas acheté de faces de dés.");
        }

        else {
            for(int i = 0; i < BuyDiceCard.getBought().size(); i++) {
                System.out.println("Le bot " + bot + " a acheté la face " + BuyDiceCard.getBought().get(0) + " pour "+ BuyDiceCard.getPrices().get(0) + " GOLD et a remplacé la face " + BuyDiceCard.getReplaced().get(0));
            }

            System.out.println("Le bot "+bot+" n'achète plus de faces.");
        }

    }

    private void printCards(String bot) {
        if (BuyCard.getBought().isEmpty()) {
            System.out.println("Le bot " + bot + " n'a pas acheté de cartes.");
        }
        else {
            for(int i = 0; i < BuyCard.getBought().size(); i++) {
                System.out.println("Le bot " + bot + " a acheté la carte " + BuyCard.getBought().get(i).name() + " pour " + BuyCard.getBought().get(i).getPrice()[0] + " SOLAR et "+ BuyCard.getBought().get(i).getPrice()[1] + " LUNAR et a gagné " + BuyCard.getBought().get(i).getVictory() + " VICTORY");
            }
        }
    }

}