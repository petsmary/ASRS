package brs.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import brs.model.Booking;

public class JDBCBookingAccessor extends BookingDataAccessor {

	private final String PROPS_FILENAME = "database.properties";
	/**
	 * The database connection. This is declared as a member variable so it can be
	 * used throughout the various methods.
	 */
	private Connection connection;

	/**
	 * Constructs the data accessor
	 */
	public JDBCBookingAccessor() {

		/*
		 * Info
		 *****************************************
		 * Note that the code below is calling a helper routine that will load the
		 * details from the rain.props file for you. If the file is not found then
		 * default settings are used. You can just use the variables, e.g. 'driverName'
		 * in your code
		 *****************************************
		 */

		// read information from properties file
		// helper routine already provided for you
		Properties props = loadProps();

		String driverName = props.getProperty("driverName");
		String dbUrl = props.getProperty("dbUrl");
		String userId = props.getProperty("userId");
		String passWord = props.getProperty("passWord");

		/*
		 * To Do: #1.
		 *****************************************************
		 * Get a Connection to the required DataBase
		 *****************************************************
		 * You will need to: - Load the data base driver - Get a connection to the
		 * database using the url, userid and password retrieved from the properties in
		 * the code directly above.
		 *
		 * Note: The connection variable has already been declared for you.
		 *****************************************************
		 */

		try {

			// load the driver
			// ...Class.forName(driverName);

			// get the connection
			// ...bookConn = DriverManager.getConnection( dbUrl, userId, passWord);

			// new -- bookConn = DriverManager
			// new --
			// .getConnection("jdbc:ucanaccess://c:/enterprise/data/MyAccess2000.mdb");
			// //jdbc:ucanaccess://c:/pippo.mdb

			// bookConn = DriverManager
			// .getConnection("jdbc:ucanaccess://c:/enterprise/data/MyAccess2000.mdb");
			// //jdbc:ucanaccess://c:/pippo.mdb

			connection = DriverManager.getConnection(dbUrl, userId, passWord);
			System.out.println("CONNECTION ESTABLISHED....");

		}
		// ...catch(ClassNotFoundException e){
		// ... log("Failed to find driver class" + e);
		// ... }
		catch (SQLException e) {
			log("Failed to get connection" + e);
		}

	}

	protected Properties loadProps() {

		// create a default set of properties
		// in case the real one cannot be found
		Properties defaultProps = new Properties();

		defaultProps.put("driverName", "sun.jdbc.odbc.JdbcOdbcDriver");
		defaultProps.put("dbUrl", "jdbc:odbc:RainForestDSN");
		defaultProps.put("userId", "student");
		defaultProps.put("passWord", "student");

		Properties theProps = new Properties(defaultProps);

		// display the path first
		java.net.URL thePropsPath = getClass().getResource(PROPS_FILENAME);
		log("Loading props file: " + thePropsPath);

		// now load the props file
		try {
			theProps.load(getClass().getResourceAsStream(PROPS_FILENAME));
			log("Loaded props file successfully");
		} catch (Exception exc) {
			log("Didn't find props file...using defaults");
			theProps.list(System.out);
		}

		return theProps;
	}

	@Override
	public ArrayList getBookingsList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList getBookingsOfbook(int book) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList getBookingsOfCustomer(int customer) {
		// TODO Auto-generated method stub
		try {
			ArrayList customers = new ArrayList();
			String sql = "select * from booking where customer = " + customer;
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				customers.add(new Booking(rs.getInt("ID"), customer, rs.getInt("book"), rs.getInt("status"), rs.getTimestamp("returndate")));				
			}
			if (customers.size() > 0)
				return customers;
			else 
				return null;
		} catch (Exception ex) {

		}
		return null;
	}

	@Override
	public Booking getBooking(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean put(Booking booking) {
		// TODO Auto-generated method stub
		try {
			String sql = "insert into booking (customer, book, status, returndate) values (?,?,?,?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, booking.getCustomer());
			stmt.setInt(2, booking.getBook());
			stmt.setInt(3, booking.getStatus());
			stmt.setNull(4, Types.TIMESTAMP);
			// System.out.println(booking.getBook() + "" + booking.getCustomer() + "" +
			// booking.getStatus());
			if (stmt.executeUpdate() == 1)
				return true;
		} catch (Exception ex) {
			log(ex);
		}
		return false;
	}

	@Override
	public boolean change(Booking booking) {
		// TODO Auto-generated method stub
		try {
			String sql = "update booking set status = ? where id = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, booking.getStatus());
			stmt.setInt(2, booking.getId());
			stmt.executeUpdate();
			if (booking.getStatus() == 4) {
				Date currentDate = new Date();
				Calendar c = Calendar.getInstance();
		        c.setTime(currentDate);
		        c.add(Calendar.DAY_OF_MONTH, 14);
		        Date currentDatePlus14 = c.getTime();

				sql = "update booking set returndate = ? where id = ?";
				//stmt.setLongDate(1, new java.sql.Date(currentDatePlus14.getTime()));
				stmt.setTimestamp(1, new java.sql.Timestamp(currentDatePlus14.getTime()));
				stmt.setInt(2, booking.getId());
				stmt.executeUpdate();
			}
			return true;
		} catch (Exception ex) {
			log(ex);
		}
		return false;
	}

}
