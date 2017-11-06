package model;

public class BookingTicket {

	private final String id;
	private final String bookingNumber;
	private final String ticketNumber;
	private final String isTaken;

	public static final int ID = 0;
	public static final int TICKETNUMBER = 1;
	public static final int BOOKINGNUMBER = 2;
	public static final int ISTAKEN = 3;

	public BookingTicket(String id, String bookingNumber, String ticketNumber, String isTaken) {
		super();
		this.id = id;
		this.bookingNumber = bookingNumber;
		this.ticketNumber = ticketNumber;
		this.isTaken = isTaken;
	}

	public String getId() {
		return id;
	}

	public String getBookingNumber() {
		return bookingNumber;
	}

	public String getTicketNumber() {
		return ticketNumber;
	}

	public String getIsTaken() {
		return isTaken;
	}

}
