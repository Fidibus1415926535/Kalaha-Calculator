package kalaha;

import java.awt.Point;
import java.util.Scanner;

public class Main{

    public static void main(String[] args){
        int depth = 1;
        Board board = new Board(true);
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        System.out.println("Startaufstellung: \n" + board.toString());
        while (running){
            if (board.myTurn()){
                System.out.println("Gebe ein aus welcher Grube die Steine genommen werden sollen\n");
                board.handleMove(sc.nextInt());
            }
            else{
                Point bestMove = board.calculateBestMove(depth); 
                System.out.println("Bester Move:  Evaluation: " + bestMove.x + "Zug: " + bestMove.y);
                board.handleMove(bestMove.y);
                System.out.println(board.toString());
            }
            System.out.println(board.toString());
        }
    }
}