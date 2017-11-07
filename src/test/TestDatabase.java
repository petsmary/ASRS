package test;

import javax.swing.JFrame;
import javax.swing.JPanel;

import asrs.db.JDBCDatabaseTable;
import asrs.gui.customer.booking.SeatPanel;

public class TestDatabase {

	public static void main(String[] args) {
		/*
		 * JDBCDatabaseTable db = new JDBCDatabaseTable("Airplane"); Vector v = (Vector)
		 * db.getTable().get(0); System.out.println(db.getTable().get(0));
		 * System.out.println(v.get(0)); Vector vector = new Vector(); vector.add("14");
		 * vector.add("Golden Queen"); vector.add("ABC Airline"); vector.add("18");
		 * vector.add("4"); vector.add("2"); vector.add("0"); vector.add("16");
		 * v.set(0,"0"); v.set(1,"Golden Queen"); v.set(6,"0"); v.set(7,"17");
		 * System.out.println(v); System.out.println(db.getColumnNames()); for(int i=0;
		 * i<db.getColumnTypes().size(); i++) {
		 * System.out.println(db.getColumnTypes().get(i)); }
		 * System.out.println(db.put(vector)); System.out.println(db.getTable());
		 */
		
		/*JFrame frame = new JFrame();
		JPanel panel = new AirplanePanel(frame, null);
		frame.add(panel);
		frame.setSize(1000, 800);
		frame.setVisible(true);*/
		
		System.out.println(new JDBCDatabaseTable("Airline").getColumnNames());
		System.out.println(new JDBCDatabaseTable("Airplane").getColumnNames());
		System.out.println(new JDBCDatabaseTable("Booking").getColumnNames());
		System.out.println(new JDBCDatabaseTable("BookingTicket").getColumnNames());
		System.out.println(new JDBCDatabaseTable("Flight").getColumnNames());
		System.out.println(new JDBCDatabaseTable("FlightDetail").getColumnNames());
		System.out.println(new JDBCDatabaseTable("Status").getColumnNames());
		System.out.println(new JDBCDatabaseTable("TABooking").getColumnNames());
		System.out.println(new JDBCDatabaseTable("Ticket").getColumnNames());		
		System.out.println(new JDBCDatabaseTable("TravelAgent").getColumnNames());

		
		System.out.println(asrs.controller.AllAirplanes.getInstance().getAirline(asrs.model.Airline.LOGINNAME, "abc")); 
		System.out.println(asrs.controller.AllFlights.getInstance().getFlightDetail("1").getFlightDetail());
		//System.out.println((Integer)"A");
		//JFrame frame = new JFrame();
		//frame.add(new SeatPanel(frame, new JPanel(), Integer.parseInt("18"), Integer.parseInt("6")));
		//frame.setVisible(true);
	}
}
