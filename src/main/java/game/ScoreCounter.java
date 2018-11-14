package game;

import game.dice.Resource;

public class ScoreCounter {

    public static void updateScore(BotScore botscore, String roll) {
        String[] scoreToken = roll.split("%");
        String[] d1Token = scoreToken[0].split("@");
        String[] d2Token = scoreToken[1].split("@");

        evalueToken(botscore, d1Token);
        evalueToken(botscore, d2Token);
    }

    private static void evalueToken(BotScore botscore, String[] token) {
        int value = Integer.parseInt(token[0]);
        String resource = token[1];

        if(resource.equals(Resource.SOLAR.resourceName())) {
            botscore.addSolar(value);
        }

        else if(resource.equals(Resource.LUNAR.resourceName())) {
            botscore.addLunar(value);
        }

        else if(resource.equals(Resource.VICTORY.resourceName())) {
            botscore.addVictory(value);
        }

        else { botscore.addGold(value);}
    }

    public static void payGold(BotScore botScore, int price) { botScore.removeGold(price); }

    public static void paySolar(BotScore botScore, int price) { botScore.removeSolar(price); }

    public static void payLunar(BotScore botScore, int price) { botScore.removeLunar(price); }

    public static void addVictory(BotScore botScore, int victory) { botScore.addVictory(victory); }
}