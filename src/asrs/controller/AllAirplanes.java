package asrs.controller;

import java.util.Iterator;
import java.util.Vector;

import asrs.model.Airline;
import asrs.model.Airplane;

public class AllAirplanes {

	static private asrs.db.JDBCDatabaseTable theAirline;
	static private asrs.db.JDBCDatabaseTable theAirplane;

	static private AllAirplanes instance = new AllAirplanes("Airline", "Airplane");

	private AllAirplanes(String tableName1, String tableName2) {
		theAirline = new asrs.db.JDBCDatabaseTable(tableName1);
		theAirplane = new asrs.db.JDBCDatabaseTable(tableName2);
	}
	
	public final static AllAirplanes getInstance() {
		return instance;
	}

	public Vector getTable() {
		return theAirline.getTable();
	}

	public Iterator getIterator() {
		return getTable().iterator();
	}

	public Vector getAirline(Object key) {
		return theAirline.get(key);
	}

	public boolean putAirline(Vector keyAndData) {
		return theAirline.put(keyAndData);
	}

	public boolean removeAirline(Object key) {
		return theAirline.remove(key);
	}

	public boolean changeAirline(Vector keyAndData) {
		return theAirline.change(keyAndData);
	}
	
	public Vector getAirline(int columnIndex, Object key) {
		return theAirline.get(columnIndex, key);
	}
	
	public Vector getAirplane(Object key) {
		return theAirplane.get(key);
	}

	public boolean putAirplane(Vector keyAndData) {
		return theAirplane.put(keyAndData);
	}

	public boolean removeAirplane(Object key) {
		return theAirplane.remove(key);
	}

	public boolean changeAirplane(Vector keyAndData) {
		return theAirplane.change(keyAndData);
	}
	
	public Vector getAirplane(int columnIndex, Object key) {
		return theAirplane.get(columnIndex, key);
	}
	
	public Vector getCoulumnName() {
		return theAirplane.getColumnNames();
	}

	public Airline getAirlineDetails(String id) {
		Vector details = theAirplane.get(Airplane.AIRLINE, id);
		return new Airline(theAirline.get(id), details);
	}
}
