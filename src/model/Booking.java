package model;

import java.util.Vector;

public class Booking {

	private final String bookId;
	private final String firstName;
	private final String lastName;
	private final String email;
	private final String phone;
	private final String flightDetail;
	private String status;
	private final String bookingNumber;

	public static final int ID = 0;
	public static final int BOOKINGNUMBER = 1;
	public static final int FIRSTNAME = 2;
	public static final int LASTNAME = 3;
	public static final int EMAIL = 4;
	public static final int PHONE = 5;
	public static final int FLIGHTDETAIL = 6;
	public static final int STATUS = 7;

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Booking(String bookId, String firstName, String lastName, String email, String phone, String flightDetail,
			String status, String bookingNumber) {
		super();
		this.bookId = bookId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.flightDetail = flightDetail;
		this.status = status;
		this.bookingNumber = bookingNumber;
	}
	
	public Booking(Vector v) {
		super();
		this.bookId = (String) v.get(ID);
		this.firstName =  (String) v.get(FIRSTNAME);
		this.lastName =  (String) v.get(LASTNAME);
		this.email =  (String) v.get(EMAIL);
		this.phone =  (String) v.get(PHONE);
		this.flightDetail =  (String) v.get(FLIGHTDETAIL);
		this.status =  (String) v.get(STATUS);
		this.bookingNumber =  (String) v.get(BOOKINGNUMBER);
	}
	
	public Vector toVector() {
		Vector v = new Vector();
		v.add(bookId);
		v.add(bookingNumber);
		v.add(firstName);
		v.add(lastName);
		v.add(email);
		v.add(phone);
		v.add(flightDetail);
		v.add(status);		
		return v;
	}

	public String getBookId() {
		return bookId;
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

	public String getStatus() {
		return status;
	}

	public String getBookingNumber() {
		return bookingNumber;
	}

}
