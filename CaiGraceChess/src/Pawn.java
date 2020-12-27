import java.util.ArrayList;

public class Pawn extends Piece{
    private boolean movedOnce;

    public boolean isEnPass() {
        return enPass;
    }

    public void setEnPass(boolean enPass) {
        this.enPass = enPass;
    }

    private boolean enPass;
    public void setMovedOnce(boolean movedOnce) {
        this.movedOnce = movedOnce;
    }

    public Pawn(String name, Position pos, int color) {
        super(name, pos, color);
        setName(name);
        setPos(pos);
        setColor(color);
        movedOnce = false;
        setPotentialMoves(new ArrayList<Position>());
        setCaptured(false);
        enPass = false;
    }

    @Override
    public void findPotentialMoves(ChessBoard board) {
        // if piece diagonal from pawn
        int x = getPos().getxPos();
        int y = getPos().getyPos();
        if(x+1 <8) {
            if(movedOnce && board.getBoard()[x + 1][y + getColor()] != null) {
                getPotentialMoves().add(new Position(x+1, y+getColor()));
            }
        }
        if(x-1>=0) {
            if(movedOnce && board.getBoard()[x - 1][y + getColor()] != null) {
                getPotentialMoves().add(new Position(x-1, y+getColor()));
            }
        }

        // single step
        if(board.getBoard()[x][y + getColor()] == null) {
            getPotentialMoves().add(new Position(x, y+getColor()));
            //double step only on first turn
            if(!movedOnce && board.getBoard()[x][y + 2*getColor()] == null) {
                getPotentialMoves().add(new Position(x, y+2*getColor()));
            }
        }

        // en passant
        if(board.possibleSpace(x+1,y)) {
            if(board.getBoard()[x+1][y] instanceof Pawn && ((Pawn) board.getBoard()[x+1][y]).isEnPass() && board.getBoard()[x+1][y+getColor()] == null) {
                getPotentialMoves().add(new Position(x+1, y+getColor()));
            }
        }
        if(board.possibleSpace(x-1,y)) {
            if(board.getBoard()[x-1][y] instanceof Pawn && ((Pawn) board.getBoard()[x-1][y]).isEnPass() && board.getBoard()[x-1][y+getColor()] == null) {
                getPotentialMoves().add(new Position(x-1, y+getColor()));
            }
        }


    }

}
