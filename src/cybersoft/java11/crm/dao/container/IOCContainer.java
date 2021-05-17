package cybersoft.java11.crm.dao.container;

import cybersoft.java11.crm.config.DatabaseConnection;
import cybersoft.java11.crm.config.MySqlConnection;

public class IOCContainer {
	
	private static MySqlConnection _mySqlConnection = null;
	private static String ENV = "PRODUCTION";

	public static DatabaseConnection getDatabaseConnection() {
		// TODO Auto-generated method stub
		
		String url;
		String username;
		String password;
		
		if(ENV.equals("PRODUCTION")) {
			url = "jdbc:mysql://localhost:3306/crm";
			username = "root";
			password = "1234";
		} else {
			url = "jdbc:mysql://localhost:3306/crm";
			username = "root";
			password = "1234";
		}
		
		if(_mySqlConnection == null) {
			return new MySqlConnection(url, username, password); 
		}
		
		return _mySqlConnection;
	}
	
}
