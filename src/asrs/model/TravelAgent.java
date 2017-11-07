package asrs.model;

import java.util.Vector;

public class TravelAgent extends Company  {

	private Vector booking;
	
	public static final int BOOKING = 4;
	
	public TravelAgent(String id, String name, String loginName, String password, Vector booking) {
		super(id, name, loginName, password);
		this.booking = booking;
		// TODO Auto-generated constructor stub
	}

	public Vector toVector() {
		Vector v = super.toVector();
		v.add(booking);
		return v;
	}
}
