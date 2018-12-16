package bot;

import game.ScoreCounter;
import game.card.BuyCard;
import game.card.Inventory;
import game.card.Islands;
import game.dice.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static game.dice.Resource.GOLD;
import static game.dice.Resource.SOLAR;

public class OneBuyBotTest {

        private OneBuyBot bot1;
        private Dice d1;
        private Dice d2;
        private Sanctuary sanctuary;
        private Islands islands;
        private Inventory inventory;
        private ScoreCounter score;

        @Before
        public void setup() {
            d1 = new Dice();
            d1.solarDiceInit();
            d2 = new Dice();
            d2.lunarDiceInit();
            bot1 = new OneBuyBot(d1,d2,"bot1","\033[0m");
            sanctuary = new Sanctuary(2);
            islands = new Islands(2);
            inventory = new Inventory(new AbstractBot[]{bot1});
            score = new ScoreCounter();
            BuyDiceCard.resetBotLog();
            BuyCard.resetBotLog();
        }

        @Test
        public void SimpleBotTest(){

            Assert.assertNotNull(bot1);

        }

        @Test//Test méthode play == buyInOrder
        public void play() {
            BuyCard.resetBotLog();
            BuyDiceCard.resetBotLog();
            //Cas 2 : faces achetées > 0 ou carte achetées == 0
            Assert.assertFalse(BuyDiceCard.getBought().size() == 0 && BuyCard.getBought().size() > 0);//assert false pour tester le else
            //Cas 2.1 : Gold > Autres ressources donc achète une face.
            score.updateScore(bot1, new DiceCard[]{new DiceCard(6, Resource.GOLD), new DiceCard(6, Resource.GOLD)});
            Assert.assertTrue(bot1.getBotScore().getGold() > bot1.getBotScore().getLunar() && bot1.getBotScore().getGold() > bot1.getBotScore().getSolar());
            bot1.play(sanctuary, islands, inventory); //achète une face et essaye d'acheter une carte sans succés.
            Assert.assertEquals(1, BuyDiceCard.getBought().size());
            Assert.assertEquals(0, BuyCard.getBought().size());

            //Cas 2.2 : Gold <= Autres ressources
            BuyCard.resetBotLog();
            BuyDiceCard.resetBotLog();
            Assert.assertFalse(BuyDiceCard.getBought().size() == 0 && BuyCard.getBought().size() > 0);//assert false pour tester le else
            Assert.assertFalse(bot1.getBotScore().getGold() > bot1.getBotScore().getLunar() && bot1.getBotScore().getGold() > bot1.getBotScore().getSolar());
            score.updateScore(bot1, new DiceCard[]{new DiceCard(6, Resource.LUNAR), new DiceCard(6, Resource.LUNAR)});
            score.updateScore(bot1, new DiceCard[]{new DiceCard(6, Resource.SOLAR), new DiceCard(6, Resource.SOLAR)});
            bot1.play(sanctuary, islands, inventory); //achète une carte et rejoue
            Assert.assertNotEquals(0, BuyCard.getBought().size());


            //Cas 1 : faces achetées == 0 et carte achetées > 0
            BuyDiceCard.resetBotLog();
            Assert.assertTrue(BuyDiceCard.getBought().size() == 0 && BuyCard.getBought().size() > 0);//On vérifie qu'on est bien dans le cas 1
            //Cas 1.1 : Gold > Autres ressources
            score.updateScore(bot1, new DiceCard[]{new DiceCard(6, Resource.GOLD), new DiceCard(6, Resource.GOLD)});
            Assert.assertTrue(bot1.getBotScore().getGold() > bot1.getBotScore().getLunar() && bot1.getBotScore().getGold() > bot1.getBotScore().getSolar());//vérif 1.1
            int tmp = BuyCard.getBought().size();
            score.updateScore(bot1, new DiceCard[]{new DiceCard(6, Resource.GOLD), new DiceCard(6, Resource.GOLD)});
            bot1.play(sanctuary, islands, inventory); //A déjà acheter une carte, n'achète donc rien.
            Assert.assertEquals(0, BuyDiceCard.getBought().size());
            Assert.assertEquals(tmp, BuyCard.getBought().size());//garde l'ancienne valeur.

            //Cas 1.2 : Gold <= Autres ressources
            BuyDiceCard.resetBotLog();
            Assert.assertTrue(BuyDiceCard.getBought().size() == 0 && BuyCard.getBought().size() > 0);//vérif 1
            score.payGold(bot1.getBotScore(),12);
            score.updateScore(bot1, new DiceCard[]{new DiceCard(6, Resource.LUNAR), new DiceCard(6, Resource.LUNAR)});
            score.updateScore(bot1, new DiceCard[]{new DiceCard(6, Resource.SOLAR), new DiceCard(6, Resource.SOLAR)});
            Assert.assertFalse(bot1.getBotScore().getGold() > bot1.getBotScore().getLunar() && bot1.getBotScore().getGold() > bot1.getBotScore().getSolar());//vérif 1.2
            bot1.play(sanctuary, islands, inventory); //achète une carte
            Assert.assertEquals(1, BuyCard.getBought().size());
            Assert.assertEquals(0, BuyDiceCard.getBought().size());
        }

        @Test
        public void diceShopping() {
        }

        @Test
        public void diceShopping1() {
            BuyDiceCard.resetBotLog();
            BuyCard.resetBotLog();
            //Budget d'achat = 0.
            Assert.assertFalse(bot1.diceShopping(sanctuary,12));
            Assert.assertFalse(bot1.diceShopping(sanctuary,8));
            Assert.assertFalse(bot1.diceShopping(sanctuary,6));
            Assert.assertFalse(bot1.diceShopping(sanctuary,5));
            Assert.assertFalse(bot1.diceShopping(sanctuary,4));
            Assert.assertFalse(bot1.diceShopping(sanctuary,3));
            Assert.assertFalse(bot1.diceShopping(sanctuary,2));


            score.updateScore(bot1, new DiceCard[]{new DiceCard(6, Resource.GOLD), new DiceCard(6, Resource.GOLD)}); //Budget d'achat = 12.

            Assert.assertTrue(bot1.diceShopping(sanctuary,8));
            Assert.assertFalse(bot1.diceShopping(sanctuary,2));
            Assert.assertFalse(bot1.diceShopping(sanctuary,2));

            BuyDiceCard.resetBotLog();
            score.updateScore(bot1, new DiceCard[]{new DiceCard(6, Resource.GOLD), new DiceCard(6, Resource.GOLD)}); //Budget d'achat = 12.

            Assert.assertTrue(bot1.diceShopping(sanctuary,6));
            Assert.assertFalse(bot1.diceShopping(sanctuary,3));
            Assert.assertFalse(bot1.diceShopping(sanctuary,3));


            BuyDiceCard.resetBotLog();
            sanctuary = new Sanctuary(2);
            score.updateScore(bot1, new DiceCard[]{new DiceCard(6, Resource.GOLD), new DiceCard(6, Resource.GOLD)}); //Budget d'achat = 12.

            //pool 3 = {A,A,B,B}
            Assert.assertTrue(bot1.diceShopping(sanctuary,3)); //Achat carte A
            Assert.assertFalse(bot1.diceShopping(sanctuary,3)); //Achat carte B
            Assert.assertFalse(bot1.diceShopping(sanctuary,3)); //Si achat -> doublon


            BuyDiceCard.resetBotLog();
            sanctuary = new Sanctuary(2);
            score.updateScore(bot1, new DiceCard[]{new DiceCard(6, Resource.GOLD), new DiceCard(6, Resource.GOLD)}); //Budget d'achat = 12.

            //pool 3 = {A,A,B,B}
            Assert.assertTrue(bot1.diceShopping(sanctuary,3)); //Achat carte A
            Assert.assertFalse(bot1.diceShopping(sanctuary,3)); //Achat carte B
            BuyDiceCard.resetBotLog(); //Reset de l'historique d'achat.
            Assert.assertTrue(bot1.diceShopping(sanctuary,3)); //Achat carte A ou B -> devrait être valide
        }

        @Test
        public void cardShopping() {
        }

        @Test
        public void lunarShopping() {
        }

        @Test
        public void solarShopping() {
        }

        @Test
        public void shopIslandTen() {
        }

        @Test
        public void getPreferredResource() {
            score.updateScore(bot1, new DiceCard[]{new DiceCard(1, Resource.SOLAR), new DiceCard(1, Resource.LUNAR)});
            Assert.assertTrue(bot1.getBotScore().getGold()<=bot1.getBotScore().getSolar()&&bot1.getBotScore().getGold()<=bot1.getBotScore().getLunar());
            Assert.assertEquals(GOLD, bot1.getPreferredResource());

            score.updateScore(bot1, new DiceCard[]{new DiceCard(1, Resource.LUNAR), new DiceCard(2, Resource.GOLD)});
            Assert.assertTrue(bot1.getBotScore().getSolar()<bot1.getBotScore().getGold()&&bot1.getBotScore().getSolar()<=bot1.getBotScore().getLunar());
            Assert.assertEquals(SOLAR, bot1.getPreferredResource());

            score.updateScore(bot1, new DiceCard[]{new DiceCard(1, Resource.LUNAR), new DiceCard(0, Resource.GOLD)});
            Assert.assertFalse(bot1.getBotScore().getGold()<=bot1.getBotScore().getSolar()&&bot1.getBotScore().getGold()<=bot1.getBotScore().getLunar()
                    && bot1.getBotScore().getSolar()<bot1.getBotScore().getGold()&&bot1.getBotScore().getSolar()<=bot1.getBotScore().getLunar());
            //Assert.assertEquals(LUNAR, bot1.getPreferredResource()); //getPreferredResource() ne renvoie jamais LUNAR
        }

        @Test
        public void choose() {
            Assert.assertEquals(0, bot1.choose(new DiceCard(1, SOLAR)));
            Assert.assertEquals(1, bot1.choose(new DiceCard(new int[]{0, 3, 2}, new Resource[]{Resource.CHOICE, Resource.VICTORY, Resource.GOLD})));
            score.updateScore(bot1, new DiceCard[]{new DiceCard(1, Resource.SOLAR), new DiceCard(1, Resource.LUNAR)});
            Assert.assertEquals(1, bot1.choose(new DiceCard(new int[]{0, 3, 2}, new Resource[]{Resource.CHOICE, Resource.GOLD, Resource.LUNAR})));
            score.updateScore(bot1, new DiceCard[]{new DiceCard(1, Resource.LUNAR), new DiceCard(2, Resource.GOLD)});
            Assert.assertEquals(2, bot1.choose(new DiceCard(new int[]{0, 3, 2}, new Resource[]{Resource.CHOICE, Resource.SOLAR, Resource.LUNAR})));
            //L'ordre dans lequelle est défini la face de dé compte.
        }

}
