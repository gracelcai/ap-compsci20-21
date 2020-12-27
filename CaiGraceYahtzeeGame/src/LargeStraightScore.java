import java.util.Arrays;

public class LargeStraightScore extends Score {
	
	public LargeStraightScore(String name) {
		super(name);
		this.setName(name);
	}

	public int calculateScore(YahtzeeDice yahtzeeDice) {
		int[] values = sortValues(yahtzeeDice);
		boolean largeStraight = true;
		for(int i = 0; i < values.length - 1; i++) {
			if(values[i] + 1 == values[i+1]){
				largeStraight = true;
			} else {
				largeStraight = false;
				break;
			}
		}
		if(largeStraight) {
			return 40;
		}
		return 0;
	}
}
