package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Department;
import utils.DBUtils;

public class DepartmentDao {
		public ArrayList<Department> query_all_department() {
		Connection conn = DBUtils.getConnection();
		String sql = "select * from department order by dno;";
		ArrayList<Department> results = new ArrayList<Department>();
		
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				Department temp = new Department();
				temp.setDno(rs.getString("Dno")); 
				temp.setDname(rs.getString("Dname"));
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
		public int insert_department(String dno,String dname){
		Connection conn = DBUtils.getConnection();
		String sql = "insert into department values(?,?);";
		int flag = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, dno);
			ps.setString(2, dname);
			flag = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeConnection(conn);
		}
		return flag;
	}
		public int delete_department(String dno) {
		Connection conn = DBUtils.getConnection();
		String sql = "delete from department where dno = ?;";
		int flag = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, dno);
			flag = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeConnection(conn);
		}
		return flag;
	}
		public int alter_department(String dno,String after_dno,String after_dname) {
		Connection conn = DBUtils.getConnection();
		String sql = "update department set dno = ?,dname = ? where dno = ?;";
		int flag = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, after_dno);
			ps.setString(2, after_dname);
			ps.setString(3, dno);
			flag = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeConnection(conn);
		}
		return flag;
	}

}
