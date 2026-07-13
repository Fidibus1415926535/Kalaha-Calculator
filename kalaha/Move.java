package kalaha;

import java.awt.Point;

public class Move{
    Board resultBoard; //board after the move
    int move; //From where the stones were taken  
    int localValue; //Evaluation of the current board state

    public Move(int move, int depth){
        this.move = move;
        calculateResultBoard();
        this.localValue = calculateLocalValue();
        calculateBestMove(depth);
    }

    private void calculateResultBoard(){
        //todo
    }

    private int calculateLocalValue(){
        int result = 0;
        result += this.resultBoard.getOwnKalaha() - this.resultBoard.getOppKalaha();
        return result;
    }

    public int calculateBestMove(int depth){
        if (depth == 0) return this.localValue;
        else System.out.println("noch nicht bearbeitet");
        return 0;
    }
}