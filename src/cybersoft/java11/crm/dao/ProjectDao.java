package cybersoft.java11.crm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import cybersoft.java11.crm.config.DatabaseConnection;
import cybersoft.java11.crm.config.MySqlConnection;
import cybersoft.java11.crm.dao.container.IOCContainer;
import cybersoft.java11.crm.model.Project;

public class ProjectDao {
	private DatabaseConnection _dbConnection;
	private UserDao userDao;
	
	public ProjectDao() {
		_dbConnection = IOCContainer.getDatabaseConnection();
		userDao = new UserDao();
	}
	
	public List<Project> findAll() {
		List<Project> listProject = new LinkedList<Project>();
		Connection connection = _dbConnection.getConnection();
		try {
			Statement statement = connection.createStatement();
			String query = "select id, name, description, start_date, end_date, create_user_id from project";
			ResultSet results = statement.executeQuery(query);
			//iterator
			while(results.next()) {
				Project newProject = new Project();
				newProject.setId(results.getInt("id"));
				newProject.setName(results.getString("name"));
				newProject.setDescription(results.getString("description"));
				newProject.setStart_date(results.getDate("start_date"));
				newProject.setEnd_date(results.getDate("end_date"));
				newProject.setCreate_user_id(userDao.findById(results.getInt("create_user_id")));
				
				listProject.add(newProject);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listProject;
	}
	
	public Project findByID(int id) {
		Connection connection = _dbConnection.getConnection();
		Project newProject = new Project();
		
		try {
			String query = "select id, name, description, start_date, end_date, create_user_id from project where id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
		
			statement.setInt(1, id);
			
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				newProject.setId(result.getInt("id"));
				newProject.setName(result.getString("name"));
				newProject.setDescription(result.getString("description"));
				newProject.setStart_date(result.getDate("start_date"));
				newProject.setEnd_date(result.getDate("end_date"));
				newProject.setCreate_user_id(userDao.findById(result.getInt("create_user_id")));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return newProject;
	}
	
	public int add(Project project) {
		int result = -1;
		
		Connection connection = _dbConnection.getConnection();
		String query = "insert project(`name`, `description`, `start_date`, `end_date`, `create_user_id`) values (?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, project.getName());
			statement.setString(2, project.getDescription());
			statement.setDate(3, project.getStart_date());
			statement.setDate(4, project.getEnd_date());
			statement.setInt(5, project.getCreate_user_id().getId());
			
			result = statement.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public int deleteByID(int id) {
		int result = -1;
		
		Connection connection = _dbConnection.getConnection();
		String query = "delete from project where id=?";
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			
			result = statement.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public int update(int id, Project project) {
		int result = -1;
		
		Connection connection = _dbConnection.getConnection();
		String query = "update project set name=?, description=?, end_date=? where id=?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, project.getName());
			statement.setString(2, project.getDescription());
			statement.setDate(3, project.getEnd_date());
//			System.out.println(project.getEnd_date() + "from projectdao");
			statement.setInt(4, id);
			
			result = statement.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
}
