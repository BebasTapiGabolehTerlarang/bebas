package beverage;

import java.util.List;

public class Coffe implements Beverage {

	private String name;
	private String size;
	private List<String> toppings;
	private double price;
	
	
	
	public Coffe(String name, String size, List<String> toppings, double price) {
		super();
		this.name = name;
		this.size = size;
		this.toppings = toppings;
		this.price = price;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getSize() {
		return size;
	}

	@Override
	public List<String> getToppings() {
		return toppings;
	}

	@Override
	public double getPrice() {
		return price;
	}

	@Override
	public String getType() {
		return "Smoothie";
	}

	@Override
	public void prepare() {
		System.out.println("Preparing a Coffe: " + 
	name+ "("+size+") with topping: "+ toppings);
	}

}
