package adapter;

public class PriceConverterAdapter implements paymentAdapter {

	@Override
	public double processPayment(String paymentType, double price) {
		if(paymentType.equals("Cash")) {
			return price * 1.0;
		}
		else if(paymentType.equals("Transfer")) {
			return price * 1.1;
		}
		else if(paymentType.equals("Crypto")) {
			return price / 2;
		}
		return price;
	}

}
