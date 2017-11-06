package model;

import java.util.Vector;

public class Ticket {

	private final String ticketId;
	private final String ticketNumber;
	private final String firstName;
	private final String lastName;
	private final String email;
	private final String phone;
	private final String flightDetail;
	private final String seatNumber;
	

	public static final int ID = 0;
	public static final int TICKETNUMBER = 1;
	public static final int FIRSTNAME = 2;
	public static final int LASTNAME = 3;
	public static final int EMAIL = 4;
	public static final int PHONE = 5;
	public static final int FLIGHTDETAIL = 6;
	public static final int SEATNUMBER = 7;
	
	public Ticket(String ticketId, String ticketNumber, String firstName, String lastName, String email, String phone,
			String flightDetail, String seatNumber) {
		super();
		this.ticketId = ticketId;
		this.ticketNumber = ticketNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.flightDetail = flightDetail;
		this.seatNumber = seatNumber;
	}
	
	public Ticket(Vector v) {
		super();
		this.ticketId = (String) v.get(ID);
		this.firstName =  (String) v.get(FIRSTNAME);
		this.lastName =  (String) v.get(LASTNAME);
		this.email =  (String) v.get(EMAIL);
		this.phone =  (String) v.get(PHONE);
		this.flightDetail =  (String) v.get(FLIGHTDETAIL);
		this.ticketNumber =  (String) v.get(TICKETNUMBER);
		this.seatNumber =  (String) v.get(SEATNUMBER);
	}

	public Vector toVector() {
		Vector v = new Vector();
		v.add(ticketId);
		v.add(ticketNumber);
		v.add(firstName);
		v.add(lastName);
		v.add(email);
		v.add(phone);
		v.add(flightDetail);
		v.add(seatNumber);
		return v;
	}

	public String getTicketId() {
		return ticketId;
	}


	public String getTicketNumber() {
		return ticketNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public String getFlightDetail() {
		return flightDetail;
	}

}
