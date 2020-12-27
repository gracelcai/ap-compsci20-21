import java.util.ArrayList;

public class Knight extends Piece{
    public Knight(String name, Position pos, int color) {
        super(name, pos, color);
        setName(name);
        setPos(pos);
        setColor(color);
        setPotentialMoves(new ArrayList<Position>());
        setCaptured(false);
    }
    @Override
    public void findPotentialMoves(ChessBoard board) {
        int x = getPos().getxPos();
        int y = getPos().getyPos();
        int[] dirs1 = new int[]{-2,-1,1,2};
        int[] dirs2 = new int[]{-1,2,-2,1};
        for(int i = 0; i < dirs1.length; i++) {
            if (board.checkPossibleMove(x + dirs1[i], y + dirs2[i], getColor())) {
                getPotentialMoves().add(new Position(x + dirs1[i], y + dirs2[i]));
            }
            if (board.checkPossibleMove(x+ dirs2[i], y+ dirs1[i], getColor())) {
                getPotentialMoves().add(new Position(x + dirs2[i], y + dirs1[i]));
            }
        }
    }
}
