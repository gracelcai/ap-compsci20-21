public class FourOfAKindScore extends Score{

	public FourOfAKindScore(String name) {
		super(name);
		this.setName(name);
	}

	public int calculateScore(YahtzeeDice yahtzeeDice) {
		int[] values = yahtzeeDice.getDiceValues();
		int[] valueCount = new int[]{0,0,0,0,0,0};
		for (int i = 0; i < values.length; i++) {
			valueCount[values[i]-1]++;
		}
		for (int i = 0; i < valueCount.length; i++) {
			if (valueCount[i] >= 4) {
				return yahtzeeDice.getDiceSum();
			}
		}
		return 0;
	}
}



