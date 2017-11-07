package brs.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import brs.model.Book;

/**
 * This class implements a data access mechanism for the book database. Methods
 * are available to get a list of the book categories and a list of recordings.
 * <p>
 *
 * Usage Example:
 * 
 * <pre>
 *
 *    // create and load the data accessor
 *    BookDataAccessor myDataAccessor = new JDBCBookAccessor();
 *
 *    // get a list of available categories;
 *    ArrayList cats = myDataAccessor.getBookCategories();
 *    ...
 *
 *    // get a list of jazz recordings
 *    ArrayList jazzRecordingList = myDataAccessor.getBookRecordings("Jazz");
 *    ...
 *
 *    // get a particular recording
 *    Bookrecording aJazzRecording = myDataAccessor.getBookRecording(42);
 *    ...
 *
 * </pre>
 *
 * @author Java Curriculum Development Team
 */
public class JDBCBookAccessor extends BookDataAccessor {

	/**
	 * The name of the ini file. This contains all the details needed to connect to
	 * the data source
	 */
	private final String PROPS_FILENAME = "database.properties";

	/**
	 * The database connection. This is declared as a member variable so it can be
	 * used throughout the various methods.
	 */
	private Connection bookConn;

	/**
	 * Constructs the data accessor
	 */
	public JDBCBookAccessor() {

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

			bookConn = DriverManager.getConnection(dbUrl, userId, passWord);
			System.out.println("CONNECTION ESTABLISHED....");

		}
		// ...catch(ClassNotFoundException e){
		// ... log("Failed to find driver class" + e);
		// ... }
		catch (SQLException e) {
			log("Failed to get connection" + e);
		}

	}

	/**
	 * Get a list of book categories from the database
	 *
	 * @return a list of String objects
	 */
	public ArrayList getBookCategories() {

		ArrayList categoryList = new ArrayList();

		try {

			// create the statement
			Statement myStmt = bookConn.createStatement();

			// execute the query
			ResultSet myRs = myStmt.executeQuery("SELECT type FROM Category");

			String category;

			// iterate over each entry in
			// the resultset
			while (myRs.next()) {

				// get the column called 'name'
				category = myRs.getString("type");

				// add the value to the ArrayList
				categoryList.add(category);
			}

			// close the variables
			myRs.close();
			myStmt.close();
		} catch (SQLException e) {
			log("Failed to execute SQL " + e);
		}

		// return the list of categories
		return categoryList;
	}

	public ArrayList getBooks(String theCategory) {
		ArrayList bookList = new ArrayList();
		try {

			// create a variable for later use
			Book book;

			// create a statement
			Statement myStmt = bookConn.createStatement();

			// create the SQL string using the supplied parameter
			String bookSql = "SELECT * FROM Book";
			bookSql += " WHERE category='" + theCategory + "'";

			// execute the query
			ResultSet myRs = myStmt.executeQuery(bookSql);

			// iterate over the resultset
			while (myRs.next()) {

				// create a new Musicrecording object
				// for each row in the resultset
				book = new Book(myRs.getString("author"), myRs.getString("title"), myRs.getString("category"),
						myRs.getString("imageName"), myRs.getString("isbn"), myRs.getString("year"), myRs.getInt("ID"),
						myRs.getInt("qty"), myRs.getBoolean("isArrive"));

				// add the new object to the ArrayList
				bookList.add(book);
			}

			// close the objects
			myRs.close();
			myStmt.close();
		} catch (SQLException e) {
			log("Failed to execute SQL" + e);
		}

		// return the recordings list
		return bookList;
	}

	public Book getBook(int bookId) {
		Book book = null;
		try {

			// create a statement
			Statement myStmt = bookConn.createStatement();

			// create the SQL string using the supplied parameter
			String bookSql = "SELECT * FROM Book";
			bookSql += " WHERE id='" + bookId + "'";

			// execute the query
			ResultSet myRs = myStmt.executeQuery(bookSql);

			// iterate over the resultset
			if (myRs.next()) {

				// create a new Musicrecording object
				// for each row in the resultset
				book = new Book(myRs.getString("author"), myRs.getString("title"), myRs.getString("category"),
						myRs.getString("imageName"), myRs.getString("isbn"), myRs.getString("year"), myRs.getInt("ID"),
						myRs.getInt("qty"), myRs.getBoolean("isArrive"));

			}

			// close the objects
			myRs.close();
			myStmt.close();
		} catch (SQLException e) {
			log("Failed to execute SQL" + e);
		}
		return book;
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
	public boolean put(Book book) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean change(Book book) {
		// TODO Auto-generated method stub
		try {
			String sql = "update book set author = ?, title = ?, category = ?, imagename = ?, isbn = ?, year = ?, qty = ?, isArrive = ? where id = ?";
			PreparedStatement stmt = bookConn.prepareStatement(sql);
			stmt.setString(1, book.getAuthor());
			stmt.setString(2, book.getTitle());
			stmt.setString(3, book.getCategory());
			stmt.setString(4, book.getImageName());
			stmt.setString(5, book.getIsbn());
			stmt.setString(6, book.getYear());
			stmt.setInt(7, book.getQty());
			stmt.setBoolean(8, book.isArrive());
			stmt.setInt(9, book.getBookId());
			if (stmt.executeUpdate() == 1)
				return true;
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return false;
	}

}