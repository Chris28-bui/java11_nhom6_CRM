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
			String query = "select * from user";
			ResultSet result = statement.executeQuery(query);
			
			while(result.next()) {
				User newUser = new User();
				newUser.setId(result.getInt("id"));
				newUser.setEmail(result.getString("email"));
				newUser.setPassword(result.getString("password"));
				newUser.setFullname(result.getString("fullname"));
				newUser.setAddress(result.getString("address"));
				newUser.setPhone(result.getString("phone"));
				newUser.setRole(result.getInt("role_id"));
				
				userList.add(newUser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}
}
