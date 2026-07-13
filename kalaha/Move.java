package kalaha;
public class Move{
    Board resultBoard; //board after the move
    int move; //From where the stones were taken  
    int localValue;

    public Move(int move){
        this.move = move;
        calculateResultBoard();
        calculateLocalValue();
    }

    private void calculateResultBoard(){
        //todo
    }

    private void calculateLocalValue(){
        //todo
    }
}