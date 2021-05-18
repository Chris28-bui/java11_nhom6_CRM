package cybersoft.java11.crm.model;

import java.sql.Date;

public class Task {
	private int id;
	private String name;
	private String description;
	private Date start_date;
	private Date end_date;
	private User assignee;
	private Project project_id;
	private Status status_id;
	
	public Task(int id, String name, String description, Date start_date, Date end_date) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.start_date = start_date;
		this.end_date = end_date;
	}
	
	public Task() {
		this.id = -1;
		this.name = "";
		this.description = "";
		this.start_date = null;
		this.end_date = null;
	}
	
	// Getter/Setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public User getAssignee() {
		return assignee;
	}

	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}

	public Project getProject_id() {
		return project_id;
	}

	public void setProject_id(Project project_id) {
		this.project_id = project_id;
	}

	public Status getStatus_id() {
		return status_id;
	}

	public void setStatus_id(Status status_id) {
		this.status_id = status_id;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", name=" + name + ", description=" + description + ", start_date=" + start_date
				+ ", end_date=" + end_date + ", assignee=" + assignee + ", project_id=" + project_id + ", status_id="
				+ status_id + "]";
	}

	
	
}
