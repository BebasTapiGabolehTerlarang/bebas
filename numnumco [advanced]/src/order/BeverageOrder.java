package order;

import java.util.List;

import utils.RandomGenerator;

public class BeverageOrder {
	private String beverageType;
	private String beverageName;
	private String beverageSize;
	private List<String> toppings;
	private double price;
	private String paymentType;
	private String paymentNumber;
	RandomGenerator random = new RandomGenerator();
	
	public BeverageOrder(String beverageType, String beverageName, String beverageSize, List<String> toppings, double price,
			String paymentType) {
		this.beverageType = beverageType;
		this.beverageName = beverageName;
		this.beverageSize = beverageSize;
		this.toppings = toppings;
		this.price = price;
		this.paymentType = paymentType;
		
		
		
		if(this.paymentType.equals("Transfer")) {
			this.paymentNumber = random.generateAccountNumber();
		}
		else if(this.paymentType.equals("Crypto")) {
			this.paymentNumber = random.generateCryptoNumber();
		}
		else {
			this.paymentNumber = null;
		}
	}

	public String getBeverageType() {
		return beverageType;
	}

	public String getBeverageName() {
		return beverageName;
	}

	public String getBeverageSize() {
		return beverageSize;
	}

	public List<String> getToppings() {
		return toppings;
	}

	public double getPrice() {
		return price;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public String getPaymentNumber() {
		return paymentNumber;
	}
	
	

	

}
