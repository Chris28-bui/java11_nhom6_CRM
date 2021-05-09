package cybersoft.java11.crm.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cybersoft.java11.crm.biz.AuthBiz;
import cybersoft.java11.crm.model.User;
import cybersoft.java11.crm.utils.UrlConst;

@WebServlet(name="authServlet", urlPatterns= {
		"/login",
		"/logout",
		"/register"
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
		req.getRequestDispatcher(UrlConst.LOGIN).forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = req.getServletPath();
		switch(path) {
		case "/login":
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			System.out.printf("email: %s, password: %s \n", email, password);
			
			User user = biz.login(email, password);
			System.out.println(user);
			if(user!=null) {
				//logged in successfully
				//session
				HttpSession session = req.getSession();
				session.setAttribute("userId","" + user.getId());
				session.setMaxInactiveInterval(30);
				//redirect
				resp.sendRedirect(req.getContextPath() + "/home");
			} else {
				//logged in unsuccessfully
				req.getRequestDispatcher("/WEB-INF/auth/login.jsp").forward(req, resp);
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
