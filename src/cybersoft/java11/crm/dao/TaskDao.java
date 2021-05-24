package cybersoft.java11.crm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import cybersoft.java11.crm.config.DatabaseConnection;
import cybersoft.java11.crm.dao.container.IOCContainer;
import cybersoft.java11.crm.model.Task;

public class TaskDao {
	private DatabaseConnection _dbConnection;
	private UserDao user;
	private ProjectDao project;
	private StatusDao status;
	
	public TaskDao() {
		_dbConnection = IOCContainer.getDatabaseConnection();
		user = new UserDao();
		project = new ProjectDao();
		status = new StatusDao();
	}
	
	public List<Task> findAll() {
		List<Task> listTask = new LinkedList<Task>();
		
		Connection connection = _dbConnection.getConnection();
		
		try {
			Statement statement = connection.createStatement();
			String query = "select id, name, description, start_date, due_date, assignee, project_id, status_id from task";
			ResultSet results = statement.executeQuery(query);
			
			while (results.next()) {
				Task newTask = new Task();
				newTask.setId(results.getInt("id"));
				newTask.setName(results.getString("name"));
				newTask.setDescription(results.getString("description"));
				newTask.setStart_date(results.getDate("start_date"));
				newTask.setEnd_date(results.getDate("due_date"));
				newTask.setAssignee(user.findById(results.getInt("assignee")));
				newTask.setProject_id(project.findByID(results.getInt("project_id")));
				newTask.setStatus_id(status.findById(results.getInt("status_id")));
				listTask.add(newTask);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
		
		return listTask;
	}
	
	public List<Task> findByProjectId(int id) {
		List<Task> projectTask = new LinkedList<Task>();
		
		Connection connection = _dbConnection.getConnection();
		String query = "select id, name, description, start_date, due_date, assignee, project_id, status_id from task where project_id=?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet results = statement.executeQuery();
			
			while(results.next()) {
				Task newTask = new Task();
				newTask.setId(results.getInt("id"));
				newTask.setName(results.getString("name"));
				newTask.setDescription(results.getString("description"));
				newTask.setStart_date(results.getDate("start_date"));
				newTask.setEnd_date(results.getDate("due_date"));
				newTask.setAssignee(user.findById(results.getInt("assignee")));
				newTask.setProject_id(project.findByID(results.getInt("project_id")));
				newTask.setStatus_id(status.findById(results.getInt("status_id")));
				projectTask.add(newTask);
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
		return projectTask;
	}
	
	public Task findById(int id) {
		Task task = new Task();
		
		Connection connection = _dbConnection.getConnection();
		String query = "select * from task where id=?";
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setInt(1, id);
			
			ResultSet results = statement.executeQuery();
			
			while (results.next()) {
				task.setId(results.getInt("id"));
				task.setName(results.getString("name"));
				task.setDescription(results.getString("description"));
				task.setStart_date(results.getDate("start_date"));
				task.setEnd_date(results.getDate("due_date"));
				task.setAssignee(user.findById(results.getInt("assignee")));
				task.setProject_id(project.findByID(results.getInt("project_id")));
				task.setStatus_id(status.findById(results.getInt("status_id")));
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
		return task;
	}
	
	public int add(Task newTask) {
		int result = -1;
		
		Connection connection = _dbConnection.getConnection();
		String query = "insert task(`name`, `description`, `start_date`, `due_date`, `assignee`, `project_id`, `status_id`) values(?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, newTask.getName());
			statement.setString(2, newTask.getDescription());
			statement.setDate(3, newTask.getStart_date());
			statement.setDate(4, newTask.getEnd_date());
			statement.setInt(5, newTask.getAssignee().getId());
			statement.setInt(6, newTask.getProject_id().getId());
			statement.setInt(7, newTask.getStatus_id().getId());
			
			result = statement.executeUpdate(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	
	public int update(int id, Task task) {
		int result = -1;
		
		Connection connection = _dbConnection.getConnection();
		
		try {
			String query = "update task set name = ?, description = ?, start_date = ?, due_date = ?, assignee = ?, project_id = ?, status_id = ? where id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, task.getName());
			statement.setString(2, task.getDescription());
			statement.setDate(3, task.getStart_date());
			statement.setDate(4, task.getEnd_date());
			statement.setInt(5, user.findById(task.getAssignee().getId()).getId());
			statement.setInt(6, project.findByID(task.getProject_id().getId()).getId());
			statement.setInt(7, status.findById(task.getStatus_id().getId()).getId());
			statement.setInt(8, id);
			
			result = statement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public int deleteById(int id) {
		int result = -1;
		
		Connection connection = _dbConnection.getConnection();
		
		try {
			String query = "delete from task where id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			
			result = statement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
