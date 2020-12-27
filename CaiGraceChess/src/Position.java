public class Position {
    //positions directly correspond to array 0-7
    public static char[] chars = {'A','B','C','D','E','F','G','H'};
    private int xPos;
    private int yPos;

    public Position(int x, int y) {
        xPos = x;
        yPos = y;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public String toString() {
        return chars[xPos] + String.valueOf(yPos+1);
    }
}
