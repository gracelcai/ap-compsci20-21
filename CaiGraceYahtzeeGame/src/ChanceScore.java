public class ChanceScore extends Score{
	
	public ChanceScore(String name) {
		super(name);
		this.setName(name);
	}

	public int calculateScore(YahtzeeDice dice) {
		return dice.getDiceSum();
	}
}
