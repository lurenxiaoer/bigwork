package model;

import java.io.Serializable;

public class Course_fail_rate implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String Cno;
	private String Cname;
	
	private double fail_rate;
	
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
	public double getFail_rate() {
		return fail_rate;
	}
	public void setFail_rate(double fail_rate) {
		this.fail_rate = fail_rate;
	}
	
}
