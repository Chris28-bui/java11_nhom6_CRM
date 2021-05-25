package cybersoft.java11.crm.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cybersoft.java11.crm.biz.AuthBiz;
import cybersoft.java11.crm.model.User;
import cybersoft.java11.crm.utils.JspPathConst;
import cybersoft.java11.crm.utils.UrlConst;

@WebServlet(name="authServlet", urlPatterns= {
		UrlConst.AUTH_LOGIN,
		UrlConst.AUTH_LOGOUT,
		UrlConst.AUTH_FORGOT_PASSWORD,
		UrlConst.AUTH_REGISTER
})
public class AuthServlet extends HttpServlet {
	private AuthBiz biz;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		biz = new AuthBiz();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO: forward to login.jsp
		String path = req.getServletPath();
		switch(path) {
			case UrlConst.AUTH_LOGIN:
				req.getRequestDispatcher(JspPathConst.AUTH_LOGIN).forward(req, resp);
			break;
			
			case UrlConst.AUTH_LOGOUT:
				req.getSession().invalidate();
				resp.sendRedirect(req.getContextPath() + UrlConst.AUTH_LOGIN);
				break;
				
			default:
				break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = req.getServletPath();
		switch(path) {
		case UrlConst.AUTH_LOGIN:
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			
			User user = biz.login(email, password);
			if(user!=null) {
				//logged in successfully
				//session
				HttpSession session = req.getSession();
				session.setAttribute("userId", "" + user.getId());
				session.setAttribute("fullname", user.getFullname());
				session.setAttribute("userRole", user.getRole());
				session.setMaxInactiveInterval(3600);
				//redirect to the page after login success
				resp.sendRedirect(req.getContextPath() + UrlConst.HOME);
			} else {
				//logged in unsuccessfully
				req.getRequestDispatcher(JspPathConst.AUTH_LOGIN).forward(req, resp);
			}
			break;
		
		case "/logout":
			break;
			
		case "/register":
			break;
		
		default:
			break;
		}
	}
}
