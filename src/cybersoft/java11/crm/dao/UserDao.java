package cybersoft.java11.crm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import cybersoft.java11.crm.config.DatabaseConnection;
import cybersoft.java11.crm.config.MySqlConnection;
import cybersoft.java11.crm.dao.container.IOCContainer;
import cybersoft.java11.crm.model.User;

public class UserDao {
	private DatabaseConnection _dbConnection;
	private RoleDao roleDao;
	
	public UserDao() {
		_dbConnection = IOCContainer.getDatabaseConnection();
		roleDao = new RoleDao();
	}
	
	public List<User> findAll(){
		List<User> userList = new LinkedList<User>();
		
		Connection connection = _dbConnection.getConnection();
		
		try {
			Statement statement = connection.createStatement();
			String query = "select id, email, password, fullname, address, phone, role_id from user";
			ResultSet results = statement.executeQuery(query);
				
			while(results.next()) {
				User newUser = new User();
				
				newUser.setId(results.getInt("id"));
				newUser.setEmail(results.getString("email"));
				newUser.setPassword(results.getString("password"));
				newUser.setFullname(results.getString("fullname"));
				newUser.setAddress(results.getString("address"));
				newUser.setPhone(results.getString("phone"));
				newUser.setRole(roleDao.findByID(results.getInt("role_id")));
//				newUser.setRole(null);
				
				userList.add(newUser);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}
	
	public User findById(int id) {
		User newUser = null;
		
		Connection connection = _dbConnection.getConnection();
		String query  = "select * from User where id=?";
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			
			ResultSet results = statement.executeQuery();
			
			while(results.next()) {
				newUser.setId(results.getInt("id"));
				newUser.setFullname(results.getString("fullname"));
				newUser.setEmail(results.getString("email"));
				newUser.setAddress(results.getString("address"));
				newUser.setPhone(results.getString("phone"));
				newUser.setRole(null);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
		
		return newUser;
	}
}
