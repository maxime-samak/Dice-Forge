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
        if(resource == Resource.PLUS.resourceName()) {
            for(int i = 3; i < token.length; i += 2) {

                if (token[i].equals(Resource.SOLAR.resourceName())) {
                    botscore.addSolar(Integer.parseInt(token[i - 1]));
                }
                else if (token[i].equals(Resource.LUNAR.resourceName())) {
                    botscore.addLunar(Integer.parseInt(token[i - 1]));
                }
                else if (token[i].equals(Resource.VICTORY.resourceName())) {
                    botscore.addVictory(Integer.parseInt(token[i - 1]));
                }
                else {
                    botscore.addGold(Integer.parseInt(token[i - 1]));
                }
            }
        }

        if(resource == Resource.CHOICE.resourceName()) {
            if (token[3].equals(Resource.SOLAR.resourceName())) {
                botscore.addSolar(Integer.parseInt(token[2]));
            }
            else if (token[3].equals(Resource.LUNAR.resourceName())) {
                botscore.addLunar(Integer.parseInt(token[2]));
            }
            else if (token[3].equals(Resource.VICTORY.resourceName())) {
                botscore.addVictory(Integer.parseInt(token[2]));
            }
            else {
                botscore.addGold(Integer.parseInt(token[2]));
            }
        }

        else{

            if (resource.equals(Resource.SOLAR.resourceName())) {
                botscore.addSolar(value);
            } else if (resource.equals(Resource.LUNAR.resourceName())) {
                botscore.addLunar(value);
            } else if (resource.equals(Resource.VICTORY.resourceName())) {
                botscore.addVictory(value);
            } else {
                botscore.addGold(value);
            }
        }
    }

    public static void payGold(BotScore botScore, int price) { botScore.removeGold(price); }

    public static void paySolar(BotScore botScore, int price) { botScore.removeSolar(price); }

    public static void payLunar(BotScore botScore, int price) { botScore.removeLunar(price); }

    public static void addVictory(BotScore botScore, int victory) { botScore.addVictory(victory); }
}