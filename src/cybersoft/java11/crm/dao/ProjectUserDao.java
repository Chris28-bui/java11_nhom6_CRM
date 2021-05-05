package cybersoft.java11.crm.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import cybersoft.java11.crm.config.MySqlConnection;
import cybersoft.java11.crm.model.project_user;

public class ProjectUserDao {
	public List<project_user> findAll(){
		List<project_user> projectUserList = new LinkedList<project_user>();
		
		//establish connection
		Connection connection = MySqlConnection.getConnection();
		
		try {
			//query
			Statement statement = connection.createStatement();
			String query = "select project_id, user_id, join_date, role_description from Project_User";
			ResultSet result = statement.executeQuery(query);
			
			//iterations
			while(result.next()) {
				project_user newProjectUser = new project_user();
				newProjectUser.setProjectId(result.getInt("project_id"));
				newProjectUser.setUserId(result.getInt("user_id"));
				newProjectUser.setJoinDate(result.getDate("join_date"));
				newProjectUser.setRoleDescription(result.getString("role_description"));
				
				projectUserList.add(newProjectUser);
				
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return projectUserList;
	}
}
