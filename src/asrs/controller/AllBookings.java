package asrs.controller;

import java.util.Iterator;
import java.util.Vector;

public class AllBookings {

	static private asrs.db.JDBCDatabaseTable thebooking;

	static private AllBookings instance = new AllBookings("Booking");

	private AllBookings(String tableName) {
		thebooking = new asrs.db.JDBCDatabaseTable(tableName);
	}
	
	public final static AllBookings getInstance() {
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
