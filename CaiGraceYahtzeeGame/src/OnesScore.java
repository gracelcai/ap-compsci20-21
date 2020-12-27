public class OnesScore extends Score{
	
	public OnesScore(String name) {
		super(name);
		this.setName(name);
	}

	public int calculateScore(YahtzeeDice yahtzeeDice) {
		return calculateNumScore(yahtzeeDice, 1);
	} 
}
