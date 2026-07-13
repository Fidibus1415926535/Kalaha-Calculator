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
        Point bestResult = new Point();
        Move[] moves = new Move[6];
        for (int i = 0; i < 6; i++){
            moves[i] = new Move(i, depth - 1);
        }
        bestResult.x = -100;
        for (int i = 0; i < 6; i++){
            int currentMoveResult = moves[i].calculateBestMove(depth - 1);
            if (currentMoveResult > bestResult.x){
                bestResult.x = currentMoveResult;
                bestResult.y = i;
            }
        }
        return bestResult;
    }

    public void handleMove(int move){
        //todo
    }

    @Override
    public String toString(){
        String string = " ";
        for (int i = 0; i < 6; i++){
            string += board[0][i] + " ";
        }
        string += "\n" + oppKalaha + "            " + ownKalaha + "/n";
        for (int i = 0; i < 6; i++){
            string += board[1][i] + " ";
        }
        return string;
    }

    public int getOwnKalaha(){
        return this.ownKalaha;
    }

    public int getOppKalaha(){
        return this.oppKalaha;
    }

    public boolean myTurn(){
        return this.myTurn;
    }
}