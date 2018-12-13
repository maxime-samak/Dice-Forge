import bot.AbstractBot;

import java.util.HashMap;

public class Stat {

    //initialisation de la partie
    public static void main(String[] args) {
        HashMap<String,Integer> nbVictory=new HashMap<>();
        for(int i=0;i<500;i++) {
            GameStat game = new GameStat(4);
            game.begin();
            String winner = game.end();
            if (nbVictory.containsKey(winner))
                nbVictory.replace(winner, nbVictory.get(winner) + 1);
            else {
                nbVictory.put(winner, 1);
            }
        }

        System.out.println(nbVictory.toString());
    }

}
