

public class Partie {
    //initialisation de la partie
    public static void main(String[] args) {
        Game game;
        if(args.length!=0) {
            if (args.length - 1 == Integer.parseInt(args[0]))
                game = new Game(Integer.parseInt(args[0]), args);
            else
                game = new Game(Integer.parseInt(args[0]));
        }
        else
            game = new Game(2);
        System.out.println("Début de la partie:");
        game.begin();
        System.out.println("Fin de la partie.");
        game.end();
    }
}