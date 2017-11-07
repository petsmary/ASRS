package controller;

import java.util.Iterator;
import java.util.Vector;

public class GetBookingTicket {

	static private db.JDBCDatabaseTable thebookingTicket;

	static private GetBookingTicket instance = new GetBookingTicket("BookingTicket");

	private GetBookingTicket(String tableName) {
		thebookingTicket = new db.JDBCDatabaseTable(tableName);
	}
	
	public final static GetBookingTicket getInstance() {
		return instance;
	}

	public Vector getTable() {
		return thebookingTicket.getTable();
	}

	public Iterator getIterator() {
		return getTable().iterator();
	}

	public Vector get(Object key) {
		return thebookingTicket.get(key);
	}

	public boolean put(Vector keyAndData) {
		return thebookingTicket.put(keyAndData);
	}

	public boolean remove(Object key) {
		return thebookingTicket.remove(key);
	}

	public boolean change(Vector keyAndData) {
		return thebookingTicket.change(keyAndData);
	}
	
	public Vector get(int columnIndex, Object key) {
		return thebookingTicket.get(columnIndex, key);
	}

}
