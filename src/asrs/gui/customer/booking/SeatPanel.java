package asrs.gui.customer.booking;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import asrs.controller.ASRS;

@SuppressWarnings("serial")
public class SeatPanel extends JPanel {

	private JPanel airplanePanel;
	private int numOfRows = 20;
	private int numOfColumns = 4;
	private String seatNumber;

	private ArrayList<JButton> buttons = new ArrayList<JButton>();

	public SeatPanel(JFrame frame, AirplanePanel airplanePanel, int numOfRows, int numOfColumns, Vector flight) {

		this.airplanePanel = airplanePanel;
		this.numOfRows = numOfRows;
		this.numOfColumns = numOfColumns;

		ArrayList<String> holdings = new ArrayList<String>();
		ArrayList<String> reservation = new ArrayList<String>();
		ArrayList<String> confirm = new ArrayList<String>();
		Object id = flight.get(asrs.model.Flight.ID);
		Vector v = asrs.controller.AllBookings.getInstance().get(asrs.model.Ticket.FLIGHTDETAIL, id);
		if (v != null) {
			for (Iterator it = v.iterator(); it.hasNext();) {
				Vector b = (Vector) it.next();
				Object book = b.get(asrs.model.Booking.BOOKINGNUMBER);
				Vector bts = asrs.controller.GetBookingTicket.getInstance().get(asrs.model.BookingTicket.BOOKINGNUMBER,
						book);
				if (bts.size() > 0) {
					Vector bt = (Vector) bts.get(0);
					Object key = bt.get(asrs.model.BookingTicket.TICKETNUMBER);
					Vector ts = asrs.controller.AllTickets.getInstance().get(asrs.model.Ticket.TICKETNUMBER, key);
					Vector t = (Vector) ts.get(0);
					if (b.get(asrs.model.Booking.STATUS).equals(ASRS.RESERVATION + "")) {
						reservation.add((String) t.get(asrs.model.Ticket.SEATNUMBER));
					} else if (b.get(asrs.model.Booking.STATUS).equals(ASRS.HOLDING + "")) {
						holdings.add((String) t.get(asrs.model.Ticket.SEATNUMBER));
					} else if (b.get(asrs.model.Booking.STATUS).equals(ASRS.CONFIRMATION + "")) {
						confirm.add((String) t.get(asrs.model.Ticket.SEATNUMBER));
					}
				}
			}
		}

		setLayout(new GridLayout(numOfColumns + 1, numOfRows + 1));

		int buttonWidth = airplanePanel.getWidth() / numOfRows;
		int buttonHeight = airplanePanel.getHeight() / numOfColumns;

		JButton button;

		for (int i = numOfColumns; i >= 1; i--) {
			String letter = String.valueOf((char) (i + 64));
			add(new JLabel(letter));
			for (int j = 1; j <= numOfRows; j++) {
				button = new JButton();
				String text = letter + Integer.toString(j);
				button.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
				button.setText(text);
				button.setBackground(Color.WHITE);
				button.setOpaque(true);
				// button.setBorderPainted(false);
				for (int k = 0; k < holdings.size(); k++) {
					if (holdings.get(k).equals(text)) {
						button.setBackground(Color.GREEN);
					}
				}
				for (int k = 0; k < reservation.size(); k++) {
					if (reservation.get(k).equals(text)) {
						button.setBackground(Color.CYAN);
					}
				}
				for (int k = 0; k < confirm.size(); k++) {
					if (confirm.get(k).equals(text)) {
						button.setBackground(Color.GRAY);
					}
				}
				if (button.getBackground().equals(Color.WHITE)) {
					button.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							airplanePanel.setSeatNumber(((JButton) e.getSource()).getText());
						}

					});
					;
				}
				buttons.add(button);
				add(button);
			}

		}
		for (int j = 0; j <= numOfRows; j++) {
			if (j == 0) {
				add(new JLabel(""));
			} else {
				JLabel label = new JLabel(j + "", SwingConstants.CENTER);
				label.setBackground(airplanePanel.getColor(j));
				label.setOpaque(true);
				add(label);
			}
		}
	}
}
