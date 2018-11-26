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

            botArray[i] = new SimpleBot(d1, d2, "bot#" + (i+1));
            ScoreCounter.addResource(botArray[i].getBotScore(), Resource.GOLD, this.nbPlayers - i);
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
                DiceCard[] roll = roll(botArray[j].getDice1(), botArray[j].getDice2());
                ScoreCounter.updateScore(botArray[j].getBotScore(),roll);
                System.out.println(roll[0] + "\n" + roll[1]);
                System.out.println(botArray[j].getBotScore().getInfos() + "\n");
            }
            //System.out.println("DES DU BOT AVANT");
            //System.out.println(botArray[i].getDice1().toString());
            //System.out.println(botArray[i].getDice2().toString());
            System.out.println("Phase d'action de " + botArray[i].getBotID()+" :");
            //for(int k = 0;  i < CardAssignement.getListCard(botArray[i]).size(); k++){ il n'y à aucune clé bot dans la table de hachage, à réparer
            //    if(CardAssignement.getListCard(botArray[i]) != null) {
            //        CardAssignement.getListCard(botArray[i]).get(k).doEffect(botArray[i]);
            //    }
            //}
            botArray[i].play(sanctuary,islands);
            printActions(botArray[i].getBotID());
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

    public void printActions(String bot)
    {
        if(BuyDiceCard.getFeePayed())
        {
            printCardsBougth(bot);
            printDiceCardsBougth(bot);
        }
        else
        {
            printDiceCardsBougth(bot);
            printCardsBougth(bot);
        }
    }

    private void printDiceCardsBougth(String bot)
    {
        ArrayList<DiceCard> diceCardsBougth = BuyDiceCard.getBoughtArray();
        ArrayList<Integer> prices = BuyDiceCard.getPricesArray();
        if(diceCardsBougth.isEmpty())
        {
            System.out.println("Le bot " + bot + " n'a pas acheté de faces de dés.");
        }
        else
        {
            while(!diceCardsBougth.isEmpty())
            {
                if(BuyDiceCard.getFeePayed()){ System.out.println("Le bot " + bot + " a payé 2 SOLAR pour jouer une action suplémentaire");}
                System.out.println("Le bot "+bot+" a acheté la face "+diceCardsBougth.get(1)+" pour "+prices.get(0)+" GOLD et a remplacé la face "+diceCardsBougth.get(0));
                diceCardsBougth.remove(1);
                diceCardsBougth.remove(0);
                prices.remove(0);
            }
            System.out.println("Le bot "+bot+" n'achète plus de faces.");
        }

    }

    private void printCardsBougth(String bot) {
        ArrayList<Card> cardsBougth = BuyCard.getBoughtArray();

        if (cardsBougth.isEmpty()) {
            System.out.println("Le bot " + bot + " n'a pas acheté de cartes.");
        }
        else
        {
            while (!cardsBougth.isEmpty())
            {
                if(BuyCard.getFeePayed()){ System.out.println("Le bot " + bot + " a payé 2 SOLAR pour jouer une action suplémentaire");}
                System.out.println("Le bot " + bot + " a acheté la carte " + cardsBougth.get(0).name() + " pour " + cardsBougth.get(0).getPrice()[0] + " SOLAR et "+cardsBougth.get(0).getPrice()[1]+" LUNAR et a gagné " + cardsBougth.get(0).getVictory()+" VICTORY");
                cardsBougth.remove(0);
            }
        }
    }
}