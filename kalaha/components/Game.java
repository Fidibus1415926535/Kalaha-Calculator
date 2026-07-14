package components;
import java.awt.Point;
import java.util.Scanner;

public class Game{

    boolean running = true;
    
    public Game(){
        runGame();
    }

    private void runGame(){
        int startingDepth = 6;
        int[][] state = new int[2][6];
        for (int i = 0; i < 6; i++){
            state[0][i] = 4;
            state[1][i] = 4;
        }
        Board board = new Board(false, state, 0, 0);
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        System.out.println("Startaufstellung:  " + "\n" + board.toString() + "\n-------------");
        while (running){
            System.out.println("Player Turn: " + board.playerTurn());
            if (board.playerTurn()){
                System.out.print(">>> ");
                board.handleMove(sc.nextInt());
                System.out.println(board.toString());
            }
            else{
                Point bestMove = board.calculateBestMove(startingDepth); 
                System.out.println("Bester Move: "+ bestMove.y  + " Evaluation: " + bestMove.x);
                if (bestMove.y != -1){
                    board.handleMove(bestMove.y);
                    System.out.println(board.toString());
                }
                else {
                    System.out.println("Forced to skip");
                    board.toggleTurn();
                }
            }
            System.out.println("-----------------------------");
        }
    }
}