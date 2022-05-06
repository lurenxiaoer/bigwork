package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
	
 
    public static Connection getConnection(){
    	String dbUserName = "root";
    	String dbUserPasswd = "0427";
    	String dbURL = "jdbc:mysql://localhost:3306/studentinfomanagement?"
    	            + "user="+dbUserName+"&password="+dbUserPasswd+"&useUnicode=true&characterEncoding=UTF8";
    	Connection conn = null;
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		conn = (Connection) DriverManager.getConnection(dbURL,dbUserName,dbUserPasswd);
    	} catch (ClassNotFoundException | SQLException e) {
    		e.printStackTrace();
    	} 
    	return conn;
    }
    
  
    public static void closeConnection(Connection conn) {
	
    	if(conn != null){
    		try {
				conn.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
    	}
	}
}
