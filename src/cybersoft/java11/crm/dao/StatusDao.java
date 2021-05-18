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
import cybersoft.java11.crm.model.Status;

public class StatusDao {
	private DatabaseConnection _dbConnection;
	
	public StatusDao() {
		_dbConnection = IOCContainer.getDatabaseConnection();
	}
	
	public List<Status> findAll(){
		List<Status> statusList = new LinkedList<Status>();
		
		//establish connection
		Connection connection = _dbConnection.getConnection();
		try {
			//query
			Statement statement = connection.createStatement();
			String query = "select id, name, description from status";
			ResultSet results = statement.executeQuery(query);
			
			//iteration
			while(results.next()) {
				//set value
				Status status = new Status();
				status.setId(results.getInt("id"));
				status.setName(results.getString("name"));
				status.setDescription(results.getString("description"));
				
				//add to list
				statusList.add(status);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
		
		return statusList;
	}
	
	public Status findById(int id) {
		Status newStatus = new Status();
		
		Connection connection = _dbConnection.getConnection();
		String query = "select id, name, description from status where id=? ";
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			
			ResultSet results = statement.executeQuery();
			
			while (results.next()) {
				newStatus.setId(results.getInt("id"));
				newStatus.setName(results.getString("name"));
				newStatus.setDescription(results.getString("description"));
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
		
		
		return newStatus;
	}
}
