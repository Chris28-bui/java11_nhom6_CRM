package cybersoft.java11.crm.model;

import java.util.Calendar;
import java.util.Date;

public class Project {
	private int id;
	private String name;
	private String description;
	private Date start_date;
	private Date end_date;
	private int create_user_id;
	
	// Constructor with params
	public Project(int id, String name, String description, Date start_date, Date end_date) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.start_date = start_date;
		this.end_date = end_date;
		this.create_user_id = -1;
	}
	
	// Constructor with no params
	public Project() {
		this.id = -1;
		this.name = "";
		this.description = "";
		this.start_date = Calendar.getInstance().getTime();
		this.end_date = null;
		this.create_user_id = -1;
	}
	
	// add Getter/Setter
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

	public int getCreate_user_id() {
		return create_user_id;
	}

	public void setCreate_user_id(int create_user_id) {
		this.create_user_id = create_user_id;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "" + id + " " + name + " " + description + " " + start_date + " " + end_date + " " + create_user_id;
	}
}
