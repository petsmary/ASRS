package model;

import java.util.Vector;

public class Airline extends Company {

	private Vector airline;
	
	public static final int AIRLINE = 4;
	
	public Airline(String id, String name, String loginName, String password, Vector airline) {
		super(id, name, loginName, password);
		this.airline = airline;
		// TODO Auto-generated constructor stub
	}
	
	public Airline(Vector airline, Vector airplane) {
		super((String) airline.get(ID), (String) airline.get(NAME), (String) airline.get(LOGINNAME), (String) airline.get(PASSWORD));
		this.airline = airline;
	}

	public Vector toVector() {
		Vector v = super.toVector();
		v.add(airline);
		return v;
	}
}
