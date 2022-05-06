package model;

import java.io.Serializable;

public class Department implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String Dno;
	private String Dname;
	
	public String getDno() {
		return Dno;
	}
	public void setDno(String dno) {
		Dno = dno;
	}
	public String getDname() {
		return Dname;
	}
	public void setDname(String dname) {
		Dname = dname;
	}

}
