package gui.airline;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.ASRS;

@SuppressWarnings("serial")
public class ChangeBookingStatus extends JPanel {

	private JFrame frame;

	private JPanel inputPanel;
	private JPanel showPanel;

	private JLabel lbookingNumer;
	private JLabel lphone;

	private JComboBox<String> flightNameList;
	private JComboBox<String> bookingNumberList;

	private JButton search;

	private JLabel bookingNumer = new JLabel();
	private JLabel firstName = new JLabel();
	private JLabel phone2 = new JLabel();
	private JLabel email = new JLabel();
	private JLabel flightName = new JLabel();
	private JLabel ticketName = new JLabel();
	private JLabel classType = new JLabel();
	private JLabel seatNumber = new JLabel();
	private JLabel status1 = new JLabel();

	private JButton button = new JButton("Finish Payment");
	private JButton button2 = new JButton("Ticket Take up");
	private JButton button3 = new JButton("Cancel Booking");

	private void toShow(Vector booking, Vector ticket, Vector flight) {
		bookingNumer.setText("	Booking Number:	" + booking.get(model.Booking.BOOKINGNUMBER));
		firstName.setText(
				"	Name:	" + booking.get(model.Booking.FIRSTNAME) + " " + booking.get(model.Booking.LASTNAME));
		phone2.setText("	Phone:	" + booking.get(model.Booking.PHONE));
		email.setText("	Email:	" + booking.get(model.Booking.EMAIL));
		if (ticket != null) {
			flightName.setText("	Flight Name:	" + flight.get(model.Flight.NAME));
			ticketName.setText("	Ticket Number:	" + ticket.get(model.Ticket.TICKETNUMBER));
			String type;
			if (ticket.get(model.Ticket.CLASSTYPE).equals(ASRS.ECONOMIC_CLASS)) {
				type = ASRS.STRING_ECONOMIC_CLASS;
			} else if (ticket.get(model.Ticket.CLASSTYPE).equals(ASRS.BUSSINESS_CLASS)) {
				type = ASRS.STRING_BUSSINESS_CLASS;
			} else {
				type = ASRS.STRING_FIRST_CLASS;
			}
			classType.setText("	Class:	" + type);
			seatNumber.setText("	Seat Number:	" + ticket.get(model.Ticket.SEATNUMBER));
		}
		String status = "";
		button3.setVisible(true);

		switch (Integer.parseInt((String) booking.get(model.Booking.STATUS))) {
		case ASRS.RESERVATION:
			status = ASRS.STRING_RESERVATION;
			button.setVisible(true);
			break;
		case ASRS.WALTING:
			status = ASRS.STRING_WALTING;
			break;
		case ASRS.HOLDING:
			status = ASRS.STRING_HOLDING;
			button2.setVisible(true);
			break;
		case ASRS.CONFIRMATION:
			status = ASRS.STRING_CONFIRMATION;
			break;
		case ASRS.CANCELLATION:
			status = ASRS.STRING_CANCELLATION;
			button3.setVisible(false);
			break;
		}

		status1.setText(" Status: " + status);

	}

	public ChangeBookingStatus(JFrame frame, Object key) {
		this.frame = frame;
		setSize(600, 400);
		setLayout(new BorderLayout());

		Vector airplanesList = controller.AllAirplanes.getInstance().getAirplane(model.Airplane.AIRLINE, key);
		Vector flightNames = new Vector();
		flightNames.add("");
		Vector flightId = new Vector();
		flightId.add("");
		for (Iterator it = airplanesList.iterator(); it.hasNext();) {
			Vector v = (Vector) it.next();
			Object airplane = v.get(model.Airplane.ID);
			Vector flightsList = controller.AllFlights.getInstance().getFlight(model.Flight.AIRPLANE, airplane);
			for (Iterator it2 = flightsList.iterator(); it2.hasNext();) {
				Vector v2 = (Vector) it2.next();
				flightNames.add(v2.get(model.Flight.NAME));
				flightId.add(v2.get(model.Flight.ID));
			}
		}

		inputPanel = new JPanel(new GridBagLayout());
		showPanel = new JPanel(new GridLayout(6, 2));
		lbookingNumer = new JLabel("	Flight Name");
		lphone = new JLabel("	Booking Number");
		bookingNumberList = new JComboBox<String>();
		flightNameList = new JComboBox<String>(flightNames);
		search = new JButton("Enter");

		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.fill = GridBagConstraints.BOTH;
		int x, y;
		addGB(lbookingNumer, x = 0, y = 0);
		addGB(lphone, x = 0, y = 1);
		constraints.weightx = 4;
		addGB(flightNameList, x = 1, y = 0);
		addGB(bookingNumberList, x = 1, y = 1);
		constraints.weightx = 1;
		addGB(search, x = 2, y = 1);

		add(inputPanel, BorderLayout.NORTH);
		add(showPanel, BorderLayout.CENTER);

		showPanel.add(bookingNumer);
		showPanel.add(ticketName);
		showPanel.add(firstName);
		showPanel.add(flightName);
		showPanel.add(phone2);
		showPanel.add(classType);
		showPanel.add(email);
		showPanel.add(seatNumber);
		showPanel.add(status1);
		showPanel.add(button);
		showPanel.add(button2);
		showPanel.add(button3);
		button.setVisible(false);
		button2.setVisible(false);
		button3.setVisible(false);
		flightNameList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int flight = flightNameList.getSelectedIndex();
				bookingNumberList.removeAllItems();
				bookingNumberList.addItem("");
				System.out.println(flightId.get(flight));
				Vector vector = controller.AllBookings.getInstance().get(model.Booking.FLIGHTDETAIL,
						flightId.get(flight));
				Vector bookingsList = new Vector();
				for (Iterator it = vector.iterator(); it.hasNext();) {
					Vector v = (Vector) it.next();
					bookingNumberList.addItem((String) v.get(model.Booking.BOOKINGNUMBER));
				}
			}
		});

		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				goSearching();
			}

		});

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				booking.set(model.Booking.STATUS, ASRS.HOLDING);
				controller.AllBookings.getInstance().change(booking);
				bookingNumer.setText("");
				firstName.setText("");
				phone2.setText("");
				email.setText("");
				flightName.setText("");
				ticketName.setText("");
				classType.setText("");
				seatNumber.setText("");
				status1.setText("");
				button.setVisible(false);
				button2.setVisible(false);
				button3.setVisible(false);
				JOptionPane.showMessageDialog(ChangeBookingStatus.this, "Successful to Do Payment",
						"Change Booking Status", JOptionPane.INFORMATION_MESSAGE);

			}
		});
		
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				booking.set(model.Booking.STATUS, ASRS.CONFIRMATION);
				controller.AllBookings.getInstance().change(booking);
				bookingTicket.set(model.BookingTicket.ISTAKEN, "true");
				controller.GetBookingTicket.getInstance().change(bookingTicket);
				bookingNumer.setText("");
				firstName.setText("");
				phone2.setText("");
				email.setText("");
				flightName.setText("");
				ticketName.setText("");
				classType.setText("");
				seatNumber.setText("");
				status1.setText("");
				button.setVisible(false);
				button2.setVisible(false);
				button3.setVisible(false);
				JOptionPane.showMessageDialog(ChangeBookingStatus.this, "Successful to Pick Up Ticket",
						"Change Booking Status", JOptionPane.INFORMATION_MESSAGE);

			}
		});

		button3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				booking.set(model.Booking.STATUS, ASRS.CANCELLATION);
				controller.AllBookings.getInstance().change(booking);
				controller.AllTickets.getInstance().remove(ticket.get(0));
				controller.GetBookingTicket.getInstance().remove(bookingTicket.get(0));
				Vector vector = controller.AllFlights.getInstance().getFlightDetail(model.FlightDetail.FLIGHTLID,
						booking.get(model.Booking.FLIGHTDETAIL));
				for (Iterator it = vector.iterator(); it.hasNext();) {
					Vector v = (Vector) it.next();
					if (v.get(model.FlightDetail.CLASSTYPE).equals(ticket.get(model.Ticket.CLASSTYPE))) {
						int number = Integer.parseInt((String) v.get(model.FlightDetail.SEATLEFT)) + 1;
						v.set((model.FlightDetail.SEATLEFT), number + "");
						controller.AllFlights.getInstance().changeFlightDetail(v);
					}
					System.out.println(v);
				}
				bookingNumer.setText("");
				firstName.setText("");
				phone2.setText("");
				email.setText("");
				flightName.setText("");
				ticketName.setText("");
				classType.setText("");
				seatNumber.setText("");
				status1.setText("");
				button.setVisible(false);
				button2.setVisible(false);
				button3.setVisible(false);
				JOptionPane.showMessageDialog(ChangeBookingStatus.this, "Successful to Cancell Booking",
						"Change Booking Status", JOptionPane.INFORMATION_MESSAGE);

			}

		});
	}

	GridBagConstraints constraints = new GridBagConstraints();

	private void addGB(Component component, int x, int y) {
		constraints.gridx = x;
		constraints.gridy = y;
		inputPanel.add(component, constraints);
	}

	private Vector booking;
	private Vector ticket;
	private Vector bookingTicket;

	private void goSearching() {
		Vector vector = controller.AllBookings.getInstance().get(model.Booking.BOOKINGNUMBER,
				bookingNumberList.getSelectedItem());
		if (vector.size() != 0) {
			Vector v = (Vector) vector.get(0);
			booking = (Vector) vector.get(0);
			try {
				Vector v2 = controller.GetBookingTicket.getInstance().get(model.BookingTicket.BOOKINGNUMBER,
						bookingNumberList.getSelectedItem());
				bookingTicket = (Vector) v2.get(0);
				Vector v3 = controller.AllTickets.getInstance().get(model.Ticket.TICKETNUMBER,
						((Vector) v2.get(0)).get(model.Ticket.TICKETNUMBER));
				ticket = (Vector) v3.get(0);
				Vector v4 = controller.AllFlights.getInstance().getFlight(v.get(model.Booking.FLIGHTDETAIL));
				System.out.println(vector + "" + v2 + "" + v3 + "" + v4);
				toShow(v, (Vector) v3.get(0), v4);
			} catch (Exception ex) {
				toShow(booking, null, null);
			}
		} else {

		}
	}

}
