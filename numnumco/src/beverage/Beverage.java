package beverage;

import java.util.List;

public interface Beverage {
	String getName();
	String getSize();
	List<String> getToppings();
	double getPrice();
	String getType();
	void prepare();
}
