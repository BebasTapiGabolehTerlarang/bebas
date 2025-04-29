package beverages;

public abstract class Beverage {
	private String type;
	
	public Beverage(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	public abstract String getDescription();
}
