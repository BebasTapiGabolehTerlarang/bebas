package beverages;

public class Coffee extends Beverage {

	public Coffee() {
		super("Coffee");
	}

	@Override
	public String getDescription() {
		return "A hot cup of Coffee";
	}

}
