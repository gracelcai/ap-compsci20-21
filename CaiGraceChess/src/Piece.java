import java.util.ArrayList;

public abstract class Piece {
    private String name;
    private Position pos;
    private boolean captured;



    private int color; //-1 is black, 1 is white
    private ArrayList<Position> potentialMoves;

    public boolean isCaptured() {
        return captured;
    }

    public void setCaptured(boolean captured) {
        this.captured = captured;
    }

    public Piece(String name, Position pos, int color) {
        this.name = name;
        this.pos = pos;
        this.color = color;
        captured = false;
        potentialMoves = new ArrayList<Position>();
    }

    public abstract void findPotentialMoves(ChessBoard board);

    public String printPotentialMoves(ChessBoard board) {
        findPotentialMoves(board);
        String returnString = "";
        for(int i = 0; i < potentialMoves.size(); i++) {
            returnString += (i + ":" + potentialMoves.get(i).toString() + " ");
        }
        if(potentialMoves.size() == 0) {
            returnString = "there are no possible moves for this piece";
        }
        return returnString;
    }
    public void checkDirection(ChessBoard board, int xDir, int yDir) {
        int x = getPos().getxPos() + xDir;
        int y = getPos().getyPos() + yDir;
        boolean canCapture = false;
        if(this instanceof King) {
            if(board.checkPossibleMove(x,y,getColor())) {
                getPotentialMoves().add(new Position(x, y));
            }
        } else {
            while(board.possibleSpace(x,y)) {
                if(canCapture || (!board.checkSpaceEmpty(x, y) && !board.checkSpaceEnemy(x, y, getColor()))) {
                    break;
                }
                if(!board.checkSpaceEmpty(x, y) && board.checkSpaceEnemy(x, y, getColor())) {
                    canCapture = true;
                }
                getPotentialMoves().add(new Position(x, y));
                x += xDir;
                y += yDir;
            }
        }

    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }
    public Position getPos() {
        return pos;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public ArrayList<Position> getPotentialMoves() {
        return potentialMoves;
    }

    public void setPotentialMoves(ArrayList<Position> potentialMoves) {
        this.potentialMoves = potentialMoves;
    }



}
