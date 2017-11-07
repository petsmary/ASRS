package brs.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import brs.db.BookDataAccessor;
import brs.db.BookingDataAccessor;
import brs.db.CustomerDataAccessor;
import brs.db.JDBCBookAccessor;
import brs.db.JDBCBookingAccessor;
import brs.db.JDBCCustomerAccessor;
import brs.model.Book;
import brs.model.Booking;
import brs.model.Customer;

/**
 * This class displays the details of a music recording. It shows the artist's
 * name, recording title and price. Also, the dialog shows an image of the
 * recording along with its track list.
 * <p>
 *
 * Usage Example:
 *
 * <pre>
 *
 * MusicDetailsDialog myDetailsDialog = new MusicDetailsDialog(myParentFrame, myRecording);
 * myDetailsDialog.setVisible(true);
 *
 * </pre>
 *
 */
public class BookDetailsDialog extends JDialog {

	protected CustomerDataAccessor customerDataAccessor;

	protected BookingDataAccessor bookingDataAccessor;

	protected BookDataAccessor bookDataAccessor;

	/**
	 * A holder for the book that is passed in during construction
	 */
	protected Book myBook;

	/**
	 * A holder for the parent frame that is passed in during construction
	 */
	protected Frame parentFrame;

	protected JTextField email = new JTextField(10);

	protected JPasswordField password = new JPasswordField(10);

	/**
	 * Constructs a modal dialog for a given book. This version uses the author and
	 * recording title for the dialog title.
	 *
	 * @param theParentFrame
	 *            the parent frame for this dialog
	 * @param theBook
	 *            the music recording to display
	 */
	public BookDetailsDialog(Frame theParentFrame, Book theBook) {

		this(theParentFrame, "Book Details for " + theBook.toString(), theBook);
	}

	/**
	 * Constructs a modal dialog for a given book. This version allows you to
	 * customize the title
	 *
	 * @param theParentFrame
	 *            the parent frame for this dialog
	 * @paran theTitle the title of the dialog
	 * @param theBook
	 *            the book to display
	 */
	public BookDetailsDialog(Frame theParentFrame, String theTitle, Book theBook) {

		super(theParentFrame, theTitle, true); // creates a modal dialog

		myBook = theBook;
		parentFrame = theParentFrame;
		customerDataAccessor = new JDBCCustomerAccessor();
		bookingDataAccessor = new JDBCBookingAccessor();
		bookDataAccessor = new JDBCBookAccessor();

		buildGui();
	}

	/**
	 * This method covers the details of creating and arranging the dialog
	 * components.
	 */
	private void buildGui() {

		Container container = this.getContentPane();

		container.setLayout(new BorderLayout());

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));

		//
		// author info panel
		//
		JPanel infoPanel = new JPanel();
		infoPanel.setBorder(new EmptyBorder(10, 10, 0, 10));

		infoPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// create and arrange the label, "Author: ..."
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 3;
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(10, 0, 2, 10);
		JLabel artistLabel = new JLabel("Artist:  " + myBook.getAuthor());
		artistLabel.setForeground(Color.black);
		infoPanel.add(artistLabel, c);

		// create and arrange the label, "Title: ..."
		c.gridy = GridBagConstraints.RELATIVE;
		c.insets = new Insets(2, 0, 10, 10);
		JLabel titleLabel = new JLabel("Title:  " + myBook.getTitle());
		titleLabel.setForeground(Color.black);
		infoPanel.add(titleLabel, c);

		// create and arrange the label, "Category ..."
		JLabel categoryLabel = new JLabel("Category:  " + myBook.getCategory());
		c.insets = new Insets(2, 0, 2, 0);
		categoryLabel.setForeground(Color.black);
		infoPanel.add(categoryLabel, c);

		// create and arrange the label, "Price: $..."
		JLabel priceLabel = new JLabel("Number of Books: " + myBook.getQty());
		c.insets = new Insets(10, 0, 2, 0);
		priceLabel.setForeground(Color.black);
		infoPanel.add(priceLabel, c);

		// create an arrange the book icon
		c.gridx = 3;
		c.gridy = 1;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = 5;
		c.fill = GridBagConstraints.NONE;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.insets = new Insets(5, 5, 20, 0);
		String imageName = myBook.getImageName();
		ImageIcon bookIcon = null;
		JLabel bookLabel = null;

		// attempt to load the image
		try {
			if (imageName.trim().length() == 0) {
				bookLabel = new JLabel("  Image not available  ");
			} else {
				bookIcon = new ImageIcon(getClass().getResource("images/books/" + imageName));
				bookLabel = new JLabel(bookIcon);
			}
		} catch (Exception exc) {
			// okay...couldn't load. Just give a text message.
			bookLabel = new JLabel("  Image not available  ");
		}

		bookLabel.setBorder(BorderFactory.createRaisedBevelBorder());
		bookLabel.setToolTipText(myBook.getAuthor());

		infoPanel.add(bookLabel, c);

		container.add(BorderLayout.NORTH, infoPanel);

		JPanel customerPanel = new JPanel(new GridLayout(3, 2));
		JLabel lemail = new JLabel("	Email:	");
		JLabel lpassword = new JLabel("	Password:	");
		customerPanel.add(new JLabel("	Customer Detail:	"));
		customerPanel.add(new JLabel(""));
		customerPanel.add(lemail);
		customerPanel.add(lpassword);
		customerPanel.add(email);
		customerPanel.add(password);

		container.add(BorderLayout.CENTER, customerPanel);

		//
		// Create and add the "OK" button
		//
		JPanel bottomPanel = new JPanel();
		JButton waitingButton = new JButton("Waiting Book");
		JButton reservationButton = new JButton("Reserse Book");
		JButton holdingButton = new JButton("Borrow Book");
		JButton okButton = new JButton("Close");

		waitingButton.addActionListener(new WaitButtonActionListener());
		reservationButton.addActionListener(new RerButtonActionListener());
		holdingButton.addActionListener(new HoldButtonActionListener());
		okButton.addActionListener(new OkButtonActionListener());

		if (myBook.getQty() > 0) {
			bottomPanel.add(holdingButton);
		} else {
			if (myBook.isArrive()) {
				bottomPanel.add(waitingButton);
			} else {
				bottomPanel.add(reservationButton);
			}
		}

		bottomPanel.add(okButton);
		container.add(BorderLayout.SOUTH, bottomPanel);

		this.pack();

		// locate this window based off of the parent frame
		Point parentLocation = parentFrame.getLocation();
		this.setLocation(parentLocation.x + 50, parentLocation.y + 50);
	}

	//
	// INNER CLASS
	//

	/**
	 * Closes the dialog when ok button is pressed
	 */
	class OkButtonActionListener implements ActionListener {

		/**
		 * Simply closes this dialog.
		 */
		public void actionPerformed(ActionEvent event) {
			setVisible(false);
		}
	}

	class WaitButtonActionListener implements ActionListener {

		/**
		 * Simply closes this dialog.
		 */
		public void actionPerformed(ActionEvent event) {
			Customer customer = customerDataAccessor.getCustomer(email.getText(), password.getText());
			if (customer != null) {
				Booking booking = new Booking(0, customer.getId(), myBook.getBookId(), 2, null);
				if (bookingDataAccessor.put(booking)) {
					
					JOptionPane.showMessageDialog(BookDetailsDialog.this,
							"Successful to add in waitinf list, " + customer.getLastName(), "Reservation",
							JOptionPane.INFORMATION_MESSAGE);
					setVisible(false);
					
				} else {

				}
			} else {
				JOptionPane.showMessageDialog(BookDetailsDialog.this, "Your email or password is incorrect", "Reservation",
						JOptionPane.INFORMATION_MESSAGE);
			}

		}
	}

	class RerButtonActionListener implements ActionListener {

		/**
		 * Simply closes this dialog.
		 */
		public void actionPerformed(ActionEvent event) {
			Customer customer = customerDataAccessor.getCustomer(email.getText(), password.getText());
			if (customer != null) {
				Booking booking = new Booking(0, customer.getId(), myBook.getBookId(), 1, null);
				if (bookingDataAccessor.put(booking)) {
					JOptionPane.showMessageDialog(BookDetailsDialog.this,
							"Successful to add in reservation list, " + customer.getLastName(), "Reservation",
							JOptionPane.INFORMATION_MESSAGE);
					
					setVisible(false);
					//setVisible(false);
				} else {

				}
			} else {
				JOptionPane.showMessageDialog(BookDetailsDialog.this, "Your email or password is incorrect", "Reservation",
						JOptionPane.INFORMATION_MESSAGE);
			}

		}
	}

	class HoldButtonActionListener implements ActionListener {

		/**
		 * Simply closes this dialog.
		 */
		public void actionPerformed(ActionEvent event) {
			Customer customer = customerDataAccessor.getCustomer(email.getText(), password.getText());
			if (customer != null) {
				Booking booking = new Booking(0, customer.getId(), myBook.getBookId(), 3, null);
				if (bookingDataAccessor.put(booking)) {
					myBook.setQty(myBook.getQty() - 1);
					if (bookDataAccessor.change(myBook)) {						
						JOptionPane.showMessageDialog(BookDetailsDialog.this,
								"Successful to borrow book, feel free pick up the book, " + customer.getLastName(),
								"Reservation", JOptionPane.INFORMATION_MESSAGE);
						setVisible(false);
					}
					
				} else {

				}
			} else {
				JOptionPane.showMessageDialog(BookDetailsDialog.this, "Your email or password is incorrect", "Reservation",
						JOptionPane.INFORMATION_MESSAGE);
			}

		}
	}
}