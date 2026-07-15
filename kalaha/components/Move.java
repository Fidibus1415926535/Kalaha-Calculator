package components;
import java.awt.Point;

public class Move{
    Board resultBoard; //board after the move
    int move;

    public Move(Board old, int move){
        this.move = move;
        this.resultBoard = new Board(old.getPlayerTurn(), old.getState(), old.getOwnKalaha(), old.getOppKalaha());
        calculateResultBoard(this.move);
    }

    private void calculateResultBoard(int move){
        if (move == -1){ //copy board into move
            return;
        }
        boolean lastInKalaha = false;
        int[][] state = resultBoard.getState();
        boolean playerTurn = resultBoard.getPlayerTurn();
        int currentRow = playerTurn ? 1 : 0;
        int stonesLeft = state[currentRow][move];
        state[currentRow][move] = 0;
        if (playerTurn) move++;
        else move--;

        while (stonesLeft != 0){
            if (currentRow == 1){
                for (; move < 6 && stonesLeft > 0; move++){
                    state[currentRow][move]++;
                    stonesLeft--;
                }
                if (playerTurn && stonesLeft > 0){
                    resultBoard.incrementOwnKalaha(1);
                    stonesLeft--;
                    if (stonesLeft == 0) {
                        this.resultBoard.toggleTurn(); //man ist nochmal dran
                        lastInKalaha = true;
                    }
                }

                move--;

                if (state[1][move] == 1 && stonesLeft == 0 && playerTurn && state[0][move] != 0 && !lastInKalaha){
                    //System.out.println("Incrementing own by " + (state[1][move] + state[0][move]));
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
                if (!playerTurn && stonesLeft > 0){
                    resultBoard.incrementOppKalaha(1);
                    stonesLeft--;
                    if (stonesLeft == 0) {
                        this.resultBoard.toggleTurn(); //man ist nochmal dran
                        lastInKalaha = true;
                    }
                }

                move++;
                
                if (state[0][move] == 1 && stonesLeft == 0 && !playerTurn && state[1][move] != 0 && !lastInKalaha){
                    //System.out.println("Incrementing opp by " + (state[1][move] + state[0][move]));
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

    public Point calculateBestMove(int depth){
        //System.out.println(this.resultBoard.toString() + "\n\n");

        boolean playerTurn = this.resultBoard.getPlayerTurn();
        if (depth == 0){ //recursive call
            return new Point(this.calculateLocalValue(), this.move);
        }
        
        Move [] moves = new Move[6];

        int row = playerTurn ? 1 : 0;  
        for (int i = 0; i < 6; i++){
            if (this.resultBoard.getState()[row][i] != 0){
                moves[i] = new Move(this.resultBoard, i);
            }
        }

        if (playerTurn){
            Point minResult = new Point(100, -1); //das ist das Result, dass anzeigt wenn nichts getroffen wurde
            for (int i = 0; i < 6; i++){
                //System.out.println("\nPlayerturn " + i + ":");
                if (moves[i] != null){
                    Point currentResult = moves[i].calculateBestMove(depth - 1);
                    if (currentResult == null) continue; //skippt alle results, die nichts treffen. Wenn alle failen kommt 
                    //der Default von minResult zurück inst spiel
                    if (currentResult.x < minResult.x){
                        minResult.x = currentResult.x;
                        minResult.y = i;
                    }
                }
            }
            //System.out.println("Returning " + minResult.y + " with eval: " + minResult.x);
            if (minResult.y == -1) return null; 
            return (minResult);
        }
        else{
            Point maxResult = new Point(-100, -1);
            for (int i = 0; i < 6; i++){
                //System.out.println("\nBottturn " + i + ":");
                if (moves[i] != null){
                    Point currentResult = moves[i].calculateBestMove(depth - 1);
                    if (currentResult == null) continue;
                    if (currentResult.x > maxResult.x){
                        maxResult.x = currentResult.x;
                        maxResult.y = i;
                    }
                }
            }
            //System.out.println("Returning: " + maxResult.y + " with eval: " + maxResult.x);
            if (maxResult.y == -1) return null;
            return (maxResult);
        }
    }

    public Board getBoard(){
        return this.resultBoard;
    }
}