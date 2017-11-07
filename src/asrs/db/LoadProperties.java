package asrs.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class LoadProperties {
	private final String PROPS_FILENAME;
	private static Connection connection;

	public static LoadProperties getInstance() {
		return new LoadProperties("database.properties");
	}
	
	private LoadProperties(String PROPS_FILENAME) {
		this.PROPS_FILENAME = PROPS_FILENAME;
		Properties properties = loadprops();
		String driverName = properties.getProperty("driverName");
		String dbUrl = properties.getProperty("dbUrl");
		String userId = properties.getProperty("userId");
		String passWord = properties.getProperty("passWord");
		try {
			Class.forName(driverName);
			connection = DriverManager.getConnection(dbUrl, userId, passWord);
			log("CONNECTION ESTABLISHED....");
		} catch (SQLException | ClassNotFoundException e) {
			log("Failed to get connection" + e);
		}
	}
	
	private Properties loadprops() {
		Properties defaultProps = new Properties();

		defaultProps.put("driverName", "sun.jdbc.odbc.JdbcOdbcDriver");
		defaultProps.put("dbUrl", "jdbc:odbc:RainForestDSN");
		defaultProps.put("userId", "student");
		defaultProps.put("passWord", "student");
		
		Properties theProps = new Properties(defaultProps);
		
		java.net.URL thePropsPath = getClass().getResource(PROPS_FILENAME);
		log("Loading props file: " + thePropsPath);

		try {
			theProps.load(getClass().getResourceAsStream(PROPS_FILENAME));
			log("Loaded props file successfully");
		}
		catch (Exception exc) {
			log("Didn't find props file...using defaults");
			theProps.list(System.out);
		}
		return theProps;
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	private void log(String message) {
		System.out.println(message);
	}
}
