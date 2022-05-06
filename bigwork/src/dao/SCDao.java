package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.SC;
import utils.DBUtils;

public class SCDao {

		public ArrayList<SC> query_all_sc() {
		Connection conn = DBUtils.getConnection();
		String sql = "select student.sno sno,sname,ssex,sage,course.cno,cname,grade from sc,student,course where sc.sno = student.sno and course.cno = sc.cno order by sno;";
		ArrayList<SC> results = new ArrayList<SC>();
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				SC temp = new SC();
				temp.setSno(rs.getString("sno"));
				temp.setSname(rs.getString("sname"));
				temp.setSsex(rs.getString("ssex"));
				temp.setSage(rs.getInt("sage"));
				temp.setCno(rs.getString("cno"));
				temp.setCname(rs.getString("cname"));
				temp.setGrade(rs.getDouble("grade"));
				results.add(temp);
			}
						
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
		return results;
	}
	
		public int insert_sc(String Sno, String Cno, double Grade) {
		Connection conn = DBUtils.getConnection();
		String sql = "insert into sc values(?,?,?);";
		int flag = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, Sno);
			ps.setString(2, Cno);
			ps.setDouble(3, Grade);
			flag = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
		return flag;
	}
	
		public int delete_sc(String Sno,String Cno) {
		Connection conn = DBUtils.getConnection();
		String sql = "delete from sc where sno = ? and cno = ?;";
		int flag = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, Sno);
			ps.setString(2, Cno);
			flag = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
		return flag;
	}
	
		public int alter_sc(String Sno, String Cno,double after_grade) {
		Connection conn = DBUtils.getConnection();
		String sql = "update sc set grade = ? where sno = ? and cno = ?;";
		int flag = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setDouble(1, after_grade);
			ps.setString(2, Sno);
			ps.setString(3, Cno);
			flag = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
		return flag;
	}

}
