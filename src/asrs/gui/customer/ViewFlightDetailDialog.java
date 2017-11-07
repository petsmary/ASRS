package asrs.gui.customer;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import asrs.controller.ASRS;
import asrs.gui.customer.booking.BookingFrame;

@SuppressWarnings("serial")
public class ViewFlightDetailDialog extends JDialog {

	private JFrame frame;

	private Vector flight;

	public ViewFlightDetailDialog(JFrame frame, String title, boolean modal, Vector flight) {
		super(frame, title, modal);
		this.flight = flight;
		this.frame = frame;
		jbInit();
		pack();
		setResizable(false);
		setLocationRelativeTo(frame);
		setVisible(true);
	}

	private void goBooking() {
		setVisible(false);
		dispose();
		new BookingFrame(frame, flight, jButton1.getName());
	}

	private void jbInit() {
		asrs.model.Flight f = asrs.controller.AllFlights.getInstance().getFlightDetail((String) flight.get(asrs.model.Flight.ID));
		panel = new JPanel(new GridBagLayout());
		this.flight = f.toVector();

		jlabel2 = new JLabel("	Name: " + f.getName());
		jlabel3 = new JLabel("	Source: " + f.getSource());
		jlabel4 = new JLabel("	Destination: " + f.getDestination());
		jlabel5 = new JLabel("	Departure Time: " + f.getDeparture());
		jlabel6 = new JLabel("	Arrive Time: " + f.getArrive());

		Vector flightDetails = f.getFlightDetail();
		Vector first = new Vector();
		Vector bussiness = new Vector();
		Vector economic = new Vector();

		for (Iterator it = flightDetails.iterator(); it.hasNext();) {
			Vector v = (Vector) it.next();
			if (v.get(asrs.model.FlightDetail.CLASSTYPE).equals(asrs.controller.ASRS.FIRST_CLASS)) {
				first = v;
			}

			if (v.get(asrs.model.FlightDetail.CLASSTYPE).equals(asrs.controller.ASRS.BUSSINESS_CLASS)) {
				bussiness = v;
			}

			if (v.get(asrs.model.FlightDetail.CLASSTYPE).equals(asrs.controller.ASRS.ECONOMIC_CLASS)) {
				economic = v;
			}
		}

		System.out.print(flightDetails + "" + first + bussiness + economic);
		jlabel7 = new JLabel("	First Class");
		jlabel8 = new JLabel("	Price: " + first.get(asrs.model.FlightDetail.PRICE));
		jlabel9 = new JLabel("	Seat Left: " + first.get(asrs.model.FlightDetail.SEATLEFT));
		jlabel10 = new JLabel("	Total Seats: " + first.get(asrs.model.FlightDetail.TOTALSEAT));

		jlabel11 = new JLabel("	Bussiness Class");
		jlabel12 = new JLabel("	Price: " + bussiness.get(asrs.model.FlightDetail.PRICE));
		jlabel13 = new JLabel("	Seat Left: " + bussiness.get(asrs.model.FlightDetail.SEATLEFT));
		jlabel14 = new JLabel("	Total Seats: " + bussiness.get(asrs.model.FlightDetail.TOTALSEAT));

		jlabel15 = new JLabel("	Economic Class");
		jlabel16 = new JLabel("	Price: " + economic.get(asrs.model.FlightDetail.PRICE));
		jlabel17 = new JLabel("	Seat Left: " + economic.get(asrs.model.FlightDetail.SEATLEFT));
		jlabel18 = new JLabel("	Total Seats: " + economic.get(asrs.model.FlightDetail.TOTALSEAT));

		jlabel7.setFont(new Font(jlabel7.getFont().getName(), Font.PLAIN, 18));
		jlabel11.setFont(new Font(jlabel7.getFont().getName(), Font.PLAIN, 18));
		jlabel15.setFont(new Font(jlabel7.getFont().getName(), Font.PLAIN, 18));

		int seatLeft = Integer.parseInt((String) first.get(asrs.model.FlightDetail.SEATLEFT))
				+ Integer.parseInt((String) bussiness.get(asrs.model.FlightDetail.SEATLEFT))
				+ Integer.parseInt((String) economic.get(asrs.model.FlightDetail.SEATLEFT));

		if (seatLeft > 0) {
			jButton1 = new JButton("Go Booking");
			jButton1.setName(ASRS.STRING_RESERVATION);
		} else {
			jButton1 = new JButton("Go Waiting");
			jButton1.setName(ASRS.STRING_WALTING);
		}

		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.insets = new Insets(10, 0, 2, 10);
		int x, y;

		addGB(jlabel2, x = 0, y = 1);
		addGB(jlabel3, x = 0, y = 3);
		addGB(jlabel4, x = 1, y = 3);
		addGB(jlabel5, x = 0, y = 5);
		addGB(jlabel6, x = 1, y = 5);
		addGB(jlabel7, x = 0, y = 7);
		addGB(jlabel8, x = 0, y = 8);
		addGB(jlabel9, x = 1, y = 8);
		addGB(jlabel10, x = 2, y = 8);
		addGB(jlabel11, x = 0, y = 10);
		addGB(jlabel12, x = 0, y = 11);
		addGB(jlabel13, x = 1, y = 11);
		addGB(jlabel14, x = 2, y = 11);
		addGB(jlabel15, x = 0, y = 13);
		addGB(jlabel16, x = 0, y = 14);
		addGB(jlabel17, x = 1, y = 14);
		addGB(jlabel18, x = 2, y = 14);

		addGB(jButton1, x = 2, y = 16);

		add(panel);

		jButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				goBooking();
			}

		});
	}

	GridBagConstraints constraints = new GridBagConstraints();

	private void addGB(Component component, int x, int y) {
		constraints.gridx = x;
		constraints.gridy = y;
		panel.add(component, constraints);
	}

	private JPanel panel;

	private JLabel jlabel2;
	private JLabel jlabel3;
	private JLabel jlabel4;
	private JLabel jlabel5;
	private JLabel jlabel6;
	private JLabel jlabel7;
	private JLabel jlabel8;
	private JLabel jlabel9;
	private JLabel jlabel10;
	private JLabel jlabel11;
	private JLabel jlabel12;
	private JLabel jlabel13;
	private JLabel jlabel14;
	private JLabel jlabel15;
	private JLabel jlabel16;
	private JLabel jlabel17;
	private JLabel jlabel18;

	private JButton jButton1;

}
