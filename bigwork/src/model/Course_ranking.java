package model;

import java.io.Serializable;

public class Course_ranking implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String Sno;
	private String Dname;
	private String Clname;
	private String Sname;
	private String Ssex;
	private int Sage;
	private Double grade;

	
	public String getDname() {
		return Dname;
	}
	public void setDname(String dname) {
		Dname = dname;
	}
	public String getClname() {
		return Clname;
	}
	public void setClname(String clname) {
		Clname = clname;
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
	public Double getGrade() {
		return grade;
	}
	public void setGrade(Double grade) {
		this.grade = grade;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
