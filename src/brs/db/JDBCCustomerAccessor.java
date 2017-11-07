package brs.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import brs.model.Customer;

public class JDBCCustomerAccessor extends CustomerDataAccessor {

	private final String PROPS_FILENAME = "database.properties";

	/**
	 * The database connection. This is declared as a member variable so it can be
	 * used throughout the various methods.
	 */
	private Connection connection;

	/**
	 * Constructs the data accessor
	 */
	public JDBCCustomerAccessor() {

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
	public ArrayList getCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer getCustomer(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer getCustomer(String email, String password) {
		// TODO Auto-generated method stub

		try {
			Statement myStmt = connection.createStatement();

			ResultSet myRs = myStmt.executeQuery(
					"SELECT * FROM Customer where email = '" + email + "' and password = '" + password + "'");
			if (myRs.next()) {
				return new Customer(myRs.getInt("ID"), myRs.getString("first_name"), myRs.getString("last_name"), email,
						password);
			} else {
				return null;
			}
		} catch (Exception ex) {
			System.out.print(ex);
		}
		return null;
	}

}
