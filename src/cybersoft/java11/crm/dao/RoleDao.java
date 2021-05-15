package cybersoft.java11.crm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import cybersoft.java11.crm.config.MySqlConnection;
import cybersoft.java11.crm.model.Role;

public class RoleDao {
	public List<Role> findAll(){
		//return all roles in databases
		List<Role> listRole = new LinkedList<Role>();
		
		Connection connection = MySqlConnection.getConnection();
		try {
			Statement statement = connection.createStatement();
			String query = "select id, name, description from role";
			ResultSet results = statement.executeQuery(query);
			
			//iterator
			while(results.next()) {
				Role newRole = new Role();
				newRole.setId(results.getInt("id"));
				newRole.setName(results.getString("name"));
				newRole.setDescription(results.getString("description"));
				
				listRole.add(newRole);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				connection.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return listRole;
	}
	
	public Role findByID(int id) {
		Connection connection = MySqlConnection.getConnection();
		Role result = null;
		
		try {
			String query = "select id, name, description from role where id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Role role = new Role();
				role.setId(resultSet.getInt("id"));
				role.setName(resultSet.getString("name"));
				role.setDescription(resultSet.getString("description"));
				
				result = role;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		
		return result;
	}
	
	public int add(Role role) {
		int result = -1;
		
		Connection connection = MySqlConnection.getConnection();
		
		try {
			String query = "insert role(`name`, `description`) values(?, ?)";
			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, role.getName());
			statement.setString(2, role.getDescription());
			
			result = statement.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				connection.close();
			} catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
		
		return result;
	}
	
	public int update(int id, Role role) {
		int result = -1;
		
		Connection connection = MySqlConnection.getConnection();
		
		try {
			String query = "update role set name = ?, description = ? where id = ?";
			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, role.getName());
			statement.setString(2, role.getDescription());
			statement.setInt(3, id);
			
			result = statement.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
		
		return result;
	}
	
	public int deleteByID(int id) {
		int result = -1;
		
		Connection connection = MySqlConnection.getConnection();
		
		try {
			String query = "delete from role where id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setInt(1, id);
			
			result = statement.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
}
