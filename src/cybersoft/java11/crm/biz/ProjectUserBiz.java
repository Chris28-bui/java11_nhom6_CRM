package cybersoft.java11.crm.biz;

import java.util.List;

import cybersoft.java11.crm.dao.ProjectUserDao;
import cybersoft.java11.crm.model.project_user;

public class ProjectUserBiz {
	private ProjectUserDao dao;
	
	public ProjectUserBiz() {
		dao = new ProjectUserDao();
	}
	
	public List<project_user> findAll(){
		return dao.findAll();
	}
}
