package cybersoft.java11.crm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cybersoft.java11.crm.utils.UrlConst;

@WebFilter(urlPatterns= {
		"/*"
})
public class AuthFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("userId");
		
		if(userId != null) {
			chain.doFilter(request, response);
		} else {
			if(req.getServletPath().equals(UrlConst.AUTH_LOGIN) || req.getServletPath().startsWith("/assets/"))
				chain.doFilter(request, response);
			else 
				resp.sendRedirect(req.getContextPath() + UrlConst.AUTH_LOGIN);
		}
	}

}
