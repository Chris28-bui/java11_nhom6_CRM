package cybersoft.java11.crm.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cybersoft.java11.crm.biz.ProjectUserBiz;
import cybersoft.java11.crm.model.project_user;

@WebServlet(name="projectUserServlet", urlPatterns= {"/projectUser"})
public class ProjectUserServlet extends HttpServlet {
	private ProjectUserBiz biz;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		biz = new ProjectUserBiz();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<project_user> projectUserList = biz.findAll();
		
		for(project_user projectUser : projectUserList) {
			resp.getWriter().append(projectUser.toString());
		}
	}
}
