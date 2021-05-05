package cybersoft.java11.crm.model;

public class Status {
	private int id;
	private String name;
	private String description;
	
	//constructor with variables
	public Status(int id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	//constructor without variables
	public Status() {
		this.id = -1;
		this.name = "";
		this.description = "";
	}

	//setters and getters
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
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return id + " " + name + " " + description;
	}
}
