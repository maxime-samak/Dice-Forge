package engine;

import bot.BotScore;

public class ScoreCounter {

    public static void updateScore(BotScore botscore, String roll) {
        String[] scoreToken = roll.split("%");
        String[] d1Token = scoreToken[0].split("@");
        String[] d2Token = scoreToken[1].split("@");

        int d1Value = Integer.parseInt(d1Token[0]);
        String d1Resource = d1Token[1];
        int d2Value = Integer.parseInt(d2Token[0]);
        String d2Resource = d2Token[1];

        if(d1Token.equals(d1Resource)) {
            botscore.addSolar(d1Value);
        }
        else { botscore.addGold(d1Value);}

        if(d2Token.equals(d2Resource)) {
            botscore.addSolar(d2Value);
        }
        else if(d2Token.equals(d2Resource)) {
            botscore.addVictory(d2Value);
        }
        else { botscore.addGold(d2Value);}
    }
}
