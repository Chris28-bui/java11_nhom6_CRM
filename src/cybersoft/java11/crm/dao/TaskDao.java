package cybersoft.java11.crm.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import cybersoft.java11.crm.config.DatabaseConnection;
import cybersoft.java11.crm.config.MySqlConnection;
import cybersoft.java11.crm.dao.container.IOCContainer;
import cybersoft.java11.crm.model.Task;

public class TaskDao {
	private DatabaseConnection _dbConnection;

	public TaskDao() {
		_dbConnection = IOCContainer.getDataBaseConnection();
	}
	
	public List<Task> findAll() {
		List<Task> listTask = new LinkedList<Task>();
		
		Connection connection = _dbConnection.getConnection();
		
		try {
			Statement statement = connection.createStatement();
			String query = "select id, name, description, start_date, end_date, assignee, project_id, status_id from task";
			ResultSet results = statement.executeQuery(query);
			
			while (results.next()) {
				Task newTask = new Task();
				newTask.setId(results.getInt("id"));
				newTask.setName(results.getString("name"));
				newTask.setDescription(results.getString("description"));
				newTask.setStart_date(results.getDate("start_date"));
				newTask.setEnd_date(results.getDate("end_date"));
//				newTask.setAssignee(results.getInt("assignee"));
//				newTask.setProject_id(results.getInt("project_id"));
//				newTask.setStatus_id(results.getInt("status_id"));
				newTask.setAssignee(null);
				newTask.setProject_id(null);
				newTask.setStatus_id(null);
				
				listTask.add(newTask);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listTask;
	}
	
	
}
