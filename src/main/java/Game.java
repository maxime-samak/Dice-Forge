import bot.AbstractBot;
import bot.SavingBot;
import bot.SimpleBot;
import game.ScoreCounter;
import game.card.BuyCard;
import game.card.Islands;
import game.card.Inventory;
import game.dice.*;

import java.io.Console;

import static game.DiceRoll.roll;

/**
 * Classe Game représente celui qui gère les tours de jeux, l'affichage du vainqueur, le lancé de dés
 */
public class Game {

    private final int nbPlayers;
    private final AbstractBot[] botArray;
    private final int nbTurn;
    private final Sanctuary sanctuary;
    private final Islands islands;
    private Inventory inventory;
    private final String[] colors;

    /**
     * Créer et lance un partie avec un nombre de joueur passé en paramètre.
     * Les couleurs instanciées dans le tableau colors sont "Cyan,Vert,Violet,Jaune,Reset".
     * @param nbPlayers
     */
    public Game(int nbPlayers) {

        this.nbPlayers = nbPlayers;
        this.botArray = new AbstractBot[nbPlayers];
        this.sanctuary = new Sanctuary(nbPlayers);
        this.islands = new Islands(nbPlayers);
        this.colors = new String[]{"\033[1;96m","\033[1;92m","\033[1;95m","\033[1;93m","\033[0m"};


        if(nbPlayers == 3) {this.nbTurn = 10;}
        else {this.nbTurn = 9;}

        for(int i = 0; i < nbPlayers; i++) {
            Dice d1 = new Dice();
            Dice d2 = new Dice();
            d1.solarDiceInit();
            d2.lunarDiceInit();

            if(i%2 == 0)
                botArray[i] = new SimpleBot(d1, d2, "bot#" + (i + 1),colors[i]);
            else
                botArray[i] = new SavingBot(d1, d2, "bot#" + (i + 1),colors[i]);

            ScoreCounter.addResource(botArray[i].getBotScore(), Resource.GOLD, 3 - i);

        }
        this.inventory = new Inventory(botArray);
    }

    /**
     * Cette méthode démarre la partie et et lance la méthode turn() * le nombre de tour prévu.
     */
    public void begin() {
        System.out.println("\nEtat initial des scores:\n");
        for(int i = 0; i < nbPlayers; i++) {
            System.out.println(botArray[i].getColor()+botArray[i].getBotID() +colors[4]+ ": " + botArray[i].getBotScore().getInfos());
        }
        System.out.println();

        for(int i = 0; i < nbTurn; i++) {
            System.out.println("************ Manche: " + (i + 1) + " ************");
            this.turn();
            System.out.println("***********************************");
            System.out.println("***********************************");
        }
    }

    /**
     * la méthode fais passer les tours, a chaque manche il y'a un tour de chaque joueur,
     * au tour d'un joueur tout le monde lance les dés et le dis joueur peut aplliquer sa stratégie.
     * La méthode fais lancer les dès deux fois au bots si ils ne sont que deux.
     */
    public void turn() {
        for (int i = 0; i < nbPlayers; i++){
            System.out.println(botArray[i].getColor()+"Tour de: " + botArray[i].getBotID()+ ":"+colors[4]);
            for (int j = 0; j < nbPlayers; j++){
                System.out.println(botArray[j].getColor()+"Lancer de dés " + botArray[j].getBotID() + ":"+colors[4]);
                if(nbPlayers==2)
                {
                    rollDices(botArray[j]);
                    System.out.println("---");
                }
                rollDices(botArray[j]);
                System.out.println("\n"+botArray[j].getBotScore().getInfos() + "\n");
            }

            System.out.println("Phase d'action de " +botArray[i].getColor()+ botArray[i].getBotID()+colors[4]+" :");
            System.out.println(inventory.toString(botArray[i]));
            for(int k = 0;  k < inventory.getRecurrent(botArray[i]).size(); k++){
                Object result = inventory.getRecurrent(botArray[i]).get(k).getEffect(botArray[i]);
                System.out.println("Exécution carte renfort: " + inventory.getRecurrent(botArray[i]).get(k).getName());
                if(result!=null)
                System.out.println("--> "+inventory.getRecurrent(botArray[i]).get(k).effectToString() + " : "+result.toString());
                else
                    System.out.println("--> "+inventory.getRecurrent(botArray[i]).get(k).effectToString());
            }
            botArray[i].play(sanctuary, islands,inventory);
            printChanges(botArray[i].getBotID());
            System.out.println("____\n");
            BuyDiceCard.resetBotLog();
            BuyCard.resetBotLog();
        }
    }

    /**
     * La méthode fais lancer ses deux dès au bot passé en paramètre, affiche les résultats et mets à jour les points.
     * @param bot
     */
    private void rollDices(AbstractBot bot)
    {
        DiceCard[] roll = new DiceCard[]{roll(bot.getDice1()), roll(bot.getDice2())};
        DiceCard dc0=roll[0];
        DiceCard dc1 = roll[1];
        if(bot.choose(dc0)!=0)
            dc0=new DiceCard(dc0.getValueArray()[bot.choose(dc0)],dc0.getResourceArray()[bot.choose(dc0)]);
        if(bot.choose(dc1)!=0)
            dc1=new DiceCard(dc1.getValueArray()[bot.choose(dc1)],dc1.getResourceArray()[bot.choose(dc1)]);
        if (roll[0].getResource() == Resource.CHOICE.resourceName() && roll[1].getResource() == Resource.CHOICE.resourceName())
            System.out.println(roll[0] + " (" + dc0 + " choisi )" + "\n" + roll[1] + " (" + dc1 + " choisi )");
        else if (roll[0].getResource() == Resource.CHOICE.resourceName())
            System.out.println(roll[0] + " (" + dc0 + " choisi )" + "\n" + roll[1]);
        else if (roll[1].getResource() == Resource.CHOICE.resourceName())
            System.out.println(roll[0] + "\n" + roll[1] + " (" + dc1 + " choisi )");
        else
            System.out.println(roll[0] + "\n" + roll[1]);
        ScoreCounter.updateScore(bot.getBotScore(), new DiceCard[]{dc0, dc1});
    }


    /**
     * Cette méthode affiche les achats de faces de dès puis de cartes du bot passé en paramètre et vérifie si plus d'une action ont été réalisées par le bot pour afficher ou non l'amande de 2 SOLAR.
     * @param bot
     */
    public void printChanges(String bot) {


        if(BuyDiceCard.getFirst()==true)
        {
            printDiceCards(bot);
            if(BuyCard.getBought().size()>0)
                System.out.println("Le bot " + bot + " a payé 2 "+Resource.SOLAR.resourceName()+" pour jouer un action suplémentaire");
            printCards(bot,false);
        }
        else
        {
            if ((BuyDiceCard.getBought().size() > 0 && BuyCard.getBought().size() > 0) || BuyCard.getBought().size() > 1)
                printCards(bot,true);
            else
                printCards(bot,false);
            if(BuyCard.getBought().size() == 1)
                System.out.println("Le bot " + bot + " a payé 2 "+Resource.SOLAR.resourceName()+" pour jouer un action suplémentaire");
            printDiceCards(bot);
        }
    }

    /**
     * Affiche la liste des faces de dès achetés et les faces de dés remplacées par le bot passé en paramètre, enregistrées dans BuyDiceCard.
     * @param bot
     */
    private void printDiceCards(String bot) {
        if(BuyDiceCard.getBought().isEmpty()) {
            System.out.println("Le bot " + bot + " n'a pas acheté de faces de dés.");
        }

        else {
            for(int i = 0; i < BuyDiceCard.getBought().size(); i++) {
                System.out.println("Le bot " + bot + " a acheté la face " + BuyDiceCard.getBought().get(i) + " pour "+ BuyDiceCard.getPrices().get(i)+" "+Resource.GOLD.resourceName()+" et a remplacé la face " + BuyDiceCard.getReplaced().get(i));
            }

            System.out.println("Le bot "+bot+" n'achète plus de faces.");
        }

    }

    /**
     * Affiche les cartes achetées par le bot passé en paramètres avec leurs prix, la récompense en victoire, et si il y'en a un, l'effet immédiat de la carte.
     * @param bot
     */
    private void printCards(String bot,boolean mult) {
        if (BuyCard.getBought().isEmpty()) {
            System.out.println("Le bot " + bot + " n'a pas acheté de cartes.");
        }
        else {
            for(int i = 0; i < BuyCard.getBought().size(); i++) {
                if (mult && i ==1)
                    System.out.println("Le bot " + bot + " a payé 2 "+Resource.SOLAR.resourceName()+" pour jouer un action suplémentaire");
                if(BuyCard.getEffects().get(i)!=null)
                    System.out.println("Le bot " + bot + " a acheté la carte " + BuyCard.getBought().get(i).getName() + " pour " + BuyCard.getBought().get(i).getPrice()[0] +" "+Resource.SOLAR.resourceName()+" et "+ BuyCard.getBought().get(i).getPrice()[1] +" "+Resource.LUNAR.resourceName()+" et a gagné " + BuyCard.getBought().get(i).getVictory() +" "+Resource.VICTORY.resourceName()+" : "+BuyCard.getEffects().get(i).toString());
                else
                    System.out.println("Le bot " + bot + " a acheté la carte " + BuyCard.getBought().get(i).getName() + " pour " + BuyCard.getBought().get(i).getPrice()[0] +" "+Resource.SOLAR.resourceName()+" et "+ BuyCard.getBought().get(i).getPrice()[1] +" "+Resource.LUNAR.resourceName()+" et a gagné " + BuyCard.getBought().get(i).getVictory() +" "+Resource.VICTORY.resourceName());
            }

        }
    }

    /**
     * Cette méthode terminé la partie, elle affiche les scores finaux des bots et séléctionne le gagnant de la partie.
     */
    public void end() {
        String finalScore = "";
        AbstractBot winner = botArray[0];
        int acc = botArray[0].getBotScore().getVictory();
        for(AbstractBot bot : botArray) {
            if(bot.getBotScore().getVictory() > acc) {
                winner = bot;
                acc = bot.getBotScore().getVictory();
            }
            else {

            }
            finalScore += bot.getColor()+bot.getBotID() +colors[4]+ ": " + bot.getBotScore().getInfos() + "\n";
        }
        System.out.println();
        System.out.println("***********************************");
        System.out.println("Scores:");
        System.out.println(finalScore);
        System.out.println(winner.getColor()+winner.getBotID() +colors[4]+ " est le gagnant");
        System.out.println("***********************************");

    }

}