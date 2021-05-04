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
}
