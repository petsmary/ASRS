package gui.customer.booking;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BookingFrame extends JFrame {

	private JFrame frame;
	
	public BookingFrame(JFrame frame, Vector flight, String type) {
		this.frame = frame;
		frame.setVisible(false);
		toFront();
		JPanel panel = new AirplanePanel(this, flight, type);
		add(panel);
		setSize(1000, 800);
		setLocationRelativeTo(this);
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				exit();
			}
		});
	}
	
	public void exit() {
		setVisible(false);
		dispose();
		frame.setVisible(true);
	}
}
