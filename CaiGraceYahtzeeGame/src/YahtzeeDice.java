public class YahtzeeDice {
    private Die[] yahtzeeDice = new Die[5];

    public YahtzeeDice() {
        for(int i = 0; i < yahtzeeDice.length; i++){
            yahtzeeDice[i] = new Die();
        }
    }

    public YahtzeeDice(int numSides) {
        for(int i = 0; i < yahtzeeDice.length; i++){
            yahtzeeDice[i] = new Die(numSides);
        }
    }

    public int roll() {
        int sum = 0;
        for(int i = 0; i < yahtzeeDice.length; i++){
            sum += yahtzeeDice[i].roll();
        }
        return sum;
    }

    public Die[] getDice() {
        return yahtzeeDice;
    }

    public int[] getDiceValues() {
        int[] diceValues = new int[yahtzeeDice.length];
        for(int i = 0; i < yahtzeeDice.length; i++){
            diceValues[i] = yahtzeeDice[i].getCurrentValue();
        }
        return diceValues;
    }

    public int getDiceSum() {
        int sum = 0;
        for(int i = 0; i < getDiceValues().length; i++) {
            sum += getDiceValues()[i];
        }
        return sum;
    }


    public String toString() {
        String yahtzee = "The " + 5 + " dice read: ";

        for(int i = 0; i < yahtzeeDice.length; i++) {
            if(i < yahtzeeDice.length - 1)
                yahtzee += yahtzeeDice[i].getCurrentValue() + ", ";
            else
                yahtzee += yahtzeeDice[i].getCurrentValue() + ".";
        }
        return yahtzee;
    }

    public void playRoll() {
        for(int i = 0; i < 5; i++) {
            System.out.print("Do you want to hold die #" + (i+1) + "? (y/n): ");
            String input = TextIO.getlnString();
            while(!(input.equals("y") || input.equals("n"))) {
                System.out.print("Please enter a valid option (y/n): ");
                input = TextIO.getlnString();
            }
            if(input.equals("n")) {
                yahtzeeDice[i].roll();
            }
           
        }

        
    }
}
