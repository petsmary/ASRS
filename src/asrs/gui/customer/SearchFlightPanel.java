package asrs.gui.customer;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class SearchFlightPanel extends JPanel {

	private JFrame frame;
	private JPanel inputPanel;
	private JScrollPane jScrollPane;

	private JTable resultTable;

	private JLabel from;
	private JLabel to;
	private JLabel stopover;

	private JComboBox<String> fromList;
	private JComboBox<String> stopoverList;
	private JComboBox<String> toList;

	private JButton searchFlight;

	private String defaultString = "";
	private Vector rows = new Vector();
	private Vector columns = new Vector();
	private Vector data = asrs.controller.AllFlights.getInstance().getTable();
	private Vector<String> headings = asrs.controller.AllFlights.getInstance().getColumnName();
	
	

	public SearchFlightPanel(JFrame frame) {
		this.frame = frame;

		setLayout(new BorderLayout());

		Vector<String> counties = asrs.controller.AllCounties.getInstance().getAllCountiesName();
		counties.add(0, defaultString);

		inputPanel = new JPanel(new GridBagLayout());
		jScrollPane = new JScrollPane();
		resultTable = new JTable();
		from = new JLabel("	From");
		to = new JLabel("	To");
		stopover = new JLabel("Stopover City");
		fromList = new JComboBox<String>(counties);
		stopoverList = new JComboBox<String>(counties);
		toList = new JComboBox<String>(counties);
		searchFlight = new JButton("Search Flight");
		// resultTable.setE

		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.fill = GridBagConstraints.BOTH;
		int x, y;
		addGB(from, x = 0, y = 0);
		addGB(to, x = 0, y = 1);
		addGB(fromList, x = 1, y = 0);
		addGB(toList, x = 1, y = 1);
		addGB(searchFlight, x = 2, y = 1);

		jScrollPane.getViewport().add(resultTable, null);
		add(BorderLayout.NORTH, inputPanel);
		add(BorderLayout.CENTER, jScrollPane);

		searchFlight.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dbInit();
			}

		});
		
		resultTable.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = resultTable.rowAtPoint(evt.getPoint());
				int col = resultTable.columnAtPoint(evt.getPoint());
				if (row >= 0 && col >= 0) {
					System.out.println(data.get(row));
					new ViewFlightDetailDialog(frame, "Flight Detail", true, (Vector) data.get(row));
				}
			}
		});
		

		columns.add(headings.get(asrs.model.Flight.NAME));
		columns.add(headings.get(asrs.model.Flight.DEPARTURE));
		columns.add(headings.get(asrs.model.Flight.ARRIVE));		

		resultTable.setModel(new DefaultTableModel(rows, columns) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
	}

	GridBagConstraints constraints = new GridBagConstraints();

	private void addGB(Component component, int x, int y) {
		constraints.gridx = x;
		constraints.gridy = y;
		inputPanel.add(component, constraints);
	}

	private void dbInit() {
		if (getSource() == defaultString || getDestination() == defaultString) {
			JOptionPane.showMessageDialog(SearchFlightPanel.this, "Please input a suitable country", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		rows = new Vector();
		data = asrs.controller.AllFlights.getInstance().getFlight(asrs.model.Flight.DESTINATION, getDestination());
		ArrayList<Integer> index = new ArrayList<Integer> ();
		int i = 0;
		if (data != null) {			
			for (Iterator it = data.iterator(); it.hasNext();) {
				Vector itemRow = (Vector) it.next();
				System.out.println(data);				
				if (!itemRow.get(asrs.model.Flight.SOURCE).equals(getSource())) {
					//data.remove(itemRow);
					index.add(i);
				} else {
					Vector row = new Vector();
					row.add(itemRow.get(asrs.model.Flight.NAME));
					row.add(itemRow.get(asrs.model.Flight.DEPARTURE));
					row.add(itemRow.get(asrs.model.Flight.ARRIVE));
					rows.add(row);
				}
				i++;
			}
			for (int j=0; j<index.size(); j++) {
				data.remove(index.get(j));
			}
		}

		resultTable.setModel(new DefaultTableModel(rows, columns) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
	}

	private String getSource() {
		String source = (String) fromList.getSelectedItem();
		return source;
	}

	private String getDestination() {
		String destination = (String) toList.getSelectedItem();
		return destination;
	}
}
