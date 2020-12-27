import java.util.Arrays;

public class SmallStraightScore extends Score{

	public SmallStraightScore(String name) {
		super(name);
		this.setName(name);
	}

	public int calculateScore(YahtzeeDice yahtzeeDice) {
		int[] values = sortValues(yahtzeeDice);
		for(int i = 0; i < values.length - 3; i++) {
			if((values[i]+1 == values[i+1]) && (values[i+1]+1 == values[i+2]) && (values[i+2]+1 == values[i+3])) {
				return 30;
			}
		}
		return 0;
	}
}
