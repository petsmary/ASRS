package asrs.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import asrs.controller.ASRS;

@SuppressWarnings("serial")
public class LoginDialog extends JDialog {

	private JTextField tfUsername;
	private JPasswordField pfPassword;
	private JLabel lbUsername;
	private JLabel lbPassword;
	private JButton btnLogin;
	private JButton btnCancel;
	private boolean succeeded;

	public LoginDialog(Frame parent, int index) {
		super(parent, "Login", true);

		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints cs = new GridBagConstraints();

		cs.fill = GridBagConstraints.HORIZONTAL;

		lbUsername = new JLabel("Username: ");
		cs.gridx = 0;
		cs.gridy = 0;
		cs.gridwidth = 1;
		panel.add(lbUsername, cs);

		tfUsername = new JTextField(20);
		cs.gridx = 1;
		cs.gridy = 0;
		cs.gridwidth = 2;
		panel.add(tfUsername, cs);

		lbPassword = new JLabel("Password: ");
		cs.gridx = 0;
		cs.gridy = 1;
		cs.gridwidth = 1;
		panel.add(lbPassword, cs);

		pfPassword = new JPasswordField(20);
		cs.gridx = 1;
		cs.gridy = 1;
		cs.gridwidth = 2;
		panel.add(pfPassword, cs);
		panel.setBorder(new LineBorder(Color.GRAY));

		btnLogin = new JButton("Login");

		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (index) {
				case ASRS.TRAVELAGENT:
					break;

				case ASRS.AIRLINE:
					Vector v = asrs.controller.AllAirplanes.getInstance().getAirline(asrs.model.Airline.LOGINNAME, getUsername());
					Vector vector = (Vector) v.get(0);
					if (vector != null) {
						if (vector.get(asrs.model.Airline.PASSWORD).equals(getPassword())) {
							correct();
						} else {
							incorrect();
						}
					} else {
						incorrect();
					}
					break;
				}
			}
		});
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		JPanel bp = new JPanel();
		bp.add(btnLogin);
		bp.add(btnCancel);

		getContentPane().add(panel, BorderLayout.CENTER);
		getContentPane().add(bp, BorderLayout.PAGE_END);

		pack();
		setResizable(false);
		setLocationRelativeTo(parent);
		setVisible(true);
	}

	public String getUsername() {
		return tfUsername.getText().trim();
	}

	public String getPassword() {
		return new String(pfPassword.getPassword());
	}

	public boolean isSucceeded() {
		return succeeded;
	}

	private void correct() {
		JOptionPane.showMessageDialog(LoginDialog.this, "Hi " + getUsername() + "! You have successfully logged in.",
				"Login", JOptionPane.INFORMATION_MESSAGE);
		succeeded = true;
		dispose();
	}

	private void incorrect() {
		JOptionPane.showMessageDialog(LoginDialog.this, "Invalid username or password", "Login",
				JOptionPane.ERROR_MESSAGE);
		// reset username and password
		tfUsername.setText("");
		pfPassword.setText("");
		succeeded = false;

	}
}
