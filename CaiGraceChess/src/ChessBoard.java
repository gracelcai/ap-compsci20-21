public class ChessBoard {

    private Piece[][] board;
    public Piece[][] getBoard() {
        return board;
    }
    public ChessBoard() {
        board = new Piece[8][8];
        // black is upper -1, white is lower 1
        //pawns
        for(int i = 0; i < 8; i++) {
            board[i][1] = new Pawn("p", new Position(i,1), 1);
        }

        for(int i = 0; i < 8; i++) {
            board[i][6] = new Pawn("P", new Position(i,6), -1);
        }
        //rooks
        board[0][0] = new Rook("r", new Position(0,0), 1);
        board[7][0] = new Rook("r", new Position(7,0), 1);
        board[0][7] = new Rook("R", new Position(0,7), -1);
        board[7][7] = new Rook("R", new Position(7,7), -1);
        //bishops
        board[2][0] = new Bishop("b",new Position(2,0), 1);
        board[5][0] = new Bishop("b",new Position(5,0), 1);
        board[2][7] = new Bishop("B",new Position(2,7), -1);
        board[5][7] = new Bishop("B",new Position(5,7), -1);
        //knights
        board[1][0] = new Knight("n",new Position(1,0), 1);
        board[6][0] = new Knight("n",new Position(6,0), 1);
        board[1][7] = new Knight("N",new Position(1,7), -1);
        board[6][7] = new Knight("N",new Position(6,7), -1);
        //queens
        board[3][0] = new Queen("q", new Position(3,0), 1);
        board[3][7] = new Queen("Q", new Position(3,7), -1);
        //kings
        board[4][0] = new King("k", new Position(4,0), 1);
        board[4][7] = new King("K", new Position(4,7), -1);

    }

    public boolean checkSpaceEmpty(int x, int y) {
        return board[x][y] == null;
    }

    public boolean checkSpaceEnemy(int x, int y, int color) {
        return board[x][y] != null && board[x][y].getColor() != color;
    }

    public boolean possibleSpace(int x, int y) {
        return x < 8 && x >= 0 && y < 8 && y >= 0;
    }

    public boolean checkPossibleMove(int x, int y, int color) {
        return possibleSpace(x, y) && (checkSpaceEnemy(x, y, color) || checkSpaceEmpty(x, y));
    }

    public void movePiece(Position pos1, Position pos2) {
        int x1 = pos1.getxPos();
        int y1 = pos1.getyPos();
        int x2 = pos2.getxPos();
        int y2 = pos2.getyPos();
        if(getBoard()[x2][y2] != null) {
            if(getBoard()[x2][y2].getColor() == 1) {
                System.out.println("white's "+ getBoard()[x2][y2].getClass().getSimpleName().toLowerCase()+  " was captured by black's "+getBoard()[x1][y1].getClass().getSimpleName().toLowerCase());
            } else {
                System.out.println("black's "+ getBoard()[x2][y2].getClass().getSimpleName().toLowerCase()+  " was captured by white's "+getBoard()[x1][y1].getClass().getSimpleName().toLowerCase());
            }
            getBoard()[x2][y2].setCaptured(true);
            getBoard()[x2][y2] = null;
        }
        getBoard()[x2][y2] = getBoard()[x1][y1];
        getBoard()[x1][y1].setPos(pos2);
        getBoard()[x1][y1] = null;
        if(getBoard()[x2][y2] instanceof Pawn) {
            ((Pawn) getBoard()[x2][y2]).setMovedOnce(true);
            if(Math.abs(y2-y1)==2) {
                ((Pawn) getBoard()[x2][y2]).setEnPass(true);
            }
            // capture using en passant

            if (getBoard()[x2][y2 - getBoard()[x2][y2].getColor()] instanceof Pawn && ((Pawn)getBoard()[x2][y2 - getBoard()[x2][y2].getColor()]).isEnPass()) {
                getBoard()[x2][y2 - getBoard()[x2][y2].getColor()].setCaptured(true);
                getBoard()[x2][y2 - getBoard()[x2][y2].getColor()] = null;
                if (getBoard()[x2][y2].getColor() == 1) {
                    System.out.println("black's pawn was captured by white's pawn with an en passant");
                } else {
                    System.out.println("white's pawn was captured by black's pawn with an en passant");
                }
            }

            //pawn promotion
            if((getBoard()[x2][y2].getColor() == 1 && y2 == 7) || (getBoard()[x2][y2].getColor()==-1 && y2 == 0)) {
                String type;
                do{
                    System.out.println("enter the letter of the type of piece you want to promote your pawn to (ex. n for knight): ");
                    type = TextIO.getlnString().toLowerCase();
                } while(!(type.equals("q") || type.equals("r")|| type.equals("b")|| type.equals("n")));
                if(getBoard()[x2][y2].getColor() == 1){
                    switch (type) {
                        case "q":
                            getBoard()[x2][y2] = new Queen("q",new Position(x2,y2),getBoard()[x2][y2].getColor());
                            break;
                        case "r":
                            getBoard()[x2][y2] = new Rook("r",new Position(x2,y2),getBoard()[x2][y2].getColor());
                            break;
                        case "b":
                            getBoard()[x2][y2] = new Bishop("b",new Position(x2,y2),getBoard()[x2][y2].getColor());
                            break;
                        default:
                            getBoard()[x2][y2] = new Knight("n",new Position(x2,y2),getBoard()[x2][y2].getColor());
                    }
                }
                if(getBoard()[x2][y2].getColor() == -1){
                    switch (type) {
                        case "q":
                            getBoard()[x2][y2] = new Queen("Q",new Position(x2,y2),getBoard()[x2][y2].getColor());
                            break;
                        case "r":
                            getBoard()[x2][y2] = new Rook("R",new Position(x2,y2),getBoard()[x2][y2].getColor());
                            break;
                        case "b":
                            getBoard()[x2][y2] = new Bishop("B",new Position(x2,y2),getBoard()[x2][y2].getColor());
                            break;
                        default:
                            getBoard()[x2][y2] = new Knight("N",new Position(x2,y2),getBoard()[x2][y2].getColor());
                    }
                }
            }
        }
    }

    public String toString() {
        StringBuilder returnString = new StringBuilder(String.format("%36s", "| A | B | C | D | E | F | G | H |\n"));
        for(int y = board.length - 1; y >= 0; y--) {
            returnString.append(String.format("%38s", "--------------------------------------\n"));
            returnString.append(String.format("%1d%3s", y + 1, " | "));
            for(int x = 0; x < board[0].length; x++) {
                if(board[x][y] != null) {
                    returnString.append(String.format("%2s", board[x][y].getName() + " | "));
                } else {
                    returnString.append("  | ");
                }
            }
            returnString.append(String.format("%1d%1s", y + 1, "\n"));

        }
        returnString.append(String.format("%38s", "--------------------------------------\n"));
        returnString.append(String.format("%36s", "| A | B | C | D | E | F | G | H |\n"));
        return returnString.toString();
    }
}
