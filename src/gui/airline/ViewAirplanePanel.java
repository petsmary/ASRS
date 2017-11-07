package gui.airline;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class ViewAirplanePanel extends JPanel {
	
	  BorderLayout borderLayout1 = new BorderLayout();
	  JScrollPane jScrollPane1 = new JScrollPane();
	  JTable jTable1;
	  Vector headings;

	  public ViewAirplanePanel(JFrame frame, Object key) {
	    try {
	      jbInit(key);
	    }
	    catch(Exception ex) {
	      ex.printStackTrace();
	    }
	  }

	  void jbInit(Object key){
	    setLayout(borderLayout1);
	    add(jScrollPane1, BorderLayout.CENTER);

	    headings = controller.AllAirplanes.getInstance().getCoulumnName();

	    jTable1 = new JTable();
	    jTable1.setModel(new DefaultTableModel(controller.AllAirplanes.getInstance().getAirplane(model.Airplane.AIRLINE, key), headings) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
	    jScrollPane1.getViewport().add(jTable1, null);

	  }
}
