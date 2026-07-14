package components;
import java.awt.Point;

public class Board{
    private int[][] state;
    private int ownKalaha;
    private int oppKalaha;
    private boolean myTurn;

    public Board(boolean myTurn, int[][] state, int own, int opp){
        this.state = new int[2][6];
        for (int i = 0; i < 6; i++){
            this.state[0][i] = state[0][i];
            this.state[1][i] = state[1][i];
        }
        this.myTurn = myTurn;
        this.ownKalaha = own;
        this.oppKalaha = opp;
    }

    public Point calculateBestMove(){
        Point bestResult = new Point();
        Move[] moves = new Move[6];
        for (int i = 0; i < 6; i++){
            moves[i] = new Move(this, i);
        }
        bestResult.x = -100;
        for (int i = 0; i < 6; i++){
            int currentMoveResult = moves[i].calculateBestMove();
            if (currentMoveResult > bestResult.x){
                bestResult.x = currentMoveResult;
                bestResult.y = i;
            }
        }
        return bestResult;
    }

    public void handleMove(int moveInt){
        Move move = new Move(this, moveInt);
        for (int i = 0; i < 6; i++){
            this.state[0][i] = (move.getBoard().getState()[0][i]);
            this.state[1][i] = (move.getBoard().getState()[1][i]);
        }
        this.oppKalaha = move.resultBoard.getOppKalaha();
        this.ownKalaha = move.resultBoard.getOwnKalaha();
        this.myTurn = move.resultBoard.getMyTurn();
    }

    @Override
    public String toString(){
        String string = " ";
        for (int i = 0; i < 6; i++){
            string += state[0][i] + " ";
        }
        string += "\n" + oppKalaha + "            " + ownKalaha + "\n ";
        for (int i = 0; i < 6; i++){
            string += state [1][i] + " ";
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

    public int[][] getState(){
        return this.state;
    }

    public void setOwnKalaha(int value){
        this.ownKalaha = value;
    }

    public void setOppKalaha(int value){
        this.oppKalaha = value;
    }

    public void toggleTurn(){
        this.myTurn = !this.myTurn;
    }

    public boolean getMyTurn(){
        return this.myTurn;
    }

    public void incrementOwnKalaha(int value){
        this.ownKalaha++;
    }

    public void incrementOppKalaha(int value){
        this.oppKalaha++;
    }
}