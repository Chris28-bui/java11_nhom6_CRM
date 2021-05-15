package cybersoft.java11.crm.biz;

import java.util.List;

import cybersoft.java11.crm.dao.RoleDao;
import cybersoft.java11.crm.model.Role;

public class RoleBiz {
	private RoleDao dao;
	
	public RoleBiz() {
		dao = new RoleDao();
	}
	
	public List<Role> findAll(){
		return dao.findAll();
	}
	
	public Role findById(int id) {
		return dao.findByID(id);
	}
	
	public int add(Role role) {
		return dao.add(role);
	}
	
	public int update(int id, Role role) {
		return dao.update(id, role);
	}
	
	public int deleteByID(int id) {
		return dao.deleteByID(id);
	}
}
