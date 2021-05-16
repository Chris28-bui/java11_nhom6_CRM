package cybersoft.java11.crm.dao.container;

import cybersoft.java11.crm.config.DatabaseConnection;
import cybersoft.java11.crm.config.MySqlConnection;

public class IOCContainer {
	private static MySqlConnection _mySqlConnection = null;
	public static DatabaseConnection getDataBaseConnection() {
		// TODO Auto-generated method stub
		String url = "jdbc:mysql://localhost:3306/crm";
		String username = "root";
		String password = "1234";
		if(_mySqlConnection==null) {
			return new MySqlConnection(url, username, password);
		}
		return _mySqlConnection;
	}

	
	
}
