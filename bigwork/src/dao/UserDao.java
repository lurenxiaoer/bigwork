package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.User;
import utils.DBUtils;

public class UserDao {
	
	public boolean userIsExist(String username) {
		Connection conn = DBUtils.getConnection();
		String sql = "select * from user where username = ?";
		try{
			
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				
				return true;
			}
			
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeConnection(conn);
		}
		return false;
	}
	
	public User login(String username,String password) {
		Connection conn = DBUtils.getConnection();
		User user = null;
		String sql = "select * from user where username = ? and password = ?;";
		
		try {
			
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			
			
			if(rs.next()){
				user = new User();
				
				
				
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setLevel(rs.getString("level"));
			}
			
			
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeConnection(conn);
		}
		return user;
	}
	
	public User register(String username,String password,String level) {
		Connection conn = DBUtils.getConnection();
		User user = null;
		
		try {
			
			if(!userIsExist(username)){
				user = new User();
				
				user.setUsername(username);
				user.setPassword(password);
				user.setLevel(level);
				
				Statement stmt = (Statement) conn.createStatement();
				stmt.executeUpdate("insert into user values('"+username+"','"+password+"','"+level+"');");
				stmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeConnection(conn);
		}
		return user;
	}

	public String level(String username){
		Connection conn = DBUtils.getConnection();
		String sql = "select level from user where username = ?;";
		String level = null; 
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				level = rs.getString("level");
			}
			
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeConnection(conn);
		}
		return level;
	}

	public ArrayList<User> query_all_user() {
		Connection conn = DBUtils.getConnection();
		String sql = "select * from user order by username;";
		ArrayList<User> results = new ArrayList<User>();
		
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				User temp = new User();
				temp.setUsername(rs.getString("username"));
				temp.setPassword(rs.getString("password"));
				temp.setLevel(rs.getString("level"));
				results.add(temp);
			}
		
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeConnection(conn);
		}
		return results;
	}

	public int insert_user(String username,String password,String level){
		Connection conn = DBUtils.getConnection();
		String sql = "insert into user values(?,?,?);";
		
		int flag = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, level);
			flag = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeConnection(conn);
		}
		return flag;
	}
	
	public int delete_user(String username) {
		Connection conn = DBUtils.getConnection();
		String sql = "delete from user where username = ?;";
		
		int flag = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, username);
			flag = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeConnection(conn);
		}
		return flag;
	}
	
	public int alter_user(String username,String after_username,String after_password,String after_level) {
		Connection conn = DBUtils.getConnection();
		String sql = "update user set username = ?,password = ?,level = ? where username = ?;";
		
		int flag = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, after_username);
			ps.setString(2, after_password);
			ps.setString(3, after_level);
			ps.setString(4, username);
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
