package components;
import java.awt.Point;
import java.util.Scanner;

public class Game{

    boolean running = true;
    
    public Game(){
        runGame();
    }

    private void runGame(){
        int depth = 1;
        int[][] state = new int[2][6];
        for (int i = 0; i < 6; i++){
            state[0][i] = 4;
            state[1][i] = 4;
        }
        Board board = new Board(true, state, 0, 0);
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        System.out.println("Startaufstellung:  " + "\n" + board.toString());
        while (running){
            if (board.myTurn()){
                System.out.print(">>> ");
                board.handleMove(sc.nextInt());
                System.out.println(board.toString());
            }
            else{
                Point bestMove = board.calculateBestMove(); 
                System.out.println("Bester Move: "+ bestMove.y  + " Evaluation: " + bestMove.y);
                board.handleMove(bestMove.y);
                System.out.println(board.toString());
            }
        }
    }
}