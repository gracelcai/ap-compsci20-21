public class ScoreBoard{
    private final int NUM_SCORES = 13;
    public final static int ONES_INDEX = 0;
    public final static int TWOS_INDEX = 1;
    public final static int THREES_INDEX = 2;
    public final static int FOURS_INDEX = 3;
    public final static int FIVES_INDEX = 4;
    public final static int SIXES_INDEX = 5;
    public final static int CHANCE_INDEX = 6;
    public final static int FULLHOUSE_INDEX = 7;
    public final static int SMALLSTRAIGHT_INDEX = 8;
    public final static int LARGESTRAIGHT_INDEX = 9;
    public final static int THREEOFAKIND_INDEX = 10;
    public final static int FOUROFAKIND_INDEX = 11;
    public final static int YAHTZEE_INDEX = 12;

    public Score[] getScores() {
        return scores;
    }

    private Score[] scores = new Score[NUM_SCORES];
    private YahtzeeDice dice;
    private int totalScore;
    private boolean numSummed;
    public int getTotalScore() {
        return totalScore;
    }

    public ScoreBoard(YahtzeeDice yahtzeeDice) {
        dice = yahtzeeDice;
        totalScore = 0;
        numSummed = false;
        scores[ONES_INDEX] = new OnesScore("Ones");
        scores[TWOS_INDEX] = new TwosScore("Twos");
        scores[THREES_INDEX] = new ThreesScore("Threes");
        scores[FOURS_INDEX] = new FoursScore("Fours");
        scores[FIVES_INDEX] = new FivesScore("Fives");
        scores[SIXES_INDEX] = new SixesScore("Sixes");
        scores[CHANCE_INDEX] = new ChanceScore("Chance");
        scores[FULLHOUSE_INDEX] = new FullHouseScore("Full House");
        scores[SMALLSTRAIGHT_INDEX] = new SmallStraightScore("Small Straight");
        scores[LARGESTRAIGHT_INDEX] = new LargeStraightScore("Large Straight");
        scores[THREEOFAKIND_INDEX] = new ThreeOfAKindScore("Three of a kind");
        scores[FOUROFAKIND_INDEX] = new FourOfAKindScore("Four of a kind");
        scores[YAHTZEE_INDEX] = new YahtzeeScore("Yahtzee");
    }
    public void updateScore(int score) {
        scores[score - 1].setValue(scores[score - 1].calculateScore(dice));
        scores[score - 1].setUsed(true);
        totalScore += scores[score-1].getValue();
        int numSum = 0;
        for(int i = ONES_INDEX; i <= SIXES_INDEX; i++) {
            numSum+=scores[i].getValue();
        }
        if(numSum >= 63 && !numSummed) {
            totalScore += 35;
            numSummed = true;
        }
    }

    public String printPotentialScores() {
        String scoreString = "***Potential Scores***\n";
        for(int i = 0; i < NUM_SCORES; i++) {
            if(scores[i].getUsed()) {
                scoreString += String.format("%-30s", (i+1) + ": " + scores[i].getName() + ": used");
            } else {
                scoreString += String.format("%-30s", (i+1) + ": " + scores[i].getName() + ": " + scores[i].calculateScore(dice) + " ");
            }
            if(i > 0 && i % 3 == 0) {
                scoreString += "\n";
            }
        }
        return scoreString;
    }


    public String printActualScores() {
        String scoreString = "***Current Scorecard***\n";
        for(int i = 0; i < NUM_SCORES; i++) {
            scoreString += String.format("%-20s", scores[i].getName() + ": " + scores[i].getValue());
            if(i > 0 && i % 3 == 0) {
                scoreString += "\n";
            }
        }
        scoreString += "Total Score: " + totalScore;
        return scoreString;
    }


}
