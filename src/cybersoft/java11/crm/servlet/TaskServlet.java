package cybersoft.java11.crm.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cybersoft.java11.crm.biz.TaskBiz;
import cybersoft.java11.crm.dao.ProjectDao;
import cybersoft.java11.crm.dao.StatusDao;
import cybersoft.java11.crm.dao.UserDao;
import cybersoft.java11.crm.model.Project;
import cybersoft.java11.crm.model.Status;
import cybersoft.java11.crm.model.Task;
import cybersoft.java11.crm.model.User;
import cybersoft.java11.crm.utils.JspPathConst;
import cybersoft.java11.crm.utils.UrlConst;

@WebServlet (name= "taskServlet", urlPatterns= {
		UrlConst.TASK_DASHBOARD,
		UrlConst.TASK_ADD,
		UrlConst.TASK_UPDATE,
		UrlConst.TASK_DELETE
}) 
public class TaskServlet extends HttpServlet {
	private TaskBiz task;
	private ProjectDao project;
	private StatusDao status;
	private UserDao user;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		task = new TaskBiz();
		project = new ProjectDao();
		status = new StatusDao();
		user = new UserDao();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = req.getServletPath();
		HttpSession session = req.getSession();
		switch (path) {
		case UrlConst.TASK_DASHBOARD:
			String taskId = req.getParameter("id");
			if(taskId != null) {
				List<Task> listTask = task.findByProjectId(Integer.parseInt(taskId));
				req.setAttribute("listTask", listTask);
			} else {
				List<Task> listTask = task.findAll();
				req.setAttribute("listTask", listTask);
			}
			req.setAttribute("roleId", session.getAttribute("userRole"));
			req.getRequestDispatcher(JspPathConst.TASK_DASHBOARD).forward(req, resp);
			break;
		case UrlConst.TASK_ADD:
			req.getRequestDispatcher(JspPathConst.TASK_ADD).forward(req, resp);
			break;
		case UrlConst.TASK_UPDATE:
			int id = Integer.parseInt(req.getParameter("id"));
			Task willBeUpdated = task.findById(id);
			req.setAttribute("updateTask", willBeUpdated);
			req.getRequestDispatcher(JspPathConst.TASK_UPDATE).forward(req, resp);
			break;
		case UrlConst.TASK_DELETE:
			int deleteId = Integer.parseInt(req.getParameter("id"));
			task.deleteById(deleteId);
			
			resp.sendRedirect(req.getContextPath() + UrlConst.TASK_DASHBOARD);
			
			break;
		default:
			break;
		}
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = req.getServletPath();
		switch (path) {
		case UrlConst.TASK_DASHBOARD:
			
			break;
		case UrlConst.TASK_ADD:
			//get all parameter from JSP file
			String taskName = req.getParameter("task-name");
			String taskDescription = req.getParameter("task-description");
			String taskStartDate = req.getParameter("task-start-date");
			String taskEndDate = req.getParameter("task-end-date");
			String taskAssignee = req.getParameter("task-assignee");
			String taskProjectId = req.getParameter("task-project-id");
			String taskStatus = req.getParameter("task-status");
			
			if (taskName == null || taskDescription == null || taskStartDate == null || taskEndDate == null || taskAssignee == null || taskProjectId == null || taskStatus == null) {
				req.setAttribute("msg", "Fill all the field above");
				req.getRequestDispatcher(JspPathConst.TASK_ADD).forward(req, resp);
			} else {
				//convert startDate, endDate, projectId, statusId, userId to propriate value type
				Date startDate = Date.valueOf(taskStartDate);
				Date endDate = Date.valueOf(taskEndDate);
				Project projectId = project.findByID(Integer.parseInt(taskProjectId));
				Status statusId = status.findById(Integer.parseInt(taskStatus));
				User userId = user.findById(Integer.parseInt(taskAssignee));
				
				//create new task then add to task database
				Task newTask = new Task();
				newTask.setName(taskName);
				newTask.setDescription(taskDescription);
				newTask.setStart_date(startDate);
				newTask.setEnd_date(endDate);
				newTask.setProject_id(projectId);
				newTask.setStatus_id(statusId);
				newTask.setAssignee(userId);
				
				task.add(newTask);
				resp.sendRedirect(req.getContextPath() + UrlConst.TASK_DASHBOARD);
			}
			
			break;
		case UrlConst.TASK_UPDATE:
			//get all parameter from JSP file
			String updateTaskName = req.getParameter("task-name");
			String updateTaskDescription = req.getParameter("task-description");
			String updateTaskStartDate = req.getParameter("task-start-date");
			String updateTaskEndDate = req.getParameter("task-end-date");
			String updateTaskAssignee = req.getParameter("task-assignee");
			String updateTaskProjectId = req.getParameter("task-project-id");
			String updateTaskStatus = req.getParameter("task-status");
			
			int id = Integer.parseInt(req.getParameter("task-id"));
			
			if (updateTaskName == null || updateTaskDescription == null || updateTaskStartDate == null || updateTaskEndDate == null || updateTaskAssignee == null || updateTaskProjectId == null || updateTaskStatus == null) {
				req.setAttribute("msg", "Fill all the field above");
				req.getRequestDispatcher(JspPathConst.TASK_UPDATE).forward(req, resp);
			} else {
				//convert startDate, endDate, projectId, statusId, userId to propriate value type
				Date startDate = Date.valueOf(updateTaskStartDate);
				Date endDate = Date.valueOf(updateTaskEndDate);
				Project projectId = project.findByID(Integer.parseInt(updateTaskProjectId));
				Status statusId = status.findById(Integer.parseInt(updateTaskStatus));
				User userId = user.findById(Integer.parseInt(updateTaskAssignee));
				
				//create new task then add to task database
				Task newTask = new Task();
				newTask.setName(updateTaskName);
				newTask.setDescription(updateTaskDescription);
				newTask.setStart_date(startDate);
				newTask.setEnd_date(endDate);
				newTask.setProject_id(projectId);
				newTask.setStatus_id(statusId);
				newTask.setAssignee(userId);
				
				task.update(id, newTask);
				
				resp.sendRedirect(req.getContextPath() + UrlConst.TASK_DASHBOARD);
			}
			break;
		case UrlConst.TASK_DELETE:
			
			break;
		default:
			break;
		}
	}
	
	
}
