package asrs.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

public class JDBCDatabaseTable {

	private final static Connection connection = LoadProperties.getInstance().getConnection();
	
	private final String tableName;
	
	private Vector columnNames;
	
	private Vector cache;

	public Vector getTable() {
		return cache;
	}

	public JDBCDatabaseTable(String tableName) {
		this.tableName = tableName;
		cache = new Vector();
		columnNames = new Vector();
		init();
	}
			
	public Vector getColumnNames() {
		return columnNames;
	}

	private void init() {
		try {
			String sql = "SELECT * FROM " + tableName;
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			ResultSetMetaData metadata = resultSet.getMetaData();
		    int columnCount = metadata.getColumnCount(); 
		    for(int i = 1; i <= columnCount; i++) {
		    		columnNames.add(metadata.getColumnName(i));
		    }
			while(resultSet.next()) {
				Vector v = new Vector();
				for (int i = 1; i <= columnCount; i++) {
					v.add(resultSet.getString(i));					
		        }
				cache.add(v);
			}
		} catch (Exception ex) {
			 System.out.println("Exception:" + ex);
		}
	}
	
	private void restore() {
		cache = new Vector();
		try {
			String sql = "SELECT * FROM " + tableName;
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				Vector v = new Vector();	
				for (int i = 1; i <= columnNames.size(); i++) {
					v.add(resultSet.getString(i));					
		        }
				cache.add(v);
			}
		} catch (Exception ex) {
			 System.out.println("Exception:" + ex);
		}		
	}
	
	private void insert(Vector keyAndData) {
		try {
			String sql = "INSERT INTO " + tableName + " ( ";
			String data = " VALUES ( ";
			for(int i = 1; i < columnNames.size(); i++) {
				if(i == 1) {
					sql +=  columnNames.get(i);
					data += "?";
				}
				else {
					sql +=  ", " + columnNames.get(i);			
					data += ", ?";
				}
			}
			sql +=  " ) ";
			data += " ) ";
			sql += data;
			System.out.println(sql);
			PreparedStatement statement = connection.prepareStatement(sql);
			for(int i = 1; i < columnNames.size(); i++) {
				statement.setObject(i, keyAndData.get(i));
			}
			statement.executeUpdate();
		} catch (Exception ex) {
			 System.out.println("Exception:" + ex);
		}
	}
	
	private void update(Vector keyAndData) {
		try {
			String sql = "UPDATE "+ tableName + " SET ";
			for(int i = 1; i < columnNames.size(); i++) {
				if(i == 1) {
					sql +=  columnNames.get(i) + " = ?";
				}
				else {
					sql +=  ", " + columnNames.get(i) + " = ?";			
				}
			}
			sql +=  " WHERE " + columnNames.get(0) + " = " + keyAndData.get(0);
			System.out.println(sql);
			PreparedStatement statement = connection.prepareStatement(sql);
			for(int i = 1; i < columnNames.size(); i++) {
				statement.setObject(i, keyAndData.get(i));
			}
			statement.executeUpdate();
		} catch (Exception ex) {
			 System.out.println("Exception:" + ex);
		}
	}
	
	private void delete(Object key) {
		try {
			String sql = "DELETE FROM " + tableName + " WHERE " + columnNames.get(0) + " = " + key;
			System.out.println(sql);
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.executeUpdate();
		} catch (Exception ex) {
			 System.out.println("Exception:" + ex);
		}
	}
	
	public Vector get(int columnIndex, Object key) {
		Vector vector = new Vector();
		for (int i = 0; i < cache.size(); i++) {
			Vector keyAndData = (Vector) cache.get(i);
			String s = (String) keyAndData.get(columnIndex);
			if (s.compareTo((String) key) == 0)
				vector.add(keyAndData);
		}
		return vector;
	}

	public Vector get(Object key) {
		for (int i = 0; i < cache.size(); i++) {
			Vector keyAndData = (Vector) cache.get(i);
			String s = (String) keyAndData.get(0);
			if (s.compareTo((String) key) == 0)
				return keyAndData;
		}
		return null;
	}

	public boolean put(Vector keyAndData) {
		if (get(keyAndData.get(0)) != null) {
			return false;
		} else {			
			insert(keyAndData);
			restore();
			return true;
		}
	}
	
	public boolean change(Vector keyAndData) {
		if (get(keyAndData.get(0)) == null) {
			return false;
		} else {
			update(keyAndData);
			restore();
			return true;
		}
	}

	public boolean remove(Object key) {
		for (int i = 0; i < cache.size(); i++) {
			Vector keyAndData = (Vector) cache.get(i);
			if (((String) keyAndData.get(0)).equals(key)) {
				cache.remove(i);
				delete(key);
				return true;
			}
		}
		return false;
	}
}
