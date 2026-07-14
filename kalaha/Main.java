import components.Board;
import components.Move;
import components.Game;

public class Main{
    public static void main(String[] args){
        if (args.length == 0) {
            System.out.println("Bitte gebe an, wer starten soll (P/b)");
            System.exit(-1);
        }
        boolean playerStarting;
        if ("b".equals(args[0])) playerStarting = false;
        else playerStarting = true;
        Game game = new Game(playerStarting);
    }
}