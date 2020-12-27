public class TwosScore extends Score{
	
	public TwosScore(String name) {
		super(name);
		this.setName(name);

	}
	

	public int calculateScore(YahtzeeDice yahtzeeDice) {
		return calculateNumScore(yahtzeeDice, 2);
	} 
}