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
	public List<User> findByRoleId(int id){
		List<User> userList = new LinkedList<User>();
		
		Connection connection = _dbConnection.getConnection();
		String query = "select id, email, password, fullname, address, phone, role_id from user where role_id=?";
		try {
			PreparedStatement statement=connection.prepareStatement(query);
			
			statement.setInt(1,id);
			ResultSet results = statement.executeQuery();
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
		User result = null;
		
		Connection connection = _dbConnection.getConnection();
		String query  = "select * from user where id=?";
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			
			ResultSet results = statement.executeQuery();
			
			while(results.next()) {
				User newUser=new User();
				newUser.setId(results.getInt("id"));
				newUser.setFullname(results.getString("fullname"));
				newUser.setPassword(results.getString("password"));
				newUser.setEmail(results.getString("email"));
				newUser.setAddress(results.getString("address"));
				newUser.setPhone(results.getString("phone"));
				newUser.setRole(roleDao.findByID(results.getInt("role_id")));
				//newUser.setRole(null);
				result =newUser;
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
		
		return result;
	}
	
	public int add(User user)
	{
		int result=-1;
		Connection connection=_dbConnection.getConnection();
		
		try {
			String query="insert into user(`fullname`,`password`,`email`,`address`,`phone`,`role_id`) values(?,?,?,?,?,?)";
			PreparedStatement statement=connection.prepareStatement(query);
			statement.setString(1, user.getFullname());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getAddress());
			statement.setString(5, user.getPhone());
			statement.setInt(6, user.getRole().getId());
			
			result=statement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			};
		}
		
		return result;
	}
	
	public int update(int id,User user)
	{
		int result =-1;
		Connection connection=_dbConnection.getConnection();
		
		try {
			String query="update user set fullname=?,password=?,email=?,address=?,phone=?,role_id=? where id=?";
			PreparedStatement statement=connection.prepareStatement(query);
			statement.setString(1, user.getFullname());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getEmail());
			
			statement.setString(4, user.getAddress());
			statement.setString(5, user.getPhone());
			statement.setInt(6, user.getRole().getId());
			statement.setInt(7, id);
			
			result=statement.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
		
		return result;
	}
	
	public int delete(int id)
	{
		int result=-1;
		Connection connection=_dbConnection.getConnection();
		
		try {
			String query="delete from user where id=?";
			PreparedStatement statement=connection.prepareStatement(query);
			statement.setInt(1, id);
			
			result=statement.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
		return result ;
				
	}
	
	
	
}
