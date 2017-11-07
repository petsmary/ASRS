package asrs.gui.airline;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class AirlineFrame extends JFrame {

	private Object id;
	private JFrame frame;	
	private ViewAirplanePanel airplaneAll;
	private AddFlightPanel addFlight;
	private JTabbedPane tabbedPane;
	private ChangeBookingStatus change;
	
	public AirlineFrame(JFrame frame, String username) {
		setTitle("Welcome to Airline Seat Reservation System - Airline");
		
		this.frame = frame;
		Vector v = asrs.controller.AllAirplanes.getInstance().getAirline(asrs.model.Airline.LOGINNAME, username);
		Vector airline = (Vector) v.get(0);
		id = airline.get(asrs.model.Airline.NAME);
		
		tabbedPane = new JTabbedPane();
		airplaneAll = new ViewAirplanePanel(this, id);
		addFlight = new AddFlightPanel(this, id);
		change = new ChangeBookingStatus(this, id);
		tabbedPane.add(airplaneAll, "All airplanes");
		tabbedPane.add(addFlight, "Add Flight");
		tabbedPane.add(change, "Change Booking Status");
		
		add(tabbedPane);
		setSize(1280, 400);
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
