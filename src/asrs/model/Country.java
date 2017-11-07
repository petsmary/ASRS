package asrs.model;

import java.util.Vector;

public class Country {

	private final String id;
	private final String code;
	private final String name;
	private final String continent;

	public static final int ID = 0;
	public static final int CODE = 1;
	public static final int NAME = 2;
	public static final int CONTINENT = 3;

	public Country(String id, String code, String name, String continent) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.continent = continent;
	}

	public Country(Vector v) {
		this.id = (String) v.get(ID);
		this.name = (String) v.get(NAME);
		this.code = (String) v.get(CODE);
		this.continent = (String) v.get(CONTINENT);
	}

	public Vector toVector() {
		Vector v = new Vector();
		v.add(id);
		v.add(code);
		v.add(name);
		v.add(continent);
		return v;
	}

	public String getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getContinent() {
		return continent;
	}

}
