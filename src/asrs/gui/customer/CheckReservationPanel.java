package asrs.gui.customer;

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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import asrs.controller.ASRS;

@SuppressWarnings("serial")
public class CheckReservationPanel extends JPanel {

	private JFrame frame;

	private JPanel inputPanel;
	private JPanel showPanel;

	private JLabel lbookingNumer;
	private JLabel lphone;

	private JTextField bookingNumber;
	private JTextField phone;

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
	private JButton button = new JButton("Cancel Booking");

	private void toShow(Vector booking, Vector ticket, Vector flight) {
		bookingNumer.setText("	Booking Number:	" + booking.get(asrs.model.Booking.BOOKINGNUMBER));
		firstName.setText(
				"	Name:	" + booking.get(asrs.model.Booking.FIRSTNAME) + " " + booking.get(asrs.model.Booking.LASTNAME));
		phone2.setText("	Phone:	" + booking.get(asrs.model.Booking.PHONE));
		email.setText("	Email:	" + booking.get(asrs.model.Booking.EMAIL));
		if (ticket != null) {
			flightName.setText("	Flight Name:	" + flight.get(asrs.model.Flight.NAME));
			ticketName.setText("	Ticket Number:	" + ticket.get(asrs.model.Ticket.TICKETNUMBER));
			String type;
			if (ticket.get(asrs.model.Ticket.CLASSTYPE).equals(ASRS.ECONOMIC_CLASS)) {
				type = ASRS.STRING_ECONOMIC_CLASS;
			} else if (ticket.get(asrs.model.Ticket.CLASSTYPE).equals(ASRS.BUSSINESS_CLASS)) {
				type = ASRS.STRING_BUSSINESS_CLASS;
			} else {
				type = ASRS.STRING_FIRST_CLASS;
			}
			classType.setText("	Class:	" + type);
			seatNumber.setText("	Seat Number:	" + ticket.get(asrs.model.Ticket.SEATNUMBER));
		}
		String status = "";
		button.setText("Cancel Booking");
		button.setVisible(true);

		switch (Integer.parseInt((String) booking.get(asrs.model.Booking.STATUS))) {
		case ASRS.RESERVATION:
			status = ASRS.STRING_RESERVATION;
			break;
		case ASRS.WALTING:
			status = ASRS.STRING_WALTING;
			break;
		case ASRS.HOLDING:
			status = ASRS.STRING_HOLDING;
			break;
		case ASRS.CONFIRMATION:
			status = ASRS.STRING_CONFIRMATION;
			break;
		case ASRS.CANCELLATION:
			status = ASRS.STRING_CANCELLATION;
			button.setVisible(false);
			break;
		}

		status1.setText(" Status: " + status);

	}

	public CheckReservationPanel(JFrame frame) {
		this.frame = frame;

		setLayout(new BorderLayout());

		inputPanel = new JPanel(new GridBagLayout());
		showPanel = new JPanel(new GridLayout(5, 2));
		lbookingNumer = new JLabel("	Booking Number");
		lphone = new JLabel("	Phone Number");
		bookingNumber = new JTextField();
		phone = new JTextField();
		search = new JButton("Enter");

		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.fill = GridBagConstraints.BOTH;
		int x, y;
		addGB(lbookingNumer, x = 0, y = 0);
		addGB(lphone, x = 0, y = 1);
		constraints.weightx = 4;
		addGB(bookingNumber, x = 1, y = 0);
		addGB(phone, x = 1, y = 1);
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
		button.setVisible(false);
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
				// TODO Auto-generated method stub
				booking.set(asrs.model.Booking.STATUS, ASRS.CANCELLATION);
				asrs.controller.AllBookings.getInstance().change(booking);
				asrs.controller.AllTickets.getInstance().remove(ticket.get(0));
				asrs.controller.GetBookingTicket.getInstance().remove(bookingTicket.get(0));
				Vector vector = asrs.controller.AllFlights.getInstance().getFlightDetail(asrs.model.FlightDetail.FLIGHTLID,
						booking.get(asrs.model.Booking.FLIGHTDETAIL));
				for (Iterator it = vector.iterator(); it.hasNext();) {
					Vector v = (Vector) it.next();
					if (v.get(asrs.model.FlightDetail.CLASSTYPE).equals(ticket.get(asrs.model.Ticket.CLASSTYPE))) {
						int number = Integer.parseInt((String) v.get(asrs.model.FlightDetail.SEATLEFT)) + 1;
						v.set((asrs.model.FlightDetail.SEATLEFT), number + "");
						asrs.controller.AllFlights.getInstance().changeFlightDetail(v);
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
				bookingNumber.setText("");
				phone.setText("");
				JOptionPane.showMessageDialog(CheckReservationPanel.this, "Successful to Cancel Ticket", "Cancellation",
						JOptionPane.INFORMATION_MESSAGE);

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
		Vector vector = asrs.controller.AllBookings.getInstance().get(asrs.model.Booking.BOOKINGNUMBER, bookingNumber.getText());
		if (vector.size() != 0) {
			Vector v = (Vector) vector.get(0);
			booking = (Vector) vector.get(0);
			if (v.get(asrs.model.Booking.PHONE).equals(phone.getText())) {
				try {
					Vector v2 = asrs.controller.GetBookingTicket.getInstance().get(asrs.model.BookingTicket.BOOKINGNUMBER,
							bookingNumber.getText());
					bookingTicket = (Vector) v2.get(0);
					Vector v3 = asrs.controller.AllTickets.getInstance().get(asrs.model.Ticket.TICKETNUMBER,
							((Vector) v2.get(0)).get(asrs.model.Ticket.TICKETNUMBER));
					ticket = (Vector) v3.get(0);
					Vector v4 = asrs.controller.AllFlights.getInstance().getFlight(v.get(asrs.model.Booking.FLIGHTDETAIL));
					System.out.println(vector + "" + v2 + "" + v3 + "" + v4);
					toShow(v, (Vector) v3.get(0), v4);
				} catch (Exception ex) {
					toShow(booking, null, null);
				}
			} else {
				JOptionPane.showMessageDialog(CheckReservationPanel.this, "Incorrect Phone Number", "Check Reservation",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(CheckReservationPanel.this, "Incorrect Booking Number", "Check Reservation",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
