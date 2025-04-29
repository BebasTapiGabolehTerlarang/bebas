package order;

import java.util.List;

import beverage.Beverage;

public class Order {
	private Beverage beverage;
	private String paymentType;
	private double totalPrice;
	private String paymentIdentifier;
	
	public Order(Beverage beverage, String paymentType, double totalPrice, String paymentIdentifier) {
		this.beverage = beverage;
		this.paymentType = paymentType;
		this.totalPrice = totalPrice;
		this.paymentIdentifier = paymentIdentifier;
	}

	public Beverage getBeverage() {
		return beverage;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public String getPaymentIdentifier() {
		return paymentIdentifier;
	}
	

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Beverage Type: ").append(beverage.getType()).append("\n");
		sb.append("Name: ").append(beverage.getName()).append("\n");
		sb.append("Size: ").append(beverage.getSize()).append("\n");
		List<String> toppings = beverage.getToppings();
		if(toppings != null && !toppings.isEmpty()) {
			sb.append("Toppings: ").append(String.join(",", toppings)).append("\n");
		}
		else {
			sb.append("Toppings: -\n");
		}
		sb.append("Price: ").append(String.format("%.2f", totalPrice)).append("\n");
		sb.append("Payment Type: ").append(paymentType).append("\n");
		if(paymentIdentifier != null) {
			sb.append("Payment identifier: ").append(paymentIdentifier).append("\n");
		}
		
		return sb.toString();
	}
	
	
}
