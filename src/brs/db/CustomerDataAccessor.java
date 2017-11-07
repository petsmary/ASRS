package brs.db;

import java.util.ArrayList;

import brs.model.Customer;

public abstract class CustomerDataAccessor {

	private final String INI_FILENAME = "rainforest.ini";

	public abstract ArrayList getCustomers();

	public abstract Customer getCustomer(int id);
	
	public abstract Customer getCustomer(String email, String password);

	protected void log(Object msg) {
		System.out.println("CustomerDataAccessor: " + msg);
	}
}
