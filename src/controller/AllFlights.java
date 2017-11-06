package controller;

import java.util.Iterator;
import java.util.Vector;

import model.Flight;
import model.FlightDetail;

public class AllFlights {

	static private db.JDBCDatabaseTable theFlights;
	static private db.JDBCDatabaseTable theFlightsDetails;

	static private AllFlights instance = new AllFlights("Flight", "FlightDetail");

	private AllFlights(String tableName1, String tableName2) {
		theFlights = new db.JDBCDatabaseTable(tableName1);
		theFlightsDetails = new db.JDBCDatabaseTable(tableName2);
	}

	public final static AllFlights getInstance() {
		return instance;
	}
	
	public Vector getColumnName() {
		return theFlights.getColumnNames();
	}

	public Vector getTable() {
		return theFlights.getTable();
	}

	public Iterator getIterator() {
		return getTable().iterator();
	}

	public Vector getFlight(Object key) {
		return theFlights.get(key);
	}

	public boolean putFlight(Vector keyAndData) {
		return theFlights.put(keyAndData);
	}

	public boolean removeFlight(Object key) {
		return theFlights.remove(key);
	}

	public boolean changeFlight(Vector keyAndData) {
		return theFlights.change(keyAndData);
	}
	
	public Vector getFlightDetail(Object key) {
		return theFlightsDetails.get(key);
	}

	public boolean putFlightDetail(Vector keyAndData) {
		return theFlightsDetails.put(keyAndData);
	}

	public boolean removeFlightDetail(Object key) {
		return theFlightsDetails.remove(key);
	}

	public boolean changeFlightDetail(Vector keyAndData) {
		return theFlightsDetails.change(keyAndData);
	}
	
	public Vector getFlight(int columnIndex, Object key) {
		return theFlights.get(columnIndex, key);
	}
	
	public Vector getFlightDetail(int columnIndex, Object key) {
		return theFlightsDetails.get(columnIndex, key);
	}

	public Flight getFlightDetail(String id) {
		Vector details = theFlightsDetails.get(FlightDetail.FLIGHTLID, id);
		return new Flight(theFlights.get(id), details);
	}

}
