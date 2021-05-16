package cybersoft.java11.crm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cybersoft.java11.crm.config.DatabaseConnection;
import cybersoft.java11.crm.config.MySqlConnection;
import cybersoft.java11.crm.dao.container.IOCContainer;
import cybersoft.java11.crm.model.User;

public class AuthDao {
	private RoleDao roleDao;
	
	private DatabaseConnection _dbConnection;
	
	public AuthDao() {
		roleDao = new RoleDao();
		_dbConnection = IOCContainer.getDataBaseConnection();
	}
	
	public User login(String email, String password) throws SQLException {
//		User user = null;
		User newUser = null;
		
		Connection connection = _dbConnection.getConnection();
		
		String query = "select id, email, fullname, address, phone, role_id\n"
				+ "from user\n"
				+ "where email = ? and password = ?";
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			//bắt đầu từ số 1
			statement.setString(1, email);
			statement.setString(2, password);
			
			ResultSet result = statement.executeQuery();
			
			//iterator
			if(result.next()) {
				newUser = new User();
				newUser.setId(result.getInt("id"));
				newUser.setEmail(result.getString("email"));
				newUser.setFullname(result.getString("fullname"));
				newUser.setAddress(result.getString("address"));
				newUser.setPhone(result.getString("phone"));
				newUser.setRole(roleDao.findByID(result.getInt("role_id")));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return newUser;
	}
}
