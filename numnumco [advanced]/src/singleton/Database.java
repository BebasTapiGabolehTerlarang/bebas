package singleton;

import java.util.ArrayList;
import java.util.List;

import javax.xml.crypto.Data;

import order.BeverageOrder;

public class Database {
	private static volatile Database instance;
	
	private List<BeverageOrder> orders;
	
	public Database() {
		orders = new ArrayList<BeverageOrder>();
	}
	
	public static Database getInstance() {
		Database result = instance;
		if(result == null) {
			synchronized (Database.class) {
				result = instance;
				if(result == null) {
					instance = result = new Database();
				}
			}
		}
		return instance;
	}
	
	public void addOrder(BeverageOrder order) {
		this.orders.add(order);
	}
	
	public List<BeverageOrder> getAllOrders(){
		return orders;
	}
	
	public boolean isEmpty() {
		return this.orders.isEmpty();
	}

}
