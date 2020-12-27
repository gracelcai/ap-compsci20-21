public class SixesScore extends Score {
	
	public SixesScore(String name){
		super(name);
		this.setName(name);
	}

	public int calculateScore(YahtzeeDice yahtzeeDice) {
		return calculateNumScore(yahtzeeDice, 6);
	}
}
