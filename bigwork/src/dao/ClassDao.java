package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Class;
import utils.DBUtils;

public class ClassDao {
		public ArrayList<Class> query_all_class() {
		Connection conn = DBUtils.getConnection();
		String sql = "select * from class order by clno;";
		ArrayList<Class> results = new ArrayList<Class>();
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Class temp = new Class();
				temp.setClno(rs.getString("clno"));
				temp.setClname(rs.getString("clname"));
				temp.setDno(rs.getString("dno"));
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
		public int insert_class(String clno, String clname, String dno) {
		Connection conn = DBUtils.getConnection();
		String sql = "insert into class values(?,?,?);";
		int flag = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, clno);
			ps.setString(2, clname);
			ps.setString(3, dno);
			flag = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
		return flag;
	}
		public int delete_class(String clno) {
		Connection conn = DBUtils.getConnection();
		String sql = "delete from class where clno = ?;";
		int flag = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, clno);
			flag = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
		return flag;
	}
		public int alter_class(String clno, String after_clno, String after_clname, String after_dno) {
		Connection conn = DBUtils.getConnection();
		String sql = "update class set clno = ?,clname = ?,dno = ? where clno = ?;";
		int flag = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, after_clno);
			ps.setString(2, after_clname);
			ps.setString(3, after_dno);
			ps.setString(4, clno);
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
