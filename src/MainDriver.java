import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import asrs.gui.ASRSDriver;
import brs.gui.BookDriver;

public class MainDriver extends JFrame {

	public MainDriver() {

		Container container = this.getContentPane();
		container.setLayout(new GridLayout(4, 0));
		
		JLabel label = new JLabel("Reservation System", JLabel.CENTER);
		JButton asrs = new JButton("Airline Seat Reservation System");
		JButton brs = new JButton("Book Reservation System");
		JButton exit = new JButton("Exit");

		container.add(label);
		container.add(asrs);
		container.add(brs);
		container.add(exit);

		setSize(500, 400);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

		label.setFont(new Font("Myriad Pro",Font.PLAIN,15));
		
		asrs.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				asrs();
			}

		});

		brs.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				brs();				
			}

		});

		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				dispose();
				System.exit(0);
			}

		});
		
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent evt) {
				setVisible(false);
				dispose();
				System.exit(0);
			}
		});
	}
	
	private void asrs() {
		new ASRSDriver(this);
	}
	
	private void brs() {
		BookDriver myFrame = new BookDriver(this);
		myFrame.setVisible(true);
	}

	public static void main(String[] args) {
		new MainDriver().setVisible(true);
		;
	}
}
