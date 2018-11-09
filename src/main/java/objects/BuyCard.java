package objects;

import engine.BotScore;
import engine.ScoreCounter;

public class BuyCard {

    int buyCard(Card card, BotScore botScore, ScoreCounter scoreCounter){
        Object[][] cost = card.getPriceCard(); //{ un int , Resource.SOLAR},{un int , Resource.LUNAR}}
        int solar_cost = (int)cost[0][0];
        int lunar_cost = (int)cost[1][0];

        int solar_monney = botScore.getSolar();
        int lunar_monney = botScore.getLunar();

        if(solar_monney <= solar_cost && lunar_monney <= lunar_cost) {

            scoreCounter.deduceSolar(botScore, solar_cost);
            scoreCounter.deduceLunar(botScore, lunar_cost);
            scoreCounter.moreVictory(botScore, card.getVictoryPoints());
            return 0;
        }
        else{
            return -1;
        }
    }

}
