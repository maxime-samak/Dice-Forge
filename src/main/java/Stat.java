import bot.AbstractBot;

import java.util.HashMap;

public class Stat {

    //initialisation de la partie
    public static void main(String[] args) {

        AbstractBot[] bots;
        int[] scores;
        int[] wins;
        if(args.length==0)
        {
            bots = new AbstractBot[4];
            scores = new int[4];
            wins = new int[4];
        }
        else
        {
            bots=new AbstractBot[Integer.parseInt(args[0])];
            scores=new int[Integer.parseInt(args[0])];
            wins= new int[Integer.parseInt(args[0])];
        }
        for(int i=0;i<500;i++) {
            GameStat game = new GameStat(Integer.parseInt(args[0]));
            game.begin();
            GameStat.Combo winner = game.end();
            bots=winner.getBots().clone();
            if(i==0)
            {
                scores=winner.getScore().clone();
                wins=winner.getScore().clone();
                for(int j=0;j<bots.length;j++)
                {
                    if(bots[j].equals(winner.getWinner()))
                        wins[j]=1;
                    else
                        wins[j]=0;
                }
            }
            else
            {
                int[] newScore=winner.getScore().clone();
                for(int j=0;j<bots.length;j++)
                {
                    scores[j]+=newScore[j];
                    if(bots[j].equals(winner.getWinner()))
                        wins[j]+=1;
                }
            }

        }


        for(int j=0;j<bots.length;j++)
        {
            System.out.println("Resumer de "+bots[j].getBotID()+"@"+bots[j].getClass().getSimpleName()+" : ");
            System.out.println("Nombres de Victoires : "+wins[j]+" ; Moyenne de points :"+scores[j]/500+"\n");
        }

    }

}
