import java.util.Arrays;

public abstract class Score {

    private String name;
    private boolean used;
    private int value;

    public Score(String name) {
        this.name = name;
        value = 0;
        used = false;
    }

    public abstract int calculateScore(YahtzeeDice dice);

    public static int calculateNumScore(YahtzeeDice dice, int num) {
        int count = 0;
        int[] values = dice.getDiceValues();
        for(int i = 0; i < values.length; i++) {
            if(values[i] == num) { // replace dice[i] with whatever necessary
                count += num;
            }
        }
        return count;
    }
    public boolean getUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int[] sortValues(YahtzeeDice dice) {
        int[] values = dice.getDiceValues();
        Arrays.sort(values);
        for(int i = 0; i < values.length-1; i++) {
            if(values[i] == values[i+1]) {
                values[i] = 0;
            }
        }
        Arrays.sort(values);
        return values;
    }



}

