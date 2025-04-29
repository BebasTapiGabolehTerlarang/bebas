package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import adapter.PriceConverterAdapter;
import adapter.paymentAdapter;
import beverages.Beverage;
import factories.BeverageFactory;
import factories.CoffeeFactory;
import factories.SmoothieFactory;
import order.BeverageOrder;
import singleton.Database;
import utils.InputValidator;

public class Main {

	final Scanner scanner = new Scanner(System.in);
	Database database = Database.getInstance();
	final PriceConverterAdapter paymentAdapter = new PriceConverterAdapter();

	public Main() {

		while (true) {
			displayMenu();
			int choice = InputValidator.getValidIntInput(scanner, "Choose an option: ", 1, 3);
			switch (choice) {
			case 1:
				orderBeverage();
				break;
			case 2:
				viewOrders();
				break;
			case 3:
				System.out.println("Thank you for using this services..");
				System.exit(0);
			default:
				System.out.println("Invalid input. Please input again...");
				break;
			}
		}
	}

	private void viewOrders() {
		System.out.println("\n--- View Beverage Order ---");
		List<BeverageOrder> orderView = database.getAllOrders();
		if (orderView.isEmpty()) {
			System.out.println("No beverage orders available.");
		} else {
			for (BeverageOrder order : orderView) {
				System.out.println("\n--- Order details ---");
				System.out.println("Type: " + order.getBeverageType());
				System.out.println("Name: " + order.getBeverageName());
				System.out.println("Size: " + order.getBeverageSize());
				System.out.println("Toppings: ");
				if (order.getToppings().isEmpty()) {
					System.out.println("-");
				} else {
					System.out.println(String.join(",", order.getToppings()));
				}
				System.out.printf("Price: %.2f\n" , order.getPrice());
				System.out.println("Payment Type: " + order.getPaymentType());
				if (order.getPaymentType().equals("Transfer")) {
					System.out.println("Account number: " + order.getPaymentNumber());
				} else {
					System.out.println("Crypto number: " + order.getPaymentNumber());
				}
			}
		}

	}

	private void orderBeverage() {

		// 1. Choose Beverage Type
		BeverageFactory beverageFactory = getBeverageFactory();
		if (beverageFactory == null) {
			return;
		}

		Beverage beverage = beverageFactory.createBeverage();

		// 2. Input beverage Name
		String beverageName = InputValidator.getValidStringInput(scanner, "Enter beverage name: ", 5, 15);
		if (beverageName == null) {
			return;
		}

		// 3. Input beverage size
		String beverageSize = InputValidator.getValidOptionInput(scanner, "Enter beverage size (Small/Medium/Large): ",
				new String[] { "Small", "Medium", "Large" });

		// 4. Input toppings
		List<String> toppings = new ArrayList<String>();
		if (InputValidator.getYesNoInput(scanner, "Add Toppings? (Y/N): ")) {
			System.out.println("Enter up to 3 toppings (1-10 characters):");
			for (int i = 0; i < 3; i++) {
				if (!InputValidator.getYesNoInput(scanner, "Add topping " + (i + 1) + "? (Y/N, or N to finish): ")) {
					break;
				}
				String topping = InputValidator.getValidStringInput(scanner, "Topping " + (i + 1) + ": ", 1, 10);
				if (topping == null)
					continue;
				toppings.add(topping);
			}
		}

		// 5. Input harga
		double price = InputValidator.getValidDoubleInput(scanner, "Enter beverage price (10.0 - 50.0): ", 10.0, 50.0);
		if (price == -1)
			return;

		// 6. Input tipe pembayaran
		String paymentType = InputValidator.getValidOptionInput(scanner, "Enter payment type (Cash/Transfer/Crypto): ",
				new String[] { "Cash", "Transfer", "Crypto" });

		if (paymentType == null)
			return;
		double finalPrice = paymentAdapter.processPayment(paymentType, price);

		// 7. Buat orderan / buat beverage Order object
		BeverageOrder order = new BeverageOrder(paymentType, beverageName, beverageSize, toppings, finalPrice,
				paymentType);

		// 8. Save orderan ke database
		database.addOrder(order);
		System.out.println("\nBeverage Ordered sucessfully!");

	}

	private BeverageFactory getBeverageFactory() {
		String beverageType = InputValidator.getValidOptionInput(scanner, "Enter beverage type (Smoothie/Coffee): ",
				new String[] { "Smoothie", "Coffee" });

		if (beverageType.equals("Smoothie")) {
			return new SmoothieFactory();
		} else {
			return new CoffeeFactory();
		}

	}

	private void displayMenu() {
		System.out.println("NumNum Co.");
		System.out.println("1. Order Beverages");
		System.out.println("2. View existing beverage orders");
		System.out.println("3. Exit");
	}

	public static void main(String[] args) {

		new Main();

	}

}
