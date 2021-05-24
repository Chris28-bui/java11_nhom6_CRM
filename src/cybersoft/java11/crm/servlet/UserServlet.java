package cybersoft.java11.crm.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cybersoft.java11.crm.biz.RoleBiz;
import cybersoft.java11.crm.biz.UserBiz;
import cybersoft.java11.crm.model.Role;
import cybersoft.java11.crm.model.User;
import cybersoft.java11.crm.utils.JspPathConst;
import cybersoft.java11.crm.utils.UrlConst;

@WebServlet(name="userServlet", urlPatterns= {
		UrlConst.USER_DASHBOARD,
		UrlConst.USER_ADD,
		UrlConst.USER_DELETE,
		UrlConst.USER_UPDATE
})
public class UserServlet extends HttpServlet {
	private UserBiz userbiz;
	private RoleBiz rolebiz;
	
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		userbiz = new UserBiz();
		rolebiz=new RoleBiz();
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String servletPath=req.getServletPath();
		HttpSession session = req.getSession();
		switch (servletPath) {
		case UrlConst.USER_DASHBOARD:
			
			String userId=req.getParameter("id");
			if(userId!=null) {
				List<User> listUser=userbiz.findByRoleId(Integer.parseInt(userId));
				req.setAttribute("users", listUser);
			}
			else {
				List<User> listUser=userbiz.findAll();
				req.setAttribute("users", listUser);
			}
			
			req.getRequestDispatcher(JspPathConst.USER_DASHBOARD).forward(req, resp);
			break;
		case UrlConst.USER_ADD:
			req.setAttribute("roleId", session.getAttribute("userRole"));
			List<Role> listRole = rolebiz.findAll();
			HttpSession curSession = req.getSession();
			req.setAttribute("roles", listRole);

			
			req.getRequestDispatcher(JspPathConst.USER_ADD).forward(req, resp);
			break;
		case UrlConst.USER_UPDATE:
			int id = Integer.parseInt(req.getParameter("id"));
			User usertoUpdate = userbiz.findById(id);
			req.setAttribute("user", usertoUpdate);
			
			List<Role> listRoleUpdate = rolebiz.findAll();
			
			req.setAttribute("roles", listRoleUpdate);
			req.getRequestDispatcher(JspPathConst.USER_UPDATE).forward(req, resp);
			break;
		case UrlConst.USER_DELETE:
			int idUser = Integer.parseInt(req.getParameter("id"));
			userbiz.delete(idUser);
			resp.sendRedirect(req.getContextPath()+UrlConst.USER_DASHBOARD);
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path=req.getServletPath();
		
		switch (path) {
		case UrlConst.USER_DASHBOARD:
			
			break;
		case UrlConst.USER_ADD:
			String userFullname=req.getParameter("fullname");
			String userEmail=req.getParameter("email");
			String userPassword=req.getParameter("password");
			String userAddress=req.getParameter("address");
			String userPhone=req.getParameter("phone");
			String userRole=req.getParameter("role_id");
			
			if(userFullname == null ||userEmail == null ||userPassword == null ||userAddress == null ||userPhone == null)
			{
				req.setAttribute("msg", "Fill all the field above");
				req.getRequestDispatcher(JspPathConst.USER_ADD).forward(req, resp);
			}
			else {
				 Role roleid=rolebiz.findById(Integer.parseInt(userRole));
				
				User newUser=new User();
				newUser.setFullname(userFullname);
				newUser.setEmail(userEmail);
				newUser.setPassword(userPassword);
				newUser.setAddress(userAddress);
				newUser.setPhone(userPhone);
				newUser.setRole(roleid);
				
				userbiz.add(newUser);
				resp.sendRedirect(req.getContextPath()+UrlConst.USER_DASHBOARD);
				
			}
			break;
		case UrlConst.USER_UPDATE:
			String updateFullname=req.getParameter("fullname");
			String updateEmail=req.getParameter("email");
			String updatePassword=req.getParameter("password");
			String updateAddress=req.getParameter("address");
			String updatePhone=req.getParameter("phone");
			String updateRole=req.getParameter("role_id");
			
			int id=Integer.parseInt(req.getParameter("id"));
			if(updateFullname == null ||updateEmail == null ||updatePassword == null ||updateAddress == null ||updatePhone == null)
			{
				req.setAttribute("msg", "Fill all the field above");
				req.getRequestDispatcher(JspPathConst.USER_ADD).forward(req, resp);
			}
			else {
				 Role roleid=rolebiz.findById(Integer.parseInt(updateRole));
				
				User newUser=new User();
				newUser.setFullname(updateFullname);
				newUser.setEmail(updateEmail);
				newUser.setPassword(updatePassword);
				newUser.setAddress(updateAddress);
				newUser.setPhone(updatePhone);
				newUser.setRole(roleid);
				
				userbiz.update(id, newUser);
				resp.sendRedirect(req.getContextPath()+UrlConst.USER_DASHBOARD);
				
			}
			break;
		case UrlConst.USER_DELETE:
	
			break;

		default:
			break;
		}
	}
}
