package utils;

import java.util.Random;

public class RandomGenerator {
	
	private static final String ACCOUNT_CHARS = "0123456789";
	private static final String CRYPTO_CHARS = "abcdefghijklmnopqrstuvwxyz0123456789";
	private static final Random random = new Random();
	
	public String generateAccountNumber() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i< 10; i++) {
			sb.append(ACCOUNT_CHARS.charAt(random.nextInt(ACCOUNT_CHARS.length())));
		}
		return sb.toString();
	}
	
	public String generateCryptoNumber() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i< 12; i++) {
			sb.append(CRYPTO_CHARS.charAt(random.nextInt(CRYPTO_CHARS.length())));
		}
		return sb.toString();
	}

}
