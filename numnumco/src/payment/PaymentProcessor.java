package payment;

import java.util.Random;

public class PaymentProcessor {
	public static double calculateTotalPrice(double price, String paymentType) {
		if("Cash".equals(paymentType)) {
			return price * 0.1;
		}
		else if("Transfer".equals(paymentType)) {
			return price * 1.1;
		}
		else if("Crypto".equals(paymentType)) {
			return price / 2.0;
		}
		else {
			return price; // default jika pembayaran tidak valid
		}
	}
	
	public static String generatePaymentIdentifier(String paymentType) {
		Random random = new Random();
		if("Transfer".equals(paymentType)) {
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<10; i++) {
				sb.append(random.nextInt(10)); // 0-9
			}
			return sb.toString();
		}
		else if("Crypto".equals(paymentType)) {
			StringBuilder sb = new StringBuilder();
			String characters = "abcdefghijklmnopqrstuvwxyz0123456789";
			for(int i=0; i<10; i++) {
				sb.append(characters.charAt(random.nextInt(characters.length())));
			}
			return sb.toString();
		}
		return null; // tidak ada identifier untuk cash atau tipe lain
	}
}
