package cybersoft.java11.crm.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection implements DatabaseConnection {
	/* database connection information */
	private static String url;
	private static String username; 
	private static String password; 
	
	public MySqlConnection(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}


	@Override
	public Connection getConnection() {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			// TODO: 
			e.printStackTrace();
			System.out.println("Driver could not be found.");
		} catch (SQLException e) {
			// TODO:
			e.printStackTrace();
			System.out.println("Can not connect to database.");
			System.out.println("Driver not found");
			e.printStackTrace();
		} 
		return null;
	}
}
