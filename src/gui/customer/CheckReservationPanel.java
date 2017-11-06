package gui.customer;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class CheckReservationPanel extends JPanel {

	private JFrame frame;

	private JPanel inputPanel;
	
	private JLabel lbookingNumer;
	private JLabel lphone;

	private JTextField bookingNumber;
	private JTextField phone;

	private JButton search;
	
	public CheckReservationPanel(JFrame frame) {
		this.frame = frame;

		setLayout(new BorderLayout());;
		
		inputPanel = new JPanel(new GridBagLayout());
		lbookingNumer = new JLabel("	Booking Number");
		lphone = new JLabel("	Phone Number");
		bookingNumber = new JTextField ();
		phone = new JTextField ();
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
	}
	
	GridBagConstraints constraints = new GridBagConstraints();

	private void addGB(Component component, int x, int y) {
		constraints.gridx = x;
		constraints.gridy = y;
		inputPanel.add(component, constraints);
	}
}
