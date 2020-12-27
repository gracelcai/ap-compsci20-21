public class FoursScore extends Score{
	
	public FoursScore(String name) {
		super(name);
		this.setName(name);
	}

	public int calculateScore(YahtzeeDice yahtzeeDice) {
		return calculateNumScore(yahtzeeDice, 4);
	}
}
