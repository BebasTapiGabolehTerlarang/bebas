package factories;

import beverages.Beverage;
import beverages.Smoothie;

public class SmoothieFactory implements BeverageFactory{

	@Override
	public Beverage createBeverage() {
		return new Smoothie();
	}

}
