package model;

import java.util.Vector;

public abstract class Company {
	
	private final String id;
	private final String name;
	private final String loginName;
	private final String password;

	public static final int ID = 0;
	public static final int NAME = 1;
	public static final int LOGINNAME = 2;
	public static final int PASSWORD = 3;

	public Company(String id, String name, String loginName, String password) {
		super();
		this.id = id;
		this.name = name;
		this.loginName = loginName;
		this.password = password;
	}
	
	public Vector toVector() {
		Vector v = new Vector();
		v.add(id);
		v.add(name);
		v.add(loginName);
		v.add(password);
		return v;
	}

	public String getid() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getLoginName() {
		return loginName;
	}

	public String getPassword() {
		return password;
	}
}
