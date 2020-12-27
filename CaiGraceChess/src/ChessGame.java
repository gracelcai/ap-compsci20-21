public class ChessGame {
    public static void main(String[] args) {
        ChessBoard board = new ChessBoard();
        Piece whiteKing = board.getBoard()[4][0];
        Piece blackKing = board.getBoard()[4][7];
        boolean whiteTurn = true;
        System.out.println("welcome to chess! \nrules: \n1. all basic moves in regular chess \n2. pawn promotion: when pawn reaches other end of board, you can promote it to a piece of your choice (other than king)\n3. en passant: immediately after a pawn move 2 spaces on its first move, an opposing pawn in the same row can capture it and move to the space behind it\n4. no castling" +
                "\nsetup: \n1. pieces are represented by letters: King(k/K), Queen(q/Q), Bishop(b/B), Knight(n/N), Rook(r/R), and Pawn(p/P)\n2. two player game: uppercase letters are black, lowercase letters are white " +
                "\nhow to play: \n1. move by typing in position with desired piece to move and select one of given possible moves \n2. game ends when a king is captured (check will be announced when king can be captured in next move)");
        System.out.println(board.toString());
        while(!whiteKing.isCaptured() && !blackKing.isCaptured()) {
            if(whiteTurn) {
                playTurn(1, board);
            } else {
                playTurn(-1, board);
            }
            whiteTurn = !whiteTurn;
        }
        if(whiteKing.isCaptured()) {
            System.out.println("black wins!");
        } else {
            System.out.println("white wins!");

        }

    }
    public static void playTurn(int color, ChessBoard board) {
        String start;
        int x = 0,y = 0;
        boolean pieceValid = false;
        boolean inputValid;
        if(color == 1) {
            System.out.println("white's turn!");
        } else {
            System.out.println("black's turn!");
        }
        while(!pieceValid) {
            do{
                System.out.println("give position of piece to move A-H,1-8(ex. A1): ");
                start = TextIO.getlnString();
                if(start.length() != 2){
                    inputValid = false;
                    System.out.println("input must be 2 characters long");
                    continue;
                }
                x = toInt(start.charAt(0));
                y = start.charAt(1) - '1';
                if(!board.possibleSpace(x,y)) {
                    inputValid = false;
                    System.out.println("this is not a valid space on the board");
                    continue;
                }
                if(board.checkSpaceEmpty(x,y)) {
                    inputValid = false;
                    System.out.println("this space is empty");
                    continue;
                }
                if(board.getBoard()[x][y].getColor() != color) {
                    inputValid = false;
                    System.out.println("the piece in this space is not yours");
                    continue;
                }
                inputValid = true;

            } while(!inputValid);
            board.getBoard()[x][y].getPotentialMoves().clear();
            board.getBoard()[x][y].findPotentialMoves(board);
            if(board.getBoard()[x][y].getPotentialMoves().size()==0) {
                System.out.println("there are no possible moves for this piece");
            } else {
                pieceValid = true;
            }
        }
        board.getBoard()[x][y].getPotentialMoves().clear();
        System.out.println("here are your possible moves, enter a number to select move: ");
        System.out.println(board.getBoard()[x][y].printPotentialMoves(board));
        int move = TextIO.getlnInt();
        while(!(move < board.getBoard()[x][y].getPotentialMoves().size() && move >= 0)) {
            System.out.println("enter a valid move 0-" + (board.getBoard()[x][y].getPotentialMoves().size()-1));
            move = TextIO.getlnInt();
        }
        board.movePiece(new Position(x,y), board.getBoard()[x][y].getPotentialMoves().get(move));
        if(checkCheck(color,board)) {
            System.out.println("check!");
        }
        resetPawns(-color, board);
        System.out.println(board.toString());
    }
    public static boolean checkCheck(int color, ChessBoard board) {
        for(int x =0; x < board.getBoard().length; x++) {
            for(int y =0; y < board.getBoard()[0].length; y++) {
                if(board.getBoard()[x][y] != null && !(board.getBoard()[x][y] instanceof King)){
                    board.getBoard()[x][y].findPotentialMoves(board);
                    for(int i =0; i < board.getBoard()[x][y].getPotentialMoves().size(); i++) {
                        if(board.getBoard()[board.getBoard()[x][y].getPotentialMoves().get(i).getxPos()][board.getBoard()[x][y].getPotentialMoves().get(i).getyPos()] instanceof King && board.getBoard()[board.getBoard()[x][y].getPotentialMoves().get(i).getxPos()][board.getBoard()[x][y].getPotentialMoves().get(i).getyPos()].getColor() != color) {
                            return true;
                        }
                    }
                }

            }
        }
        return false;
    }
    public static int toInt(char pos) {
        pos = Character.toUpperCase(pos);
        switch(pos) {
            case 'A':
                return 0;
            case 'B':
                return 1;
            case 'C':
                return 2;
            case 'D':
                return 3;
            case 'E':
                return 4;
            case 'F':
                return 5;
            case 'G':
                return 6;
            case 'H':
                return 7;
            default:
                return -1;
        }
    }

    public static void resetPawns(int color, ChessBoard board){
        for(int x =0; x < board.getBoard().length; x++) {
            for(int y =0; y < board.getBoard()[0].length; y++) {
                if(board.getBoard()[x][y] instanceof Pawn && board.getBoard()[x][y].getColor()==color){
                    ((Pawn) board.getBoard()[x][y]).setEnPass(false);
                }

            }
        }
    }
}
