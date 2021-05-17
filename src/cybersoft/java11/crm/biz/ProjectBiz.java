package cybersoft.java11.crm.biz;

import java.util.List;

import cybersoft.java11.crm.dao.ProjectDao;
import cybersoft.java11.crm.model.Project;

public class ProjectBiz {
	private ProjectDao project;
	
	public ProjectBiz() {
		project = new ProjectDao();
	}
	
	public List<Project> findAll() {
		return project.findAll();
	}
	
	public Project findByID(int id) {
		return project.findByID(id);
	}
	
	public int add(Project projectAdd) {
		return project.add(projectAdd);
	}
	
	public int deleteById(int id) {
		return project.deleteByID(id);
	}
	
	public int update(int id, Project projectUpdate) {
		return project.update(id, projectUpdate);
	}
}
