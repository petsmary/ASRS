package asrs.gui.customer.booking;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import asrs.controller.ASRS;
import asrs.gui.LoginDialog;

@SuppressWarnings("serial")
public class AirplanePanel extends JPanel {

	private SeatPanel seatPanel;
	private JPanel bookingPanel;
	private JPanel show;
	private JPanel intro;
	private BookingFrame frame;

	private void goReservation(String firstName, String lastName, String email, String phone, Vector flight) {
		if (firstName.equals("") || lastName.equals("") || phone.equals("") || email.equals("")
				|| seatNumber.getText().equals("")) {
			JOptionPane.showMessageDialog(frame, "Please input all the information", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int month = Calendar.getInstance().get(Calendar.MONTH);
		int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		String bookingNumber = year + "" + month + "" + day + asrs.controller.AllBookings.getInstance().getTable().size();
		String ticketNumber = ((String) flight.get(asrs.model.Flight.NAME)).substring(2, 5) + flight.get(asrs.model.Flight.ID)
				+ getClassType2() + ((int) seatNumber.getText().charAt(0) - 64) + seatNumber.getText().substring(1);
		Vector v = new Vector();
		v.add("");
		v.add(bookingNumber);
		v.add(firstName);
		v.add(lastName);
		v.add(email);
		v.add(phone);
		v.add(flight.get(asrs.model.Flight.ID));
		v.add(ASRS.RESERVATION);
		if (asrs.controller.AllBookings.getInstance().put(v)) {
			Vector v2 = new Vector();
			v2.add("");
			v2.add(ticketNumber);
			v2.add(firstName);
			v2.add(lastName);
			v2.add(email);
			v2.add(phone);
			v2.add(flight.get(asrs.model.Flight.ID));
			v2.add(seatNumber.getText());
			v2.add(getClassType2());
			if (asrs.controller.AllTickets.getInstance().put(v2)) {
				Vector v3 = new Vector();
				v3.add("");
				v3.add(ticketNumber);
				v3.add(bookingNumber);
				v3.add("false");
				if (asrs.controller.GetBookingTicket.getInstance().put(v3)) {
					Vector vector = asrs.controller.AllFlights.getInstance().getFlightDetail(asrs.model.FlightDetail.FLIGHTLID,
							flight.get(asrs.model.Flight.ID));
					for (Iterator it = vector.iterator(); it.hasNext();) {
						Vector v4 = (Vector) it.next();
						if (getClassType2().equals(v4.get(asrs.model.FlightDetail.CLASSTYPE))) {
							int seatl = Integer.parseInt((String) v4.get(asrs.model.FlightDetail.SEATLEFT)) - 1;
							v4.set(asrs.model.FlightDetail.SEATLEFT, seatl + "");
							asrs.controller.AllFlights.getInstance().changeFlightDetail(v4);
						}

					}
					JOptionPane.showMessageDialog(frame,
							"Successful to Reservation, " + lastName
									+ "\nPlease Remember your booking Number to check your booking :" + bookingNumber,
							"Reservation", JOptionPane.INFORMATION_MESSAGE);					
				}
			}
		}
	}

	private void goWaiting(String firstName, String lastName, String email, String phone, Vector flight) {
		if (firstName.equals("") || lastName.equals("") || phone.equals("") || email.equals("")) {
			JOptionPane.showMessageDialog(frame, "Please input all the message", "Error", JOptionPane.ERROR_MESSAGE);
		}
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int month = Calendar.getInstance().get(Calendar.MONTH);
		int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		String bookingNumber = year + "" + month + "" + day + asrs.controller.AllBookings.getInstance().getTable().size();

		Vector v = new Vector();
		v.add(-1);
		v.add(bookingNumber);
		v.add(firstName);
		v.add(lastName);
		v.add(email);
		v.add(phone);
		v.add(flight.get(asrs.model.Flight.ID));
		v.add(ASRS.WALTING);
		if (asrs.controller.AllBookings.getInstance().put(v)) {

			JOptionPane.showMessageDialog(frame, "Successful to add to waiting list " + lastName, "Login",
					JOptionPane.INFORMATION_MESSAGE);
		}

	}

	public AirplanePanel(BookingFrame frame, Vector flight, String type) {
		setSize(800, 400);

		this.frame = frame;
		JLabel jlabel1 = new JLabel("	Customer Detail: ");
		JLabel jlabel2 = new JLabel("	First Name: ");
		JTextField firstName = new JTextField(10);
		JLabel jlabel3 = new JLabel("	Last Name: ");
		JTextField lastName = new JTextField(10);
		JLabel jlabel4 = new JLabel("	Phone Number: ");
		JTextField phone = new JTextField(10);
		JLabel jlabel5 = new JLabel("	Email Address: ");
		JTextField email = new JTextField(10);
		JLabel jlabel6 = new JLabel("	Seat Number: ");

		JButton book = new JButton("Reservation");

		bookingPanel = new JPanel(new GridBagLayout());
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.insets = new Insets(10, 0, 2, 10);
		int x, y;

		addGB(jlabel1, x = 0, y = 0);
		addGB(jlabel2, x = 0, y = 1);
		addGB(jlabel3, x = 2, y = 1);
		addGB(jlabel4, x = 0, y = 2);
		addGB(jlabel5, x = 2, y = 2);
		addGB(jlabel6, x = 4, y = 2);
		addGB(classType, x = 5, y = 1);
		addGB(firstName, x = 1, y = 1);
		addGB(lastName, x = 3, y = 1);
		addGB(phone, x = 1, y = 2);
		addGB(email, x = 3, y = 2);
		addGB(seatNumber, x = 5, y = 2);
		addGB(book, x = 6, y = 2);
		seatNumber.setEnabled(false);
		classType.setEnabled(false);

		if (type.equals(ASRS.STRING_RESERVATION)) {
			String airplane = (String) flight.get(asrs.model.Flight.AIRPLANE);

			Vector vector = asrs.controller.AllAirplanes.getInstance().getAirplane(airplane);
			String firstRows = (String) vector.get(asrs.model.Airplane.FIRST);
			String bussinessRows = (String) vector.get(asrs.model.Airplane.BUSSINESS);
			String economicRows = (String) vector.get(asrs.model.Airplane.ECONOMIC);
			String columnRows = (String) vector.get(asrs.model.Airplane.COLUMN);
			String rows = (String) vector.get(asrs.model.Airplane.ROW);

			this.first = Integer.parseInt(firstRows);
			this.bussincess = Integer.parseInt(bussinessRows) + first;

			seatPanel = new SeatPanel(frame, this, Integer.parseInt(rows), Integer.parseInt(columnRows), flight);
			add(seatPanel);

			int buttonWidth = getWidth() / Integer.parseInt(rows);
			int buttonHeight = getHeight() / Integer.parseInt(columnRows);

			intro = new JPanel();
			JLabel firstLabel = new JLabel(ASRS.STRING_FIRST_CLASS);
			intro.add(firstLabel);
			JLabel bussincessLabel = new JLabel(ASRS.STRING_BUSSINESS_CLASS);
			intro.add(bussincessLabel);
			JLabel economicLabel = new JLabel(ASRS.STRING_ECONOMIC_CLASS);
			intro.add(economicLabel);
			firstLabel.setOpaque(true);
			bussincessLabel.setOpaque(true);
			economicLabel.setOpaque(true);
			firstLabel.setBackground(Color.ORANGE);
			bussincessLabel.setBackground(Color.MAGENTA);
			economicLabel.setBackground(Color.RED);
			JLabel jButton1 = new JLabel(ASRS.STRING_RESERVATION);
			JLabel jButton2 = new JLabel(ASRS.STRING_HOLDING);
			JLabel jButton3 = new JLabel(ASRS.STRING_CONFIRMATION);
			jButton1.setOpaque(true);
			jButton2.setOpaque(true);
			jButton3.setOpaque(true);
			jButton1.setBackground(Color.CYAN);
			jButton2.setBackground(Color.GREEN);
			jButton3.setBackground(Color.GRAY);
			intro.add(jButton1);
			intro.add(jButton2);
			intro.add(jButton3);
			add(intro);
		} else {
			seatNumber.setText("----");
		}
		add(bookingPanel);

		book.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (type.equals(ASRS.STRING_RESERVATION)) {
					goReservation(firstName.getText(), lastName.getText(), email.getText(), phone.getText(), flight);
				} else {
					goWaiting(firstName.getText(), lastName.getText(), email.getText(), phone.getText(), flight);
				}
				frame.exit();
			}

		});
	}

	GridBagConstraints constraints = new GridBagConstraints();

	private void addGB(Component component, int x, int y) {
		constraints.gridx = x;
		constraints.gridy = y;
		bookingPanel.add(component, constraints);
	}

	JTextField seatNumber = new JTextField(5);
	JTextField classType = new JTextField(10);

	public void setSeatNumber(String seatNumber) {
		this.seatNumber.setText(seatNumber);
		this.classType.setText(getClassType());
	}

	public Color getColor(int number) {
		if (number <= first) {
			return Color.ORANGE;
		} else if (number <= bussincess) {
			return Color.MAGENTA;
		} else {
			return Color.RED;
		}
	}

	private int first;
	private int bussincess;

	private String getClassType() {
		int number = Integer.parseInt(seatNumber.getText().substring(1));
		if (number <= first) {
			return ASRS.STRING_FIRST_CLASS;
		} else if (number <= bussincess) {
			return ASRS.STRING_BUSSINESS_CLASS;
		} else {
			return ASRS.STRING_ECONOMIC_CLASS;
		}
	}

	private String getClassType2() {
		int number = Integer.parseInt(seatNumber.getText().substring(1));
		if (number <= first) {
			return ASRS.FIRST_CLASS;
		} else if (number <= bussincess) {
			return ASRS.BUSSINESS_CLASS;
		} else {
			return ASRS.ECONOMIC_CLASS;
		}
	}
}
