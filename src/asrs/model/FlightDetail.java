package asrs.model;

import java.util.Vector;

public class FlightDetail {

	private final String id;
	private final String classType;
	private final String price;
	private String seatLeft;
	private final String totalSeat;
	private final String flightId;

	public static final int ID = 0;
	public static final int CLASSTYPE = 1;
	public static final int PRICE = 2;
	public static final int SEATLEFT = 3;
	public static final int TOTALSEAT = 4;
	public static final int FLIGHTLID = 5;
	
	public void setSeatLeft(String seatLeft) {
		this.seatLeft = seatLeft;
	}

	public FlightDetail(String id, String classType, String price, String seatLeft, String totalSeat,
			String flightId) {
		super();
		this.id = id;
		this.classType = classType;
		this.price = price;
		this.seatLeft = seatLeft;
		this.totalSeat = totalSeat;
		this.flightId = flightId;
	}
	
	public Vector toVector() {
		Vector v = new Vector();
		v.add(id);
		v.add(classType);
		v.add(price);
		v.add(seatLeft);
		v.add(totalSeat);
		v.add(flightId);
		return v;
	}

	public String getId() {
		return id;
	}

	public String getClassType() {
		return classType;
	}

	public String getPrice() {
		return price;
	}

	public String getSeatLeft() {
		return seatLeft;
	}

	public String getTotalSeat() {
		return totalSeat;
	}

	public String getFlightId() {
		return flightId;
	}

}
