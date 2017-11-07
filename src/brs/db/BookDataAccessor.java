package brs.db;

import java.util.ArrayList;

import brs.model.Book;

/**
 *  This class provides a data access mechanism for the video recordings database.
 *  Methods are available to get a list of video recordings.   <p>
 *
 */
public abstract class BookDataAccessor {

	/**
	 *  The name of the ini file
	 */
	private final String INI_FILENAME = "rainforest.ini";


	/**
	 *  Get a list of book categories from the database
	 *
	 *  @return a list of String objects
	 */
	public abstract ArrayList getBookCategories();

	/**
	 *  Get a list of Book from the database for the given category
	 *
	 *  @return a list of Book objects
	 */
	public abstract ArrayList getBooks(String theCategory);

	/**
	 *  Returns an Book based on the id
	 *
	 */
	public abstract Book getBook(int bookId);
	
	public abstract boolean put(Book book);
	
	public abstract boolean change(Book book);

	/**
	 *  Convenience method for logging messages to standard out.
	 */
	protected void log(Object msg) {
		System.out.println("BookDataAccessor: " + msg);
	}

}