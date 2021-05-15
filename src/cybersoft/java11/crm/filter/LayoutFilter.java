package cybersoft.java11.crm.filter;
import javax.servlet.annotation.WebFilter;

import com.opensymphony.sitemesh.webapp.SiteMeshFilter;

import cybersoft.java11.crm.utils.UrlConst;

@WebFilter(displayName = "sitemesh", urlPatterns = UrlConst.ROOT)
public class LayoutFilter extends SiteMeshFilter {
	
}
