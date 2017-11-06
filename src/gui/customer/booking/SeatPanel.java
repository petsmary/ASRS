package gui.customer.booking;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SeatPanel extends JPanel {
	
	private JPanel airplanePanel;	
	private int type;	
	private String name;	
	private int startRow = 1;	
	private int numOfRows = 20;
	private int numOfColumns = 4;
			
	private ArrayList<JButton> buttons = new ArrayList<JButton>();
	
	public SeatPanel(JFrame frame, JPanel airplanePanel) {
		this.airplanePanel = airplanePanel;
		
		setLayout(new GridLayout(numOfColumns, numOfRows));		
		
		int buttonWidth = airplanePanel.getWidth() / numOfRows;
        int buttonHeight = airplanePanel.getHeight() / numOfColumns;
        
		JButton button;
        
		for (int i = numOfColumns; i >= 1; i--) {
			String letter = String.valueOf((char)(i + 64));
			for (int j = startRow; j <= numOfRows; j++) {				
				button = new JButton();
				button.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
				button.setText(letter + Integer.toString(j));
				buttons.add(button);
				add(button);
			}
		}
	}
}
