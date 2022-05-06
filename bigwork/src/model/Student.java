package model;

import java.io.Serializable;

public class Student implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String Sno;
	private String Sname;
	private String Ssex;
	private int Sage;
	private String clno;
	
	public String getSno() {
		return Sno;
	}
	public void setSno(String sno) {
		Sno = sno;
	}
	public String getSname() {
		return Sname;
	}
	public void setSname(String sname) {
		Sname = sname;
	}
	public String getSsex() {
		return Ssex;
	}
	public void setSsex(String ssex) {
		Ssex = ssex;
	}
	public int getSage() {
		return Sage;
	}
	public void setSage(int sage) {
		Sage = sage;
	}
	public String getClno() {
		return clno;
	}
	public void setClno(String clno) {
		this.clno = clno;
	}
	
	
}
