public class YahtzeeGame {
    public static void main(String[] args) {
        System.out.println("Welcome to Yahtzee!");
        // print out rules and stuff
        YahtzeeDice dice = new YahtzeeDice();
        ScoreBoard scoreBoard = new ScoreBoard(dice);
        boolean gameOver = false;
        while(!gameOver) {
            playRound(dice);
            chooseScore(scoreBoard);
            gameOver = checkGameOver(scoreBoard.getScores());
        }
        System.out.println("GAME OVER");
        System.out.println("Your final score is: " + scoreBoard.getTotalScore());
    }

    public static void playRound(YahtzeeDice dice) {
        System.out.println("New Round!! You have max 3 rolls per round");
        String choice = "";
        System.out.println("Rolling dice...");
        dice.roll();
        System.out.println(dice.toString());
        System.out.println("2 rolls left");
        for(int i = 0; i < 2; i++) {
            choice = "";
            while(!(choice.equals("y") || choice.equals("n"))) {
                System.out.print("Would you like to roll again? (y/n): ");
                choice = TextIO.getlnString();
            }

            if(choice.equals("y")) {
                dice.playRoll();
            } else {
                break;
            }
            System.out.println("Rolling dice...");
            System.out.println(dice.toString());
            System.out.println((1-i) + " rolls left this round");
        }
        System.out.println(dice.toString());
    }

    public static void chooseScore(ScoreBoard scores) {
        System.out.println(scores.printActualScores());
        System.out.println(scores.printPotentialScores());
        int choice = 0;
        boolean valid = false;
        while(!valid) {
            System.out.print("Choose which score you would like to take (number 1-13): ");
            choice = TextIO.getlnInt();
            if (choice < 1 || choice > 13) {
                valid = false;
            } else {
                valid = !scores.getScores()[choice-1].getUsed();
            }
        }
        scores.updateScore(choice);

    }

    public static boolean checkGameOver(Score[] scores) {
        for(int i = 0; i < scores.length; i++) {
            if(!scores[i].getUsed()) {
                return false;
            }
        }
        return true;
    }
}
