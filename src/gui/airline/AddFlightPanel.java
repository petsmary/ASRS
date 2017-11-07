package gui.airline;

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
import javax.swing.JTextField;

import com.github.lgooddatepicker.components.DateTimePicker;
import com.github.lgooddatepicker.optionalusertools.DateTimeChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;
import com.github.lgooddatepicker.zinternaltools.DateTimeChangeEvent;
import com.github.lgooddatepicker.zinternaltools.TimeChangeEvent;

@SuppressWarnings("serial")
public class AddFlightPanel extends JPanel {

	private void add() {
		if (flightName.getText().equals("") || airplane.getSelectedItem().equals("")
				|| source.getSelectedItem().equals("") || destination.getSelectedItem().equals("") || departureD == null
				|| departureT == null || arriveD == null || arriveT == null || priceF.getText().equals("")
				|| priceB.getText().equals("") || priceE.getText().equals("")) {
			JOptionPane.showMessageDialog(frame, "Please input all the information", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		} else if (controller.AllFlights.getInstance().getFlight(model.Flight.NAME, flightName.getText()).size() != 0) {
			JOptionPane.showMessageDialog(frame, "The Flight name is used", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		} else {
			String departure = departureD + " " + departureT + ":00";
			String arrive = arriveD + " " + arriveT + ":00";
			Vector v = new Vector();
			v.add("");
			v.add(flightName.getText());
			v.add(source.getSelectedItem());
			v.add(destination.getSelectedItem());
			v.add(departure);
			v.add(arrive);
			v.add(idOfAirplane.get((idOfAirplane.indexOf(airplane.getSelectedItem()) - 1)));
			System.out.println(idOfAirplane.get((idOfAirplane.indexOf(airplane.getSelectedItem()) - 1)));
			if (controller.AllFlights.getInstance().putFlight(v)) {
				Vector vector = controller.AllFlights.getInstance().getFlight(model.Flight.NAME, flightName.getText());
				Vector flight = (Vector) vector.get(0);
				Vector v1 = new Vector();
				Vector v2 = new Vector();
				Vector v3 = new Vector();
				v1.add("");
				v1.add("1");
				v1.add(priceF.getText());
				v1.add(leftF.getText());
				v1.add(totalF.getText());
				v1.add(flight.get(model.Flight.ID));
				v2.add("");
				v2.add("2");
				v2.add(priceB.getText());
				v2.add(leftB.getText());
				v2.add(totalB.getText());
				v2.add(flight.get(model.Flight.ID));
				v3.add("");
				v3.add("3");
				v3.add(priceE.getText());
				v3.add(leftE.getText());
				v3.add(totalE.getText());
				v3.add(flight.get(model.Flight.ID));
				controller.AllFlights.getInstance().putFlightDetail(v1);
				controller.AllFlights.getInstance().putFlightDetail(v2);
				controller.AllFlights.getInstance().putFlightDetail(v3);
				JOptionPane.showMessageDialog(frame, "Successful to add new flight", "Flight",
						JOptionPane.INFORMATION_MESSAGE);

			}
			flightName.setText("");
			source.setSelectedItem("");
			destination.setSelectedItem("");
			priceB.setText("");
			priceF.setText("");
			priceE.setText("");
			airplane.setSelectedItem("");
		}
	}

	private void changeSeats() {
		for (Iterator it = airplanesList.iterator(); it.hasNext();) {
			Vector v = (Vector) it.next();
			if (v.get(model.Airplane.NAME).equals(airplane.getSelectedItem())) {
				int totalColumn = Integer.parseInt(((String) v.get(model.Airplane.COLUMN)));
				int first = Integer.parseInt(((String) v.get(model.Airplane.FIRST))) * totalColumn;
				int bussiness = Integer.parseInt(((String) v.get(model.Airplane.BUSSINESS))) * totalColumn;
				int economic = Integer.parseInt(((String) v.get(model.Airplane.ECONOMIC))) * totalColumn;
				totalF.setText(first + "");
				totalB.setText(bussiness + "");
				totalE.setText(economic + "");

				leftF.setText(first + "");
				leftB.setText(bussiness + "");
				leftE.setText(economic + "");

				if (first == 0) {
					priceF.setEnabled(false);
					priceF.setText("0");
				} else {
					priceF.setEnabled(true);
					priceF.setText("");
				}
				if (bussiness == 0) {
					priceB.setEnabled(false);
					priceB.setText("0");
				} else {
					priceB.setEnabled(true);
					priceB.setText("");
				}
				if (economic == 0) {
					priceE.setEnabled(false);
					priceE.setText("0");
				} else {
					priceE.setEnabled(true);
					priceE.setText("");
				}
			}
		}
	}

	private Vector idOfAirplane;
	private Vector airplanesList;

	public AddFlightPanel(JFrame frame, Object key) {
		this.frame = frame;
		Vector<String> counties = controller.AllCounties.getInstance().getAllCountiesName();
		counties.add(0, "");

		airplanesList = controller.AllAirplanes.getInstance().getAirplane(model.Airplane.AIRLINE, key);
		Vector<String> airplanes = new Vector();
		idOfAirplane = new Vector();
		airplanes.add("");
		for (Iterator it = airplanesList.iterator(); it.hasNext();) {
			Vector v = (Vector) it.next();
			airplanes.add((String) v.get(model.Airplane.NAME));
			idOfAirplane.add(v.get(model.Airplane.ID));
			idOfAirplane.add(v.get(model.Airplane.NAME));
		}

		setLayout(new GridLayout(10, 4));
		flightName = new JTextField(10);
		airplane = new JComboBox<String>(airplanes);
		source = new JComboBox<String>(counties);
		destination = new JComboBox<String>(counties);
		departure = new DateTimePicker();
		arrive = new DateTimePicker();

		departure.addDateTimeChangeListener(new SampleDateTimeChangeListener("departure"));
		arrive.addDateTimeChangeListener(new SampleDateTimeChangeListener("arrive"));

		priceF = new JTextField(10);
		leftF = new JTextField(4);
		totalF = new JTextField(4);
		priceB = new JTextField(10);
		leftB = new JTextField(4);
		totalB = new JTextField(4);
		priceE = new JTextField(10);
		leftE = new JTextField(4);
		totalE = new JTextField(4);

		leftF.setEnabled(false);
		totalF.setEnabled(false);
		leftB.setEnabled(false);
		totalB.setEnabled(false);
		leftE.setEnabled(false);
		totalE.setEnabled(false);

		jlabel1 = new JLabel("	Flight Name: ");
		jlabel2 = new JLabel("	Airplane Name: ");
		jlabel3 = new JLabel("	Source: ");
		jlabel4 = new JLabel("	Destination: ");
		jlabel5 = new JLabel("	Departure: ");
		jlabel6 = new JLabel("	Departure: ");

		jlabel7 = new JLabel("	First Class");
		jlabel8 = new JLabel("	Price: ");
		jlabel9 = new JLabel("	Seat Left: ");
		jlabel10 = new JLabel("	Total Seats: ");

		jlabel11 = new JLabel("	Bussiness Class");
		jlabel12 = new JLabel("	Price: ");
		jlabel13 = new JLabel("	Seat Left: ");
		jlabel14 = new JLabel("	Total Seats: ");

		jlabel15 = new JLabel("	Economic Class");
		jlabel16 = new JLabel("	Price: ");
		jlabel17 = new JLabel("	Seat Left: ");
		jlabel18 = new JLabel("	Total Seats: ");

		jButton1 = new JButton("Submit");

		add(jlabel1);
		add(flightName);
		add(jlabel2);
		add(airplane);
		add(jlabel3);
		add(source);
		add(jlabel4);
		add(destination);
		add(jlabel5);
		add(departure);
		add(jlabel6);
		add(arrive);
		add(jlabel7);
		add(new JLabel());
		add(jlabel9);
		add(leftF);
		add(jlabel8);
		add(priceF);
		add(jlabel10);
		add(totalF);
		add(jlabel11);
		add(new JLabel());
		add(jlabel13);
		add(leftB);
		add(jlabel12);
		add(priceB);
		add(jlabel14);
		add(totalB);
		add(jlabel15);
		add(new JLabel());
		add(jlabel17);
		add(leftE);
		add(jlabel16);
		add(priceE);
		add(jlabel18);
		add(totalE);
		add(jButton1);

		airplane.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeSeats();
			}
		});

		jButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				add();
			}

		});
	}

	private static class SampleDateTimeChangeListener implements DateTimeChangeListener {

		public String dateTimePickerName;

		private SampleDateTimeChangeListener(String dateTimePickerName) {
			this.dateTimePickerName = dateTimePickerName;
		}

		@Override
		public void dateOrTimeChanged(DateTimeChangeEvent event) {
			DateChangeEvent dateEvent = event.getDateChangeEvent();
			if (dateEvent != null) {
				if (dateTimePickerName.equals("arrive")) {
					arriveD = dateEvent.getNewDate() + "";
				} else {
					departureD = dateEvent.getNewDate() + "";
				}
			}
			TimeChangeEvent timeEvent = event.getTimeChangeEvent();
			if (timeEvent != null) {
				if (dateTimePickerName.equals("arrive")) {
					arriveT = timeEvent.getNewTime() + "";
				} else {
					departureT = timeEvent.getNewTime() + "";
				}
			}
		}
	}

	private JFrame frame;

	private JTextField flightName;
	private JComboBox<String> airplane;
	private JComboBox<String> source;
	private JComboBox<String> destination;
	private DateTimePicker departure;
	private DateTimePicker arrive;
	private JTextField priceF;
	private JTextField leftF;
	private JTextField totalF;
	private JTextField priceB;
	private JTextField leftB;
	private JTextField totalB;
	private JTextField priceE;
	private JTextField leftE;
	private JTextField totalE;

	private JLabel jlabel1;
	private JLabel jlabel2;
	private JLabel jlabel3;
	private JLabel jlabel4;
	private JLabel jlabel5;
	private JLabel jlabel6;
	private JLabel jlabel7;
	private JLabel jlabel8;
	private JLabel jlabel9;
	private JLabel jlabel10;
	private JLabel jlabel11;
	private JLabel jlabel12;
	private JLabel jlabel13;
	private JLabel jlabel14;
	private JLabel jlabel15;
	private JLabel jlabel16;
	private JLabel jlabel17;
	private JLabel jlabel18;

	private static String departureD = null;
	private static String arriveD = null;
	private static String departureT = null;
	private static String arriveT = null;

	private JButton jButton1;
}
