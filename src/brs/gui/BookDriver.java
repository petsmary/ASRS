package brs.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

/**
 *  This class is the  Music Test Harness for RainForest system. <p>
 *  It creates the MusicPanel and displays it
 *
 *  @author, Java Curriculum Development Team
 *
 */
public class BookDriver extends JFrame {


	/**
	 *  The book panel
	 */
	protected BookPanel bookPanel;
	
	protected CancellBookingPanel cancelPanel;
	
	protected PickupPanel PickupPanel;

	protected JTabbedPane panel;

	/**
	 *  This constructor creates the BookPanel
	 */
	public BookDriver(JFrame frame) {
		this();
		frame.setEnabled(false);
		WindowListener[] wl = (WindowListener[])this.getListeners(WindowListener.class);
        for (int i = 0; i < wl.length; i++) {
            this.removeWindowListener(wl[i]);
        }
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent evt) {
				frame.setEnabled(true);
				setVisible(false);
				dispose();
			}
		});
	}
	
	public BookDriver() {

		//
		//  LAYOUT FRAME
		//
		setTitle("Welcome to Book Reservation System");

		// Layout the UI
		Container container = this.getContentPane();
		container.setLayout(new BorderLayout());

		panel = new JTabbedPane();
		container.add(BorderLayout.CENTER, panel);
		bookPanel = new BookPanel(this);
		cancelPanel = new CancellBookingPanel();
		PickupPanel = new PickupPanel();
		panel.add(bookPanel, "Booking");
		panel.add(cancelPanel, "Cancel");
		panel.add(PickupPanel, "Pick up");

		setSize(500, 400);
		setLocation(100, 100);

		//
		//  ADD LISTENERS
		//
		this.addWindowListener(new WindowCloser());
	}

	//
	//  INNER CLASSES
	//

	/**
	 *  This class handles window closing event.  The event handler
	 *  simply exists the program.
	 */
	class WindowCloser extends WindowAdapter {

		/**
		 *  let's call our exit() method
		 */
		public void windowClosing(WindowEvent e) {

			setVisible(false);
			dispose();
			System.exit(0);

		}
	}

	/**
	 *  Main method to create the program and display the GUI frame.
	 */
	public static void main(String[] args)  {

		BookDriver myFrame = new BookDriver();

		myFrame.setVisible(true);
	}
}