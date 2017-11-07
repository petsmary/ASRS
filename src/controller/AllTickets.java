package controller;

import java.util.Iterator;
import java.util.Vector;

public class AllTickets {

	static private db.JDBCDatabaseTable thebooking;

	static private AllTickets instance = new AllTickets("Ticket");

	private AllTickets(String tableName) {
		thebooking = new db.JDBCDatabaseTable(tableName);
	}
	
	public final static AllTickets getInstance() {
		return instance;
	}

	public Vector getTable() {
		return thebooking.getTable();
	}

	public Iterator getIterator() {
		return getTable().iterator();
	}

	public Vector get(Object key) {
		return thebooking.get(key);
	}

	public boolean put(Vector keyAndData) {
		return thebooking.put(keyAndData);
	}

	public boolean remove(Object key) {
		return thebooking.remove(key);
	}

	public boolean change(Vector keyAndData) {
		return thebooking.change(keyAndData);
	}
	
	public Vector get(int columnIndex, Object key) {
		return thebooking.get(columnIndex, key);
	}
	
	
	public Vector getCoulumnName() {
		return thebooking.getColumnNames();
	}
}
