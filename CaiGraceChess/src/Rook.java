import java.util.ArrayList;

public class Rook extends Piece{
    public Rook(String name, Position pos, int color) {
        super(name, pos, color);
        setName(name);
        setPos(pos);
        setColor(color);
        setPotentialMoves(new ArrayList<Position>());
        setCaptured(false);
    }
    @Override
    public void findPotentialMoves(ChessBoard board) {
        //right
        checkDirection(board, 1, 0);
        //left
        checkDirection(board, -1, 0);
        //down
        checkDirection(board, 0, -1);
        //up
        checkDirection(board, 0, 1);

    }
}
