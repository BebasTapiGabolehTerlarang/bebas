package database;

import java.util.ArrayList;
import java.util.List;


import order.Order;

public class Database {
	private static volatile Database instance;
	private List<Order> orders;
	
	private Database() {
		this.orders = new ArrayList<Order>();
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
		return result;
	}
	
	public void addOrder(Order order) {
		this.orders.add(order);
	}
	
	public List<Order> getAllOrder(){
		return new ArrayList<Order>(this.orders);
	}
	
	public boolean isEmpty() {
		return this.orders.isEmpty();
	}
	
}
