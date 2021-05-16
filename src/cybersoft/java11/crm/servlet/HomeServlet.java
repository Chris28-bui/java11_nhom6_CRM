package cybersoft.java11.crm.servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cybersoft.java11.crm.biz.HomeBiz;
import cybersoft.java11.crm.utils.JspPathConst;
import cybersoft.java11.crm.utils.UrlConst;

@WebServlet(name = "homeServlet", urlPatterns = {
//		"/health",
//		"/home"
		UrlConst.HEALTH,
		UrlConst.HOME
})
public class HomeServlet extends HttpServlet {
	private HomeBiz biz;
	
	@Override
	public void init() throws ServletException {
		biz = new HomeBiz();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO: check database health
		
		//tạo mới cookies: "username" is name of cookie and "tuanphan91" is a value
		Cookie cookie = new Cookie("username", "tuanphan");
		
		//đặt thời gian sống cho (tính theo second)
		cookie.setMaxAge(20);
		
		//add vô response to send to client
		resp.addCookie(cookie);
		
		Date curTime = Calendar.getInstance().getTime();
		String now = "" + curTime.getHours() 
					+ "." + curTime.getMinutes() 
					+ "." + curTime.getSeconds() 
					+ "." + curTime.getDate()
					+ "." + curTime.getMonth()
					+ "." + curTime.getYear();
		Cookie anotherCookie = new Cookie("last_access", now);
		//60 giây x 60 phút x 24 giờ nhân 30 ngay
		cookie.setMaxAge(60*60*24*30);
		
		resp.addCookie(anotherCookie);
		
		//session
		HttpSession currentSession = req.getSession();
		
		System.out.println(currentSession.getAttribute("loggedUser"));
		
		//check attribute is available already or not
		if(currentSession.getAttribute("loggedUser") == null) {
			//set an attribute to current session
			currentSession.setAttribute("loggedUser", "tuanphan91");
			//set max time to wait for another request from client
			currentSession.setMaxInactiveInterval(60*60); //second
		}
		
		boolean databaseCheckResult = biz.checkHealth();
		if(databaseCheckResult)
			resp.getWriter().append("Connection to database has been made successfully.");
		else
			resp.getWriter().append("Connection to database has been made unsuccessfully.");

		req.getRequestDispatcher(JspPathConst.HOME_DASHBOARD).forward(req, resp);
	}
}
