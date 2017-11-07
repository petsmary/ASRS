package model;

import java.util.Vector;

public class Flight {

	private final String flightId;
	private final String name;
	private final String source;
	private final String destination;
	private final String departure;
	private final String arrive;
	private final String airplane;
	private Vector flightDetail;

	public static final int ID = 0;
	public static final int NAME = 1;
	public static final int SOURCE = 2;
	public static final int DESTINATION = 3;
	public static final int DEPARTURE = 4;
	public static final int ARRIVE = 5;
	public static final int AIRPLANE = 6;
	public static final int FLIGHTDETAIL = 7;

	public void setFlightDetail(Vector flightDetail) {
		this.flightDetail = flightDetail;
	}

	public Flight(String flightId, String name, String source, String destination, String departure, String arrive,
			String airplane, Vector flightDetail) {
		super();
		this.flightId = flightId;
		this.name = name;
		this.source = source;
		this.destination = destination;
		this.departure = departure;
		this.arrive = arrive;
		this.airplane = airplane;
		this.flightDetail = flightDetail;
	}

	public Flight(Vector flight, Vector flightDetail) {
		super();
		this.flightId = (String) flight.get(ID);
		this.name = (String) flight.get(NAME);
		this.source = (String) flight.get(SOURCE);
		this.destination = (String) flight.get(DESTINATION);
		this.departure = (String) flight.get(DEPARTURE);
		this.arrive = (String) flight.get(ARRIVE);
		this.airplane = (String) flight.get(AIRPLANE);
		this.flightDetail = flightDetail;
	}

	public Vector toVector() {
		Vector v = new Vector();
		v.add(flightId);
		v.add(name);
		v.add(source);
		v.add(destination);
		v.add(departure);
		v.add(arrive);
		v.add(airplane);
		v.add(flightDetail);
		return v;
	}
	
	public String getAirplane() {
		return airplane;
	}

	public String getFlightId() {
		return flightId;
	}

	public String getName() {
		return name;
	}

	public String getSource() {
		return source;
	}

	public String getDestination() {
		return destination;
	}

	public String getDeparture() {
		return departure;
	}

	public String getArrive() {
		return arrive;
	}

	public Vector getFlightDetail() {
		return flightDetail;
	}

}
