package cybersoft.java11.crm.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import cybersoft.java11.crm.config.MySqlConnection;
import cybersoft.java11.crm.model.User;

public class UserDao {
	public List<User> findAll(){
		List<User> userList = new LinkedList<User>();
		
		Connection connection = MySqlConnection.getConnection();
		
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
				newUser.setRole(results.getInt("role_id"));
				
				userList.add(newUser);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}
}