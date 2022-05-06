package model;

import java.io.Serializable;

public class Course_avg implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String Cno;
	private String Cname;

	private double avg;
	
	public String getCno() {
		return Cno;
	}
	public void setCno(String cno) {
		Cno = cno;
	}
	public String getCname() {
		return Cname;
	}
	public void setCname(String cname) {
		Cname = cname;
	}

	public double getAvg() {
		return avg;
	}
	public void setAvg(double avg) {
		this.avg = avg;
	}
	
}
