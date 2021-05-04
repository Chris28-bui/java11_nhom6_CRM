package cybersoft.java11.crm.model;

public class User {
	private int id;
	private String email;
	private String password;
	private String fullname;
	private String address;
	private String phone;
	private Role role;
	public User(int id, String email, String password, String fullname, String address, String phone) {
//		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.address = address;
		this.phone = phone;
		this.role = null;
	}
	
	public User() {
		this.id = -1;
		this.email = "";
		this.password = "";
		this.fullname = "";
		this.address = "";
		this.phone = "";
		this.role = null;
	}
	
}
