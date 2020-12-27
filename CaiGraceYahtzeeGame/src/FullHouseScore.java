import java.util.Arrays;

public class FullHouseScore extends Score{

	public FullHouseScore(String name) {
		super(name);
		this.setName(name);
	}

	public int calculateScore(YahtzeeDice yahtzeeDice) {
		int[] values = yahtzeeDice.getDiceValues();
		Arrays.sort(values);
		if((values[0] == values[1] && values[2] == values[1] && values[3] == values[4]) || (values[0] == values[1] && values[2] == values[3] && values[3] == values[4])) {
			return 25;
		}
		return 0;
	}
}
