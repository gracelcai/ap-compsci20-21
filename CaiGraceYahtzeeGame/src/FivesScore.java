public class FivesScore extends Score {
	
	public FivesScore(String name) {
		super(name);
		this.setName(name);
	}

	public int calculateScore(YahtzeeDice yahtzeeDice) {
		return calculateNumScore(yahtzeeDice, 5);
	}
}

