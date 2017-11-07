package asrs.gui.customer;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class CustomerFrame extends JFrame {

	private JFrame frame;	
	private JPanel searchPanel;
	private JPanel checkPanel;
	private JTabbedPane tabbedPane;
			
	public CustomerFrame(JFrame frame) {

		this.frame = frame;
		
		setTitle("Welcome to Airline Seat Reservation System - Customer");
		
		tabbedPane = new JTabbedPane();
		searchPanel = new SearchFlightPanel(this);
		checkPanel = new CheckReservationPanel(this);
		tabbedPane.add(searchPanel, "Do Flight Reservation");
		tabbedPane.add(checkPanel, "Check Flight Reservation");
		
		add(tabbedPane);
		setSize(600, 400);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setVisible(true);
		

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				exit();
			}
		});
	}
	
	private void exit() {
		setVisible(false);
		dispose();
		frame.setEnabled(true);
	}
}
