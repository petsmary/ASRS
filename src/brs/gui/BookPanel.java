package brs.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import brs.db.BookDataAccessor;
import brs.db.JDBCBookAccessor;
import brs.model.Book;

/**
 *  This panel is responsible for displaying books.  It contains
 *  a combo box for selecting the category.  Once a category is selected then
 *  a list of books are displayed in a list box.  The user can then
 *  highlight a book and get the details.
 *
 *
 */
public class BookPanel extends JPanel {

	/**
	 *  The label to hold the text "Select a Book Category"
	 */
	protected JLabel selectionLabel;

	/**
	 *  The combo box to hold a list of book categories
	 */
	protected JComboBox categoryComboBox;

	/**
	 *  A panel to contain components.
	 */
	protected JPanel topPanel;

	/**
	 * List Box for displaying the books
	 */
	protected JList bookListBox;

	/**
	 *  Supporting scroll pane for the bookListBox
	 */
	protected JScrollPane bookScrollPane;

	/**
	 *  Button labeled "Details"
	 */
	protected JButton detailsButton;

	/**
	 *  Button labeled "Clear"
	 */
	protected JButton clearButton;

	/**
	 *  Button labeled "Exit"
	 */
	protected JButton exitButton;

	/**
	 *  A panel to contain components.
	 */
	protected JPanel bottomPanel;

	/**
	 *  A reference to the parent frame
	 */
	private JFrame parentFrame;


	/**
	 *  Array list used to hold books
	 */
	 protected ArrayList bookArrayList;


	/**
	 *  A reference to the data accessor for books
	 */
	 protected BookDataAccessor myDataAccessor;


	/**
	 *  Creates the GUI components and arranges them
	 *  in the container.
	 */
	public BookPanel(JFrame theParentFrame) {

		parentFrame = theParentFrame;

		//
		//Intialize data accessor here.
		//
		myDataAccessor = new JDBCBookAccessor();


		selectionLabel = new JLabel("Select a Book Category");

		// create and populate the combo box
		categoryComboBox = new JComboBox();
		categoryComboBox.addItem("-------");
		ArrayList categoryArrayList = myDataAccessor.getBookCategories();

		String aCategory;
		Iterator iterator = categoryArrayList.iterator();

		while (iterator.hasNext()) {
			aCategory = (String) iterator.next();
			categoryComboBox.addItem(aCategory);
		}

		// create the top panel
		topPanel = new JPanel();

		// create the list box and associated scroll panes
		bookListBox = new JList();
		bookListBox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		bookScrollPane = new JScrollPane(bookListBox);

		detailsButton = new JButton("Go Booking");
		clearButton = new JButton("Clear");
		exitButton = new JButton("Exit");

		bottomPanel = new JPanel();

		// setting the layout
		this.setLayout(new BorderLayout());

		// set layout and add components for top panel
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		topPanel.add(selectionLabel);
		topPanel.add(categoryComboBox);

		this.add(BorderLayout.NORTH, topPanel);

		this.add(BorderLayout.CENTER, bookScrollPane);

		// use the default flow layout for the bottom panel
		bottomPanel.add(detailsButton);
		bottomPanel.add(clearButton);
		bottomPanel.add(exitButton);

		this.add(BorderLayout.SOUTH, bottomPanel);

		// state management
		detailsButton.setEnabled(false);
		clearButton.setEnabled(false);

		//
		//  REGISTER LISTENERS
		//
		detailsButton.addActionListener(new DetailsActionListener());
		exitButton.addActionListener(new ExitActionListener());
		categoryComboBox.addItemListener(new GoItemListener());
		clearButton.addActionListener(new ClearActionListener());
		bookListBox.addListSelectionListener(new BookListSelectionListener());
	}


	/**
	 *  Simple helper function to return the
	 *  currently selected product
	 *
	 */

	public Book getSelectedProduct() {

		// get the currently selected
		// return the product to the caller
		return (Book) bookListBox.getSelectedValue();
	}

	/**
	 *  Populate the list box w/ the categories
	 *
	 *  <pre>
	 *
	 *    1.  If the selected category is not equal to "----" then
	 *        1a.  Get the array list from data accessor
	 *    2.  Else create an empty book array list
	 *
	 *    3.  If the list is not empty then enable "Clear" button
	 *
	 *  </pre>
	 */
	protected void populateListBox() {

		String category = (String) categoryComboBox.getSelectedItem();

		if (! category.startsWith("---")) {
			bookArrayList = myDataAccessor.getBooks(category);
		}
		else {
			bookArrayList = new ArrayList();
		}

		Object[] theData = bookArrayList.toArray();
		bookListBox.setListData(theData);

		// clear button is enabled if we have some data
		if (bookArrayList.size() > 0) {
			clearButton.setEnabled(true);
		}
		else {
			clearButton.setEnabled(false);
		}
	}


	/**
	 *  <pre>
	 *
	 *  When the go button is pressed, we will:
	 *
	 *  1.  Find out the selected category from combo box
	 *  2.  Get a list of books for the selected category from the data accessor
	 *  3.  Populate the list box w/ the author and titles
	 *
	 *  </pre>
	 */
	class GoActionListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			// the real work occurs in the populateListBox() method.
			populateListBox();
		}
	}

	/**
	 *  When a category is selected, we will:
	 *
	 *  <pre>
	 *
	 *  1.  Find out the selected category from combo box
	 *  2.  Get a list of sCDs for the selected category from the data accessor
	 *  3.  Populate the list box w/ the author and titles
	 *
	 *  </pre>
	 */
	class GoItemListener implements ItemListener {

		public void itemStateChanged(ItemEvent event) {

			if (event.getStateChange() == ItemEvent.SELECTED) {
				populateListBox();
			}
		}
	}

	/**
	 *  When the details button is pressed, we will
	 *
	 *  1.  Get the selected item index from the list box
	 *  2.  Get the desired book from the book array list
	 *  3.  Create a details dialog for the book
	 *  4.  Show the details dialog
	 */

	class DetailsActionListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			// null functionality for this exercise
			int index = bookListBox.getSelectedIndex();

						Book myMusicRecording =
							(Book) bookArrayList.get(index);

						BookDetailsDialog myDetailsDialog =
							new BookDetailsDialog(parentFrame, myMusicRecording);

			myDetailsDialog.setVisible(true);
		}
	}

	/**
	 *  When the "Clear" button is pressed, we will:
	 *
	 *  <pre>
	 *
	 *  1.  Clear the videoListBox
	 *  2.  Set the first category item as selected
	 *
	 *  </pre>
	 */
	class ClearActionListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			// create an empty array
			Object[] noData = new Object[1];

			// this will clear out the list
			bookListBox.setListData(noData);

			// set the first category item as selected
			categoryComboBox.setSelectedIndex(0);
		}
	}
	
	class ExitActionListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			System.exit(0);
		}
	}

	class BookListSelectionListener implements ListSelectionListener {

			public void valueChanged(ListSelectionEvent event) {

				if (bookListBox.isSelectionEmpty()) {
					detailsButton.setEnabled(false);
				}
				else {
					detailsButton.setEnabled(true);
				}
			}
	}

}