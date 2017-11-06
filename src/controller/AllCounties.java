package controller;

import java.util.Iterator;
import java.util.Vector;

public class AllCounties {

	static private db.JDBCDatabaseTable theCounties;

	static private AllCounties instance = new AllCounties("Country");

	private AllCounties(String tableName) {
		theCounties = new db.JDBCDatabaseTable(tableName);
	}

	public final static AllCounties getInstance() {
		return instance;
	}

	public Vector getTable() {
		return theCounties.getTable();
	}

	public Vector<String> getAllCountiesName() {
		Vector<String> names = new Vector();
		Vector v = getTable();
		for (Iterator it = v.iterator(); it.hasNext();) {
			Vector itemRow = (Vector) it.next();
			String name = (String) itemRow.get(model.Country.NAME);
			names.add(name);
		}
		return names;
	}
}
