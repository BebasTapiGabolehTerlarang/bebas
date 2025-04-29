package beverage;

import java.util.List;

public class BeverageFactory {
	
	public static Beverage createBeverage(String type, String name, String size, List<String> toppings,
			double price) {
		if("Smoothie".equals(type)) {
			return new Smoothie(name, size, toppings, price);
		}
		else if("Coffee".equals(type)) {
			return new Coffe(name, size, toppings, price);
		}
		else {
			System.out.println("Invalid beverage type");;
			return null;
		}
	}
}
