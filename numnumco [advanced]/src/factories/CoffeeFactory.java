package factories;

import beverages.Beverage;
import beverages.Coffee;

public class CoffeeFactory implements BeverageFactory{

	@Override
	public Beverage createBeverage() {
		return new Coffee();
	}

}
