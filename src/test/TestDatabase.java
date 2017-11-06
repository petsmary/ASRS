package test;

import javax.swing.JFrame;
import javax.swing.JPanel;

import db.JDBCDatabaseTable;
import gui.customer.booking.AirplanePanel;

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

		
		System.out.println(controller.AllAirplanes.getInstance().getAirline(model.Airline.LOGINNAME, "abc")); 
		System.out.println(controller.AllFlights.getInstance().getFlightDetail("1").getFlightDetail());

		//JFrame frame = new JFrame();
		//frame.add(new ViewAirplanePanel(frame, "Airline"));
		//frame.setVisible(true);
	}
}
