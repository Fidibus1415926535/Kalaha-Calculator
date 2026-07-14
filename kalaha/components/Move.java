package components;
import java.awt.Point;

public class Move{
    Board resultBoard; //board after the move
    int localValue; //Evaluation of the current board state

    public Move(Board old, int move){
        this.resultBoard = new Board(old.getMyTurn(), old.getState(), old.getOwnKalaha(), old.getOppKalaha());
        calculateResultBoard(move);
        this.localValue = calculateLocalValue();
    }

    private void calculateResultBoard(int move){
        int[][] state = resultBoard.getState();
        boolean myTurn = resultBoard.getMyTurn();
        int currentRow = myTurn ? 1 : 0;
        int stonesLeft = state[currentRow][move];
        state[currentRow][move] = 0;
        if (myTurn) move++;
        else move--;

        while (stonesLeft != 0){
            if (currentRow == 1){
                for (; move < 6 && stonesLeft > 0; move++){
                    state[currentRow][move]++;
                    stonesLeft--;
                }
                if (myTurn && stonesLeft > 0){
                    resultBoard.incrementOwnKalaha(1);
                    stonesLeft--;
                    if (stonesLeft == 0) this.resultBoard.toggleTurn(); //man ist nochmal dran
                }

                move--;

                if (state[1][move] == 1 && stonesLeft == 0 && myTurn && state[0][move] != 0){
                    System.out.println("Incrementing own by " + (state[1][move] + state[0][move]));
                    resultBoard.incrementOwnKalaha(state[1][move] + state[0][move]);
                    state[1][move] = 0;
                    state[0][move] = 0;
                }

                currentRow = 0;
        
            } else {
                for (; move > -1 && stonesLeft > 0; move --){
                    state[currentRow][move]++;
                    stonesLeft--;
                }
                if (!myTurn && stonesLeft > 0){
                    resultBoard.incrementOppKalaha(1);
                    stonesLeft--;
                    if (stonesLeft == 0) this.resultBoard.toggleTurn(); //man ist nochmal dran
                }

                move++;
                if (state[0][move] == 1 && stonesLeft == 0 && !myTurn && state[1][move] != 0){
                    System.out.println("Incrementing opp by " + (state[1][move] + state[0][move]));
                    resultBoard.incrementOppKalaha(state[1][move] + state[0][move]);
                    state[1][move] = 0;
                    state[0][move] = 0;
                }

                currentRow = 1;

            }
        }
        this.resultBoard.toggleTurn();
    }

    private int calculateLocalValue(){
        int result = 0;
        result += this.resultBoard.getOppKalaha() - this.resultBoard.getOwnKalaha();
        return result;
    }

    public int calculateBestMove(){
        return this.localValue;
    }

    public Board getBoard(){
        return this.resultBoard;
    }
}