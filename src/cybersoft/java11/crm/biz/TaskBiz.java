package cybersoft.java11.crm.biz;

import java.util.List;

import cybersoft.java11.crm.dao.TaskDao;
import cybersoft.java11.crm.model.Task;

public class TaskBiz {
	private TaskDao task;
	
	public TaskBiz() {
		task = new TaskDao();
	}
	
	public List<Task> findAll() {
		return task.findAll();
	}
	
	public List<Task> findByProjectId(int id) {
		return task.findByProjectId(id);
	}
	
	public int add(Task newTask) {
		return task.add(newTask);
	}

	public Task findById(int id) {
		return task.findById(id);
	}
	
	public int deleteById(int id) {
		return task.deleteById(id);
	}
	
	public int update(int id, Task newtask) {
		return task.update(id, newtask);
	}
}
