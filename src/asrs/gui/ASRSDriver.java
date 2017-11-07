package asrs.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import asrs.controller.ASRS;
import asrs.db.LoadProperties;
import asrs.gui.customer.CustomerFrame;

@SuppressWarnings("serial")
public class ASRSDriver extends JFrame {

	JPanel choosePanel;
	JButton customer;
	JButton travelAgent;
	JButton airline;

	public ASRSDriver(JFrame frame) {
		this();
		frame.setEnabled(false);
		WindowListener[] wl = (WindowListener[])this.getListeners(WindowListener.class);
        for (int i = 0; i < wl.length; i++) {
            this.removeWindowListener(wl[i]);
        }
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent evt) {
				frame.setEnabled(true);
				exit();
			}
		});
	}

	public ASRSDriver() {
		setTitle("Welcome to Airline Seat Reservation System");

		choosePanel = new JPanel(new GridLayout());

		customer = new JButton(ASRS.STRING_CUSTOMER);
		travelAgent = new JButton(ASRS.STRING_TRAVELAGENT);
		airline = new JButton(ASRS.STRING_AIRLINE);

		choosePanel.add(customer);
		// choosePanel.add(travelAgent);
		choosePanel.add(airline);
		add(choosePanel);

		setSize(600, 200);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		setVisible(true);

		customer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				customer();
			}

		});

		travelAgent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				travelAgent();
			}

		});

		airline.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				airline();
			}

		});

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent evt) {				
				exit();
				System.exit(0);
			}
		});
	}

	private void travelAgent() {
		LoginDialog login = new LoginDialog(this, ASRS.TRAVELAGENT);
		if (login.isSucceeded()) {

		}
	}

	private void airline() {
		LoginDialog login = new LoginDialog(this, ASRS.AIRLINE);
		if (login.isSucceeded()) {
			setEnabled(false);
			new asrs.gui.airline.AirlineFrame(this, login.getUsername());
		}
	}

	private void customer() {
		setEnabled(false);
		new CustomerFrame(this);
	}

	private void exit() {
		try {
			asrs.db.LoadProperties.getInstance().getConnection().close();
		} catch (Exception ex) {

		}
		setVisible(false);
		dispose();
	}
	
	public static void main(String [] args) {
		new ASRSDriver();
	}
}
