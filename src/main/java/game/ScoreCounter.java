package game;

import game.dice.DiceCard;
import game.dice.Resource;

public class ScoreCounter {

    public static void updateScore(BotScore botscore, DiceCard[] roll) {

        DiceCard rollD1 = roll[0];
        DiceCard rollD2 = roll[1];

        evalueRoll(botscore,rollD1);
        evalueRoll(botscore, rollD2);
    }

    public static void updateScore(BotScore botscore, DiceCard roll) {
        evalueRoll(botscore,roll);
    }

    private static void evalueRoll(BotScore botscore, DiceCard roll) {
        int[] value = roll.getValueArray();
        Resource[] resource = roll.getResourceArray();

        if(resource.length == 1) {
            addResource(botscore, resource[0], value[0]);
        }

        else if(resource.length > 1) {
            if(resource[0] == Resource.CHOICE) {
                addResource(botscore, resource[1], value[1]);
            }

            else if(resource[0] == Resource.PLUS) {
                for(int i = 1; i < resource.length; i++) {
                    addResource(botscore, resource[i], value[i]);
                }
            }
        }
    }

    public static void addResource(BotScore botscore, Resource resource, int value) {
        switch(resource) {
            case GOLD:
                botscore.addGold(value);
                break;
            case LUNAR:
                botscore.addLunar(value);
                break;
            case SOLAR:
                botscore.addSolar(value);
                break;
            case VICTORY:
                botscore.addVictory(value);
                break;
        }
    }

    public static void payGold(BotScore botScore, int price) { botScore.removeGold(price); }

    public static void paySolar(BotScore botScore, int price) { botScore.removeSolar(price); }

    public static void payLunar(BotScore botScore, int price) { botScore.removeLunar(price); }

    //public static void addVictory(BotScore botScore, int victory) { botScore.addVictory(victory); }

    //public static void gainLunar(BotScore botScore, int lunar) { botScore.addLunar(lunar); }

    //public static void gainSolar(BotScore botScore, int solar) { botScore.addSolar(solar); }

    //public static void gainGold(BotScore botScore, int gold) { botScore.addGold(gold); }
}