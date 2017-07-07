package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Administrador;

@WebFilter(urlPatterns = "/administrador/*")
public class FiltroAdm implements Filter {	
	@Override
	public void destroy() {			
	}	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		Administrador u = null;	
		HttpSession session = ((HttpServletRequest) req).getSession(false);
		
		if(session != null){
			u = (Administrador) session.getAttribute("administrador");
		}		
		if (u == null){
			String contextPath = ((HttpServletRequest) req).getContextPath();
			((HttpServletResponse) resp).sendRedirect(contextPath + "/login.xhtml");
		} else {
			chain.doFilter(req, resp);			
		}
	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {		
	}
}

