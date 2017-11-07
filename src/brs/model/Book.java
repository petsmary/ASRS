package brs.model;

import java.util.*;

/**
 * This class represents a Book. It describes the author, title, price,
 * category, imageName and isbn.
 * <p>
 *
 */
public class Book implements Comparable {

	//
	// DATA MEMBERS
	//

	/**
	 * The book author
	 */
	protected String author;

	/**
	 * The book title
	 */
	protected String title;

	/**
	 * The book category
	 */
	protected String category;

	/**
	 * The book image name
	 */
	protected String imageName;

	/**
	 * The book id
	 */
	protected int bookId;

	/**
	 * The book isbn
	 */
	protected String isbn;

	/**
	 * The release year
	 */
	protected String year;

	protected int qty;
	
	protected boolean isArrive;

	//
	// CONSTRUCTORS
	//

	/**
	 * Basic default constructor
	 */
	public Book() {
		// default constructor
	}

	/**
	 * Constructs a book w/ given parameter values
	 */
	public Book(String author, String title, String category, String imageName, String isbn, String year) {

		this.author = author;
		this.title = title;
		this.category = category;
		this.imageName = imageName;
		this.isbn = isbn;
		this.year = year;
	}

	/**
	 * Constructs a book w/ given parameter values
	 */
	public Book(String author, String title) {

		this(author, title, "", "", "", "");
	}

	/**
	 * Constructs a book w/ given parameter values
	 */
	public Book(String author, String title, String category, String imageName, String isbn, String year, int id,
			int qty, boolean isArrive) {

		this(author, title, category, imageName, isbn, year);

		this.bookId = id;
		this.qty = qty;
		this.isArrive = isArrive;
	}

	/**
	 * Returns the book title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the book title
	 */
	public void setTitle(String theTitle) {
		title = theTitle;
	}

	/**
	 * Returns the product name
	 */
	public String getName() {
		return title;
	}

	/**
	 * Returns the book category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Sets the book category
	 */
	public void setCategory(String theCategory) {
		category = theCategory;
	}

	/**
	 * Returns the book image name
	 */
	public String getImageName() {
		return imageName;
	}

	/**
	 * Sets the book image name
	 */
	public void setImageName(String theImageName) {
		imageName = theImageName;
	}

	/**
	 * Returns the book id
	 */
	public int getBookId() {
		return bookId;
	}

	/**
	 * Sets the book id
	 */
	public void setBookId(int theBookId) {
		bookId = bookId;
	}

	/**
	 * Returns the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Sets the author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * Returns the book id.
	 */
	public int getProductId() {
		return bookId;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public boolean isArrive() {
		return isArrive;
	}

	public void setArrive(boolean isArrive) {
		this.isArrive = isArrive;
	}

	/**
	 * Returns the title and author of book
	 */
	public String toString() {
		return title + " - " + author;
	}

	/**
	 * Allow us to sort the recordings by title
	 */
	public int compareTo(Object object) {

		Book book = (Book) object;
		String targetTitle = book.getTitle();

		return title.compareTo(targetTitle);
	}

}