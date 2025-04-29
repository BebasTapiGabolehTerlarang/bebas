package utils;

import java.util.Scanner;

public class InputValidator {

	public static String getValidStringInput(Scanner scanner, String prompt, int minLength, int maxLength) {
		String input;
		do {
			System.out.println(prompt);
			input = scanner.nextLine().trim();
			if (input.length() >= minLength && input.length() <= maxLength) {
				return input;
			} else {
				System.out.println("Invalid input. Please enter a value between " + minLength + " and " + maxLength
						+ " characters.");
			}
		} while (true);
	}

	public static String getValidOptionInput(Scanner scanner, String prompt, String[] validOptions) {
		String input;
		do {
			System.out.println(prompt);
			input = scanner.nextLine().trim();
			boolean isValidOption = false;
			for (String option : validOptions) {
				if (option.equals(input)) {
					isValidOption = true;
					break;
				}
			}
			if (isValidOption) {
				return input;
			} else {
				System.out.println("Invalid input. Please enter one of:" + String.join("/", validOptions));
			}
		} while (true);
	}

	public static boolean getYesNoInput(Scanner scanner, String prompt) {
		String input;
		do {
			System.out.println(prompt);
			input = scanner.nextLine().trim();
			if (input.equals("Y")) {
				return true;
			} else if (input.equals("N")) {
				return false;
			} else {
				System.out.println("Invalid input. Please enter 'Y' or 'N'");
			}
		} while (true);
	}

	public static int getValidIntInput(Scanner scanner, String prompt, int min, int max) {
			int input;
			while(true) {
				System.out.println(prompt);
				if(scanner.hasNextInt()) {
					input = scanner.nextInt();
					scanner.nextLine();
					if(input >= min && input <= max) {
						return input;
					}
					else {
						System.out.println("Invalid input. Please enter a number between " + min + " and "	 + max+".");
					}
				}
				else {
					System.out.println("Wrong input. Input must be Int. With range of " + min + " up to " + max);
					scanner.nextLine();
				}
			}
		}
	
	public static double getValidDoubleInput(Scanner scanner, String prompt, double min, double max) {
		while(true) {
			System.out.println(prompt);
			if(scanner.hasNextDouble()) {
				double input = scanner.nextDouble();
				scanner.nextLine();
				if(input >= min && input <= max) {
					return input;
				}
				else {
					System.out.println("Invalid input. Please enter a number between " + min + " and "	 + max+".");
				}
			}
			else {
				System.out.println("Wrong input. Input must be Int. With range of " + min + " up to " + max);
				scanner.nextLine();
			}
		}
		
		
	}

}
