package kalaha;

import java.awt.Point;

public class Board{
    private int[][] board = new int[2][6];
    private int ownKalaha = 0;
    private int oppKalaha = 0;
    private boolean myTurn;

    public Board(boolean myTurn){
        for (int i = 0; i < 6; i++){
            this.board[0][i] = 4;
            this.board[1][i] = 4;
            this.myTurn = myTurn;
        }
    }

    public Point calculateBestMove(int depth){
        Point result = new Point();
        Move[] moves = new Move[6];
        for (int i = 0; i < 6; i++){
            moves[i] = new Move(i);
        }
        return result;
    }
}