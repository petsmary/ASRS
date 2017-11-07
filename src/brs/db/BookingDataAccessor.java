package brs.db;

import java.util.ArrayList;

import brs.model.Booking;

public abstract class BookingDataAccessor {

	/**
	 *  The name of the ini file
	 */
	private final String INI_FILENAME = "rainforest.ini";

	public abstract ArrayList getBookingsList();

	public abstract ArrayList getBookingsOfbook(int book);
	
	public abstract ArrayList getBookingsOfCustomer(int customer);

	public abstract Booking getBooking(int id);
	
	public abstract boolean put(Booking booking);
	
	public abstract boolean change(Booking booking);
	
	//public abstract boolean remove(Booking booking);

	protected void log(Object msg) {
		System.out.println("BookingDataAccessor: " + msg);
	}
}
