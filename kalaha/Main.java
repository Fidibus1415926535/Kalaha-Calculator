import components.Board;
import components.Move;
import components.Game;

public class Main{
    public static void main(String[] args){
        if (args[0] == null) {
            System.out.println("Bitte gebe an, wer starten soll (P/b)");
            System.exit(-1);
        }
        boolean playerStarting;
        if (args[1] == "b") playerStarting = false;
        else playerStarting = true;
        Game game = new Game(playerStarting);
    }
}