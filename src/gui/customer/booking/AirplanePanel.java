package gui.customer.booking;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class AirplanePanel extends JPanel {

	private ArrayList<JPanel> seatPanel = new ArrayList<JPanel>();
	
	public AirplanePanel(JFrame frame, Vector flight) {
		setSize(800, 400);
		String airplane = (String) flight.get(model.Flight.AIRPLANE);
		Vector vector = controller.AllAirplanes.getInstance().getAirplane(airplane);
		String firstRows = (String) vector.get(model.Airplane.FIRST);
		//String b
		seatPanel.add(new SeatPanel(frame, this));
		for(int i=0; i<seatPanel.size(); i++) {
			add(seatPanel.get(i));		
		}
	}

}
