package cybersoft.java11.crm.model;

import java.util.Calendar;
import java.util.Date;

public class project_user {
	
	private int projectId;
	private int userId;
	private Date joinDate;
	private String roleDescription;
	
	
	//constructors
	public project_user(int projectId, int userId, Date joinDate, String roleDescription) {
		this.projectId = projectId;
		this.userId = userId;
		this.joinDate = joinDate;
		this.roleDescription = roleDescription;
	}

	public project_user() {
		this.projectId = -1;
		this.userId = -1;
		this.joinDate = Calendar.getInstance().getTime();
		this.roleDescription = "";
	}

	//getters and setters
	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	@Override
	public String toString() {
		return "project_user [projectId=" + projectId + ", userId=" + userId + ", joinDate=" + joinDate
				+ ", roleDescription=" + roleDescription + "]";
	}
	
}
