package brs.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import brs.db.BookDataAccessor;
import brs.db.BookingDataAccessor;
import brs.db.CustomerDataAccessor;
import brs.db.JDBCBookAccessor;
import brs.db.JDBCBookingAccessor;
import brs.db.JDBCCustomerAccessor;
import brs.model.Book;
import brs.model.Booking;
import brs.model.Customer;

public class CancellBookingPanel extends JPanel {

	protected JTextField email = new JTextField(10);

	protected JPasswordField password = new JPasswordField(10);

	protected CustomerDataAccessor customerDataAccessor;

	protected BookingDataAccessor bookingDataAccessor;

	protected BookDataAccessor bookDataAccessor;

	protected ArrayList<Booking> bookingList = new ArrayList();
	protected JList bookingListBox = new JList();
	protected JButton cancellButton = new JButton("Cancel Booking");
	protected JButton exitButton = new JButton("Exit");

	public CancellBookingPanel() {
		customerDataAccessor = new JDBCCustomerAccessor();
		bookingDataAccessor = new JDBCBookingAccessor();
		bookDataAccessor = new JDBCBookAccessor();
		setLayout(new BorderLayout());
		JPanel customerPanel = new JPanel(new GridLayout(4, 2));
		JLabel lemail = new JLabel("	Email:	");
		JLabel lpassword = new JLabel("	Password:	");
		JButton button = new JButton("	Enter");
		customerPanel.add(new JLabel("	Input Customer Detail:	"));
		customerPanel.add(new JLabel(""));
		customerPanel.add(lemail);
		customerPanel.add(lpassword);
		customerPanel.add(email);
		customerPanel.add(password);
		customerPanel.add(button);
		add(BorderLayout.NORTH, customerPanel);

		JPanel bottomPanel = new JPanel();
		bottomPanel.add(cancellButton);
		bottomPanel.add(exitButton);

		bookingListBox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane bookingScrollPane = new JScrollPane(bookingListBox);

		add(BorderLayout.CENTER, bookingScrollPane);
		add(BorderLayout.SOUTH, bottomPanel);

		cancellButton.setEnabled(false);

		cancellButton.addActionListener(new CancellsActionListener());
		exitButton.addActionListener(new ExitActionListener());
		button.addActionListener(new GoItemListener());
		bookingListBox.addListSelectionListener(new BookingListSelectionListener());
	}

	class CancellsActionListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			int index = bookingListBox.getSelectedIndex();
			Booking booking = bookingList.get(index);
			Book book = books.get(index);
			System.out.println(book);
			if (booking.getStatus() == 3) {
				book.setQty(book.getQty() + 1);
				bookDataAccessor.change(book);
			}
			booking.setStatus(5);
			if (bookingDataAccessor.change(booking)) {
				JOptionPane.showMessageDialog(CancellBookingPanel.this, "Successful to cancel booking", "Reservation",
						JOptionPane.INFORMATION_MESSAGE);
			}
			new GoItemListener().actionPerformed(event);
		}
	}

	protected ArrayList<Book> books = new ArrayList<Book>();

	class GoItemListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			// the real work occurs in the populateListBox() method.
			Customer customer = customerDataAccessor.getCustomer(email.getText(), password.getText());
			books = new ArrayList();
			ArrayList<String> column = new ArrayList();
			if (customer != null) {
				bookingList = bookingDataAccessor.getBookingsOfCustomer(customer.getId());
				for (int i = 0; i < bookingList.size(); i++) {
					Book book = bookDataAccessor.getBook(bookingList.get(i).getBook());
					books.add(book);
					column.add(book.getTitle() + " - " + book.getAuthor() + " - " + bookingList.get(i).getStatus());
				}
				bookingListBox.setListData(column.toArray());
			}
		}
	}

	class ExitActionListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			System.exit(0);
		}
	}

	class BookingListSelectionListener implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent event) {

			if (bookingListBox.isSelectionEmpty()) {
				cancellButton.setEnabled(false);
			} else {
				if (bookingList.get(bookingListBox.getSelectedIndex()).getStatus() == 5  ) {
					cancellButton.setEnabled(false);
				} else if (bookingList.get(bookingListBox.getSelectedIndex()).getStatus() == 4) {
					cancellButton.setEnabled(false);
				}	else {
					cancellButton.setEnabled(true);
				}
			}

		}
	}
}
