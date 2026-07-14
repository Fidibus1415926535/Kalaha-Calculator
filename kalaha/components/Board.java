package components;
import java.awt.Point;

public class Board{
    private int[][] state;
    private int ownKalaha;
    private int oppKalaha;
    private boolean playerTurn;

    public Board(boolean playerTurn, int[][] state, int own, int opp){
        this.state = new int[2][6];
        for (int i = 0; i < 6; i++){
            this.state[0][i] = state[0][i];
            this.state[1][i] = state[1][i];
        }
        this.playerTurn = playerTurn;
        this.ownKalaha = own;
        this.oppKalaha = opp;
    }

    public Point calculateBestMove(int depth){
        Point result = new Point();
        Move minimaxRoot = new Move(this, -1);
        Point idk = minimaxRoot.calculateBestMove(depth);
        result.x = idk.x;
        result.y = idk.y;
        return result;
    }

    public void handleMove(int moveInt){
        Move move = new Move(this, moveInt);
        for (int i = 0; i < 6; i++){
            this.state[0][i] = (move.getBoard().getState()[0][i]);
            this.state[1][i] = (move.getBoard().getState()[1][i]);
        }
        this.oppKalaha = move.resultBoard.getOppKalaha();
        this.ownKalaha = move.resultBoard.getOwnKalaha();
        this.playerTurn = move.resultBoard.getPlayerTurn();
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

    public boolean playerTurn(){
        return this.playerTurn;
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
        this.playerTurn = !this.playerTurn;
    }

    public boolean getPlayerTurn(){
        return this.playerTurn;
    }

    public void incrementOwnKalaha(int value){
        this.ownKalaha += value;
    }

    public void incrementOppKalaha(int value){
        this.oppKalaha += value;
    }
}