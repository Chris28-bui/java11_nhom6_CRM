package cybersoft.java11.crm.servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cybersoft.java11.crm.biz.ProjectBiz;
import cybersoft.java11.crm.dao.UserDao;
import cybersoft.java11.crm.model.Project;
import cybersoft.java11.crm.utils.JspPathConst;
import cybersoft.java11.crm.utils.UrlConst;

@WebServlet(name= "projectServlet", urlPatterns= {
//		"/projects"
		UrlConst.PROJECT_DASHBOARD,
		UrlConst.PROJECT_ADD,
		UrlConst.PROJECT_DELETE,
		UrlConst.PROJECT_UPDATE
})
public class ProjectServlet extends HttpServlet{
	private ProjectBiz biz;
	private UserDao userDao;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		biz = new ProjectBiz();
		userDao = new UserDao();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = req.getServletPath();
		HttpSession session = req.getSession();
		switch(path) {
			case UrlConst.PROJECT_DASHBOARD:
				List<Project> listProject = biz.findAll();
				req.setAttribute("projects", listProject);
				req.setAttribute("roleId", session.getAttribute("userRole"));
				req.getRequestDispatcher(JspPathConst.PROJECT_DASHBOARD).forward(req, resp);
				break;
			case UrlConst.PROJECT_ADD:
				req.setAttribute("userId", session.getAttribute("userId"));
				req.getRequestDispatcher(JspPathConst.PROJECT_ADD).forward(req, resp);
				break;
			case UrlConst.PROJECT_UPDATE:
				int id = Integer.parseInt(req.getParameter("id"));
				Project willBeUpdated = biz.findByID(id);
				req.setAttribute("project", willBeUpdated);
				req.getRequestDispatcher(JspPathConst.PROJECT_UPDATE).forward(req, resp);
				break;
			case UrlConst.PROJECT_DELETE:
				int willBeDeleted = Integer.parseInt(req.getParameter("id"));
				biz.deleteById(willBeDeleted);
				
				resp.sendRedirect(req.getContextPath() + UrlConst.PROJECT_DASHBOARD);
				break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = req.getServletPath();
		switch(path) {
			case UrlConst.PROJECT_DASHBOARD:
				break;
			case UrlConst.PROJECT_ADD:
				//get parameter from form
				String projectName = req.getParameter("project-name");
				String projectDescription = req.getParameter("project-description");
				int userCreate = Integer.parseInt(req.getParameter("project-user-create"));
				Date startDate = Date.valueOf(req.getParameter("project-start-date"));
				Date endDate = Date.valueOf(req.getParameter("project-end-date"));
				
				//check null and create new project
				if(projectName == null || projectName.equals("")) {
					req.setAttribute("msg", "Project name cannot be empty");
					req.getRequestDispatcher(JspPathConst.PROJECT_ADD).forward(req, resp);
				} else if(projectDescription == null || projectDescription.equals("")) {
					req.setAttribute("msg", "Project description cannot be empty");
					req.getRequestDispatcher(JspPathConst.PROJECT_ADD).forward(req, resp);
				} else {
					Project newProject = new Project();
					
					newProject.setName(projectName);
					newProject.setDescription(projectDescription);
					newProject.setStart_date(startDate);
					newProject.setEnd_date(endDate);
					newProject.setCreate_user_id(userDao.findById(userCreate));
					
					biz.add(newProject);
					
					resp.sendRedirect(req.getContextPath() + UrlConst.PROJECT_DASHBOARD);
				}
				break;
			case UrlConst.PROJECT_UPDATE:
				int projectID = Integer.parseInt(req.getParameter("project-id"));
				String projectNameUpdated = req.getParameter("project-name");
				String projectDescriptionUpdated = req.getParameter("project-description");
				Date projectEndDateUpdated = Date.valueOf(req.getParameter("project-end-date"));
				
				if(projectNameUpdated == null || projectNameUpdated.equals("")) {
					req.setAttribute("msg", "Project name cannot be empty");
					req.getRequestDispatcher(JspPathConst.PROJECT_UPDATE).forward(req, resp);
				} else if(projectDescriptionUpdated == null || projectDescriptionUpdated.equals("")) {
					req.setAttribute("msg", "Project description cannot be empty");
					req.getRequestDispatcher(JspPathConst.PROJECT_UPDATE).forward(req, resp);
				} else {
					Project projectUpdate = new Project();
					projectUpdate.setName(projectNameUpdated);
					projectUpdate.setDescription(projectDescriptionUpdated);
					projectUpdate.setEnd_date(projectEndDateUpdated);
					
					biz.update(projectID, projectUpdate);
					resp.sendRedirect(req.getContextPath() + UrlConst.PROJECT_DASHBOARD);
				}
				break;
			case UrlConst.PROJECT_DELETE:
				break;
		}
	}
}
