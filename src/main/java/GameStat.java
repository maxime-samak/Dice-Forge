import bot.AbstractBot;
import bot.SavingBot;
import bot.SimpleBot;
import game.ScoreCounter;
import game.card.BuyCard;
import game.card.Inventory;
import game.card.Islands;
import game.dice.*;

import static game.DiceRoll.roll;

public class GameStat {


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
    public GameStat(int nbPlayers) {

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
     * la méthode fais passer les tours, a chaque manche il y'a un tour de chaque joueur,
     * au tour d'un joueur tout le monde lance les dés et le dis joueur peut aplliquer sa stratégie.
     * La méthode fais lancer les dès deux fois au bots si ils ne sont que deux.
     */
    public void turn() {
        for (int i = 0; i < nbPlayers; i++){
            for (int j = 0; j < nbPlayers; j++){
                if(nbPlayers==2)
                {
                    rollDices(botArray[j]);
                }
                rollDices(botArray[j]);
            }

            for(int k = 0;  k < inventory.getRecurrent(botArray[i]).size(); k++){
                Object result = inventory.getRecurrent(botArray[i]).get(k).getEffect(botArray[i]);
            }
            botArray[i].play(sanctuary, islands,inventory);
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
        ScoreCounter.updateScore(bot.getBotScore(), new DiceCard[]{dc0, dc1});
    }


    /**
     * Cette méthode démarre la partie et et lance la méthode turn() * le nombre de tour prévu.
     */
    public void begin() {

        for(int i = 0; i < nbTurn; i++) {
            this.turn();
        }
    }

    /**
     * Cette méthode terminé la partie, elle affiche les scores finaux des bots et séléctionne le gagnant de la partie.
     */
    public String end() {
        AbstractBot winner = botArray[0];
        int acc = botArray[0].getBotScore().getVictory();
        for (AbstractBot bot : botArray) {
            if (bot.getBotScore().getVictory() > acc) {
                winner = bot;
                acc = bot.getBotScore().getVictory();
            }
        }
        return winner.getBotID()+";"+winner.getClass().getName();
    }

}
