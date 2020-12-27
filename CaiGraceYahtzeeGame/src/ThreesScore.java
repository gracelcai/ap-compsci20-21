public class ThreesScore extends Score{
	
	public ThreesScore(String name) {
		super(name);
		this.setName(name);
	}

	public int calculateScore(YahtzeeDice yahtzeeDice) {
		return calculateNumScore(yahtzeeDice, 3);
	}
}