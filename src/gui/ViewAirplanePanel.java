package gui;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class ViewAirplanePanel extends JPanel {
	
	  BorderLayout borderLayout1 = new BorderLayout();
	  JScrollPane jScrollPane1 = new JScrollPane();
	  JTable jTable1;
	  Vector headings;

	  public ViewAirplanePanel(JFrame frame, String title) {
	    try {
	      jbInit();
	    }
	    catch(Exception ex) {
	      ex.printStackTrace();
	    }
	  }

	  void jbInit(){
	    setLayout(borderLayout1);
	    add(jScrollPane1, BorderLayout.CENTER);

	    headings = controller.AllAirplanes.getInstance().getCoulumnName();

	    // Note that we need to pad the table to a fixed width for JTable, width is passed to getTable
	    jTable1 = new JTable(controller.AllAirplanes.getInstance().getTable(), headings);
	    jScrollPane1.getViewport().add(jTable1, null);

	  }
}
