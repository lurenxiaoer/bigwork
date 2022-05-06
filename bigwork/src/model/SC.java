package model;

import java.io.Serializable;

public class SC implements Serializable {
			
	private static final long serialVersionUID = 1L;

	private String Sno;
	private String Sname;
	private String Ssex;
	private int Sage;
	private String Cno;
	private String Cname;
	private double Grade;
	
	public String getCname() {
		return Cname;
	}
	public void setCname(String cname) {
		Cname = cname;
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
	public void setSage(int i) {
		Sage = i;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setGrade(double grade) {
		Grade = grade;
	}
	public String getSno() {
		return Sno;
	}
	public void setSno(String sno) {
		Sno = sno;
	}
	public String getCno() {
		return Cno;
	}
	public void setCno(String cno) {
		Cno = cno;
	}
	public double getGrade() {
		return Grade;
	}
	public void setGrade(int grade) {
		Grade = grade;
	}
}
