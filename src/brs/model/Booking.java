package brs.model;

import java.util.Date;

public class Booking {

	protected int id;
	protected int customer;
	protected int book;
	protected int status;
	protected Date returnDate;

	public Booking(int id, int customer, int book, int status, Date returnDate) {
		super();
		this.id = id;
		this.customer = customer;
		this.book = book;
		this.status = status;
		this.returnDate = returnDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomer() {
		return customer;
	}

	public void setCustomer(int customer) {
		this.customer = customer;
	}

	public int getBook() {
		return book;
	}

	public void setBook(int book) {
		this.book = book;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

}
