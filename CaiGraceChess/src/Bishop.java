import java.util.ArrayList;

public class Bishop extends Piece{
    public Bishop(String name, Position pos, int color) {
        super(name, pos, color);
        setName(name);
        setPos(pos);
        setColor(color);
        setPotentialMoves(new ArrayList<Position>());
        setCaptured(false);
    }    @Override
    public void findPotentialMoves(ChessBoard board) {

      //up right
        checkDirection(board, 1,1);
      //up left
        checkDirection(board, -1,1);
      //down right
        checkDirection(board, 1,-1);
      //down left
        checkDirection(board, -1,-1);

    }

}


