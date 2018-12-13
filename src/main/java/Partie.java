

public class Partie {
    //initialisation de la partie
    public static void main(String[] args) {
        Game game = new Game(4);
        System.out.println("Début de la partie:");
        game.begin();
        System.out.println("Fin de la partie.");
        game.end();
    }
}