
public class YahtzeeScore extends Score {
    public YahtzeeScore(String name) {

        super(name);
        this.setName(name);
    }
    public int calculateScore(YahtzeeDice yahtzeeDice) {
        boolean isThisRoundYahtzee = true; // assume this is a true yahtzee
        int[] values = yahtzeeDice.getDiceValues();
        int yahtzeeDiceSide = values[0];
        // confirm all dices are same side
        for (int i = 1; i < values.length; i++) {
            if (yahtzeeDiceSide != values[i]) {
                // at least one side is different, it is a not an yahtzee
                isThisRoundYahtzee = false;
                break;
            }
        }
        if (isThisRoundYahtzee) {
            return 50;
        }
        return 0;
    }

}