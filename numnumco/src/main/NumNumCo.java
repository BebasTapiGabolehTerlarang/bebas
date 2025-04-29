package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import beverage.Beverage;
import beverage.BeverageFactory;
import database.Database;
import order.Order;
import payment.PaymentProcessor;

public class NumNumCo {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Database database = Database.getInstance();

		while (true) {
			displayMenu();
			System.out.println("Choose an option: ");
			String option = scanner.nextLine();

			switch (option) {
			case "1":
				orderBeverage(scanner, database);
				break;
				
			case "2":
				viewOrders(database);
				break;
				
			case "3":
				System.out.println("GoodBye!");
				scanner.close();
				return;
			default:
				System.out.println("Invalid option. Please choose again.");
			}

		}

	}

	

	private static void displayMenu() {
		System.out.println("\nNumNum Co.");
		System.out.println("1. Order beverages");
		System.out.println("2. View existing beverage orders");
		System.out.println("3. Exit");
	}

	private static void orderBeverage(Scanner scanner, Database database) {
		System.out.println("\n--- Order Beverages ---");
		
		// Input dan Validasi beverage type
		String beverageType;
		while(true) {
			System.out.println("Enter beverage type[Smoothie/Coffee][Case Sensitive]: ");
			beverageType = scanner.nextLine();
			if("Smoothie".equals(beverageType) || "Coffee".equals(beverageType)) {
				break;
			}
			else {
				System.out.println("Invalid beverage type/typo. Plese enter 'Smoothie' or 'Coffee.");
			}
		}
		
		// Input dan Validasi beverage name
		String beverageName;
		while(true) {
			System.out.println("Enter beverage name[5-15 Characters]: ");
			beverageName = scanner.nextLine();
			if(beverageName.length() >=5 && beverageName.length() <= 15) {
				break;
			}
			else {
				System.out.println("Invalid beverage name length. Please enter a name between 5 and 15 characters");
			}
		}
		
		// Input dan Validasi beverage size
		String beverageSize;
		while(true) {
			System.out.println("Enter beverage size[Small/Medium/Large][Case Sensitive]: ");
			beverageSize = scanner.nextLine();
			if("Small".equals(beverageSize) || "Medium".equals(beverageSize) || "Large".equals(beverageSize)) {
				break;
			}
			else {
				System.out.println("Invalid beverage size. Please enter 'Small' or 'Medium' or 'Large'");
			}
			
		}
		
		// Input untuk toppings
		List<String> toppingsList = new ArrayList<String>();
		System.out.println("Do you want to add toppings? (Y/N): ");
		String hasToppingsInput = scanner.nextLine();
		if("Y".equals(hasToppingsInput)) {
			System.out.println("Enter up to 3 toppings (each 1-10 characters), enter 'done' when finished:");
			int toppingCount = 0;
			while(toppingCount < 3) {
				System.out.println("Topping " + (toppingCount + 1) + ": ");
				String topping = scanner.nextLine();
				if("done".equalsIgnoreCase(topping)){
					break;
				}
				if(topping.length() >= 1 && topping.length() <= 10) {
					toppingsList.add(topping);
					toppingCount++;
				}
				else {
					System.out.println("Invalid topping length. Please enter a topping between 1 and 10 characters.");
				}
			}
		}
		
		// input dan validasi price
		double beveragePrice;
		while(true) {
			System.out.println("Enter beverage price (10.00 - 50.0): ");
			if(scanner.hasNextDouble()) {
				beveragePrice = scanner.nextDouble();
				scanner.nextLine(); // consume '\n'
				if(beveragePrice > 10.0 && beveragePrice < 50.0) {
					break;
				}
				else {
					System.out.println("Invalid price range. Please enter a price between 10.0 and 50.0.");
				}
			}
			else {
				System.out.println("Invalid input. Please enter a valid number for price.");
				scanner.nextLine(); // consume invalid input
			}
		}
		
		// input dan validasi payment type
		String paymentType;
		while(true) {
			System.out.println("Enter payment type (Cash/Transfer/Crypto): ");
			paymentType = scanner.nextLine();
			if("Cash".equals(paymentType) || "Transfer".equals(paymentType) || "Crypto".equals(paymentType)) {
				break;
			}
			else {
				System.out.println("Invalid payment type. Please enter 'Cash', 'Transfer', or 'Crypto'.");
			}
		}
		
		// buat objek beverage menggunakan factory
		Beverage beverage = BeverageFactory.createBeverage(beverageType, beverageName, beverageSize, toppingsList, beveragePrice);
		
		if(beverage != null) {
			// hitung harga total berdasarkan tipe pembayaran paymentprocessor
			double totalPrice = PaymentProcessor.calculateTotalPrice(beveragePrice, paymentType);
			String paymentIdentifier = PaymentProcessor.generatePaymentIdentifier(paymentType);
			
			// buat objek order
			Order order = new Order(beverage, paymentType, totalPrice, paymentIdentifier);
			
			database.addOrder(order);
			
			beverage.prepare(); // proses pembuatan minuman
			System.out.println("Beverage ordered sucessfully!");
		}
	}
	
	private static void viewOrders(Database database) {
		System.out.println("\n--- View Beverage Orders ---");
		if(database.isEmpty()) {
			System.out.println("No beverage order yet.");
		}else {
			// Retrieve data into local variable
			List<Order> orders = database.getAllOrder();
			for(int i=0; i< orders.size(); i++) {
				System.out.println("\nOrder " + (i+1) + ":");
				System.out.println(orders.get(i));
				System.out.println("----------------------------");
			}
		}
	}

}
