package cybersoft.java11.crm.biz;

import java.util.List;

import cybersoft.java11.crm.dao.UserDao;
import cybersoft.java11.crm.model.User;

public class UserBiz {
	private UserDao dao;
	
	public UserBiz() {
		dao = new UserDao();
	}
	
	public List<User> findAll(){
		return dao.findAll();
	}
	
	public List<User> findByRoleId(int id){
		return dao.findByRoleId(id);
	}
	
	public User findById(int id) {
		return dao.findById(id);
	}
	
	public int add(User user) {
		return dao.add(user);
	}
	
	public int update(int id,User user) {
		return dao.update(id, user);
	}
	
	public int delete(int id) {
		return dao.delete(id);
	}
}

