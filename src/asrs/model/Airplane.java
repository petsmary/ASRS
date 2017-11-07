package asrs.model;

import java.util.Vector;

public class Airplane {

	private final String id;
	private final String name;
	private final String airline;
	private final String row;
	private final String column;
	private final String first;
	private final String bussiness;
	private final String economic;

	public static final int ID = 0;
	public static final int NAME = 1;
	public static final int AIRLINE = 2;
	public static final int ROW = 3;
	public static final int COLUMN = 4;
	public static final int FIRST = 5;
	public static final int BUSSINESS = 6;
	public static final int ECONOMIC = 7;

	public Airplane(String id, String name, String airline, String row, String column, String first, String bussiness,
			String economic) {
		super();
		this.id = id;
		this.name = name;
		this.airline = airline;
		this.row = row;
		this.column = column;
		this.first = first;
		this.bussiness = bussiness;
		this.economic = economic;
	}
	
	public Airplane(Vector v) {
		this.id = (String) v.get(ID);
		this.name = (String) v.get(NAME);
		this.airline = (String) v.get(AIRLINE);
		this.row = (String) v.get(ROW);
		this.column = (String) v.get(COLUMN);
		this.first = (String)  v.get(FIRST);
		this.bussiness = (String) v.get(BUSSINESS);
		this.economic = (String) v.get(ECONOMIC);
	}
	
	public Vector toVector() {
		Vector v = new Vector();
		v.add(id);
		v.add(name);
		v.add(airline);
		v.add(row);
		v.add(column);
		v.add(first);
		v.add(bussiness);
		v.add(economic);
		return v;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAirline() {
		return airline;
	}

	public String getRow() {
		return row;
	}

	public String getColumn() {
		return column;
	}

	public String getFirst() {
		return first;
	}

	public String getBussiness() {
		return bussiness;
	}

	public String getEconomic() {
		return economic;
	}

}
