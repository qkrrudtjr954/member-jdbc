package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection implements DBConnection{
	
	public void initConnect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Loading Success!!");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection makeConnection() {
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/member", "root", "Rudtjr1216");
			System.out.println("Data Base is connected.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}

}
